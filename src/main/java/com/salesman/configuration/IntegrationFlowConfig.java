package com.salesman.configuration;

import com.salesman.dto.ReportDTO;
import com.salesman.service.DataReaderService;
import com.salesman.service.DataResultService;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.transformer.GenericTransformer;

import java.nio.file.Paths;
import java.util.Objects;

@Configuration
@EnableIntegration
public class IntegrationFlowConfig {

    private static final Boolean WATCH_SERVICE = Boolean.TRUE;

    private final Long timerate;
    private final int maxMessagesPerPoll;
    private final String fileType;
    private final String folderIn;
    private final String folderOut;

    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final SalesService salesService;
    private final DataReaderService dataReaderService;
    private final DataResultService dataResultService;

    public IntegrationFlowConfig(DataReaderService dataReaderService,
                                 DataResultService dataResultService,
                                 CustomerService customerService,
                                 SalesmanService salesmanService,
                                 SalesService salesService,
                                 @Value("${poller.timerate}") Long timerate,
                                 @Value("${poller.maxmessages}") int maxMessagesPerPoll,
                                 @Value("${file.in.type}") String fileType,
                                 @Value("${file.in.folder}") String folderIn,
                                 @Value("${file.out.folder}") String folderOut) {

        this.dataReaderService = dataReaderService;
        this.dataResultService = dataResultService;
        this.customerService = customerService;
        this.salesmanService = salesmanService;
        this.salesService = salesService;
        this.timerate = timerate;
        this.maxMessagesPerPoll = maxMessagesPerPoll;
        this.fileType = fileType;
        this.folderIn = folderIn;
        this.folderOut = folderOut;
    }

    @Bean
    IntegrationFlow integrationFlow() {
        return IntegrationFlows
                .from(createFlowBuiler(),
                        spec -> spec.poller(Pollers.fixedRate(timerate).maxMessagesPerPoll(maxMessagesPerPoll)))
                .filter(new SimplePatternFileListFilter(fileType))
                .log(LoggingHandler.Level.INFO, "Processing file")
                .transform(Files.toStringTransformer())
                .transform(processData())
                .handle(generateMetricHandler())
                .log(LoggingHandler.Level.INFO, "Finished file read process ")
                .handle(fileWritingMessageHandler())
                .get();
    }

    private FileInboundChannelAdapterSpec createFlowBuiler() {
        return Files.inboundAdapter(Paths.get(getBasePath(), folderIn).toFile())
                .useWatchService(WATCH_SERVICE);
    }

    private GenericTransformer<String, ReportDTO> processData() {
        clearLists();
        return dataReaderService::processData;
    }

    public GenericHandler<ReportDTO> generateMetricHandler() {
        return (message, headers) -> dataResultService.saveResult(message);
    }

    private FileWritingMessageHandler fileWritingMessageHandler() {
        return Files.outboundAdapter(Paths.get(getBasePath(), folderOut).toFile())
                .deleteSourceFiles(true)
                .fileExistsMode(FileExistsMode.REPLACE)
                .fileNameGenerator(nameGenerator())
                .get();
    }

    private FileNameGenerator nameGenerator() {
        return fileName -> Objects.requireNonNull(fileName.getHeaders()
                .get(FileHeaders.FILENAME, String.class))
                .replaceFirst("(.*)(\\.dat)", "$1.done$2");
    }

    private String getBasePath() {
        return System.getProperty("user.home");
    }

    private void clearLists() {
        salesmanService.clearList();
        salesService.clearList();
        customerService.clearList();
    }
}

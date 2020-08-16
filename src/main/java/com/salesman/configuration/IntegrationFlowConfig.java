package com.salesman.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

import java.io.File;
import java.nio.file.Paths;

@Configuration
@EnableIntegration
public class IntegrationFlowConfig {

    private static Boolean AUTOCREATE_DIRECTORY = Boolean.TRUE;
    private static Boolean PREVENT_DUPLICATES = Boolean.TRUE;
    private static Boolean WATCH_SERVICE = Boolean.TRUE;

    @Bean
    IntegrationFlow integrationFlow() {
        return IntegrationFlows.from(createFlowBuiler(),
        spec -> spec.poller(Pollers.fixedRate(Long.parseLong("${timerate}"))))
                .filter(new SimplePatternFileListFilter("${file.in.matcher}"))
                .transform(Files.toStringTransformer())
               // .split(split-> split.delimiters(System.lineSeparator()))
                .transform()
                .get();

    }

    private FileInboundChannelAdapterSpec createFlowBuiler(){
        return  Files.inboundAdapter(getFileByPath())
                .useWatchService(WATCH_SERVICE)
                .autoCreateDirectory(AUTOCREATE_DIRECTORY)
                .preventDuplicates(PREVENT_DUPLICATES);
    }

    private File getFileByPath() {
        return Paths.get("${file.base.path}", "${file.in.folder}").toFile();
    }
}

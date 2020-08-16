package com.salesman.service;

import com.salesman.configuration.PropertiesConfig;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@Service
public class FileService {

    private static PropertiesConfig config;

    private static Log log;

    public FileService(PropertiesConfig config) {
        this.config = config;
        this.log = log;
    }

    public static void fileWatch() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path inputPath = Paths.get(config.getFileInputFolder());
        inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String filename = event.context().toString();
                if (".dat".equalsIgnoreCase(filename.substring(filename.length() - 4))) {
                    Path inputFilePath = inputPath.resolve((Path) event.context());
                    log.info("processando arquivo de ".concat(inputFilePath.toString()));
                }
            }
            key.reset();
        }
    }
}

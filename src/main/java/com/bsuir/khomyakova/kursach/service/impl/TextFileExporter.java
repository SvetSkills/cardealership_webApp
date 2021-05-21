package com.bsuir.khomyakova.kursach.service.impl;

import com.bsuir.khomyakova.kursach.service.FileExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextFileExporter implements FileExporter {
    private static final String EXPORT_DIRECTORY = "D:\\4СЕМ";

    private Logger logger = LoggerFactory.getLogger(TextFileExporter.class);

    @Override
    public Path export(String fileContent, String fileName) {
        Path filePath = Paths.get(EXPORT_DIRECTORY, fileName);
        try {
            Path exportedFilePath = Files.write(filePath, fileContent.getBytes(), StandardOpenOption.CREATE);
            return exportedFilePath;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}

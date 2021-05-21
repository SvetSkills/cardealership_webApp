package com.bsuir.khomyakova.kursach.service;

import java.nio.file.Path;

public interface FileExporter {
    public Path export(String fileContent, String fileName);
}

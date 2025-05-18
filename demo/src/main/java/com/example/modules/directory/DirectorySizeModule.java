package com.example.modules.directory;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class DirectorySizeModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Подсчёт общего размера всех файлов в каталоге";
    }

    @Override
    public void process(File file) throws IOException {
        long totalSize = Files.walk(file.toPath())
                .filter(Files::isRegularFile)
                .mapToLong(path -> path.toFile().length())
                .sum();
        System.out.println("Общий размер: " + totalSize + " байт");
    }
}

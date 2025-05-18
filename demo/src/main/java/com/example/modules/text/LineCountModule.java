package com.example.modules.text;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class LineCountModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчёт количества строк в текстовом файле";
    }

    @Override
    public void process(File file) throws IOException {
        long count = Files.lines(file.toPath()).count();
        System.out.println("Количество строк: " + count);
    }
}

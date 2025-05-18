package com.example.modules.text;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Component
public class WordCountModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчёт количества слов в текстовом файле";
    }

    @Override
    public void process(File file) throws IOException {
        long count = Files.lines(file.toPath())
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .filter(s -> !s.isBlank())
                .count();
        System.out.println("Количество слов: " + count);
    }
}

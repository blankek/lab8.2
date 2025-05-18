package com.example.modules.text;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CharFrequencyModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчёт частоты символов в текстовом файле";
    }

    @Override
    public void process(File file) throws IOException {
        Map<Character, Long> freq = Files.lines(file.toPath())
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        freq.forEach((c, count) -> System.out.println("'" + c + "': " + count));
    }
}

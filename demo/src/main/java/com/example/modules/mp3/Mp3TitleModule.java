package com.example.modules.mp3;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Mp3TitleModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Вывод названия трека из тегов";
    }

    @Override
    public void process(File file) throws IOException {
        Process process = new ProcessBuilder("ffprobe", "-v", "error", "-show_entries",
                "format_tags=title", "-of", "default=noprint_wrappers=1:nokey=0", file.getAbsolutePath())
                .redirectErrorStream(true).start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            if (line == null || line.isBlank()) {
                System.out.println("Тег 'title' не найден.");
            } else {
                System.out.println("Название трека: " + line);
            }
        }
    }
}


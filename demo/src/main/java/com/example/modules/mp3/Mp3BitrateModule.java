package com.example.modules.mp3;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Mp3BitrateModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Вывод битрейта MP3-файла";
    }

    @Override
    public void process(File file) throws IOException {
        Process process = new ProcessBuilder("ffprobe", "-v", "error", "-select_streams", "a:0",
                "-show_entries", "stream=bit_rate", "-of", "default=noprint_wrappers=1:nokey=1", file.getAbsolutePath())
                .redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            System.out.println("Битрейт: " + reader.readLine() + " бит/сек");
        }
    }
}
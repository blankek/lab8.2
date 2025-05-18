package com.example.modules.mp3;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Mp3DurationModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Вывод длительности трека";
    }

    @Override
    public void process(File file) throws IOException {
        Process process = new ProcessBuilder("ffprobe", "-v", "error", "-show_entries",
                "format=duration", "-of", "default=noprint_wrappers=1:nokey=1", file.getAbsolutePath())
                .redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            System.out.println("Длительность (сек): " + reader.readLine());
        }
    }
}

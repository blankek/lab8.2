package com.example.modules.image;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class ImageTypeModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().matches(".*\\.(jpg|jpeg|png|bmp)$");
    }

    @Override
    public String getDescription() {
        return "Определение типа изображения";
    }

    @Override
    public void process(File file) throws IOException {
        String mimeType = Files.probeContentType(file.toPath());
        System.out.println("Тип изображения: " + mimeType);
    }
}

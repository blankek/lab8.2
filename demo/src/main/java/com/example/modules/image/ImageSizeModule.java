package com.example.modules.image;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageSizeModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().matches(".*\\.(jpg|jpeg|png|bmp)$");
    }

    @Override
    public String getDescription() {
        return "Вывод размера изображения";
    }

    @Override
    public void process(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        System.out.println("Размер изображения: " + img.getWidth() + "x" + img.getHeight());
    }
}

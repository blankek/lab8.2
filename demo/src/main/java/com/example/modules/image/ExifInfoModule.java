package com.example.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ExifInfoModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.getName().matches(".*\\.(jpg|jpeg|png)$");
    }

    @Override
    public String getDescription() {
        return "Вывод информации EXIF";
    }

    @Override
    public void process(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }
}
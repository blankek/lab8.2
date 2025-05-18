package com.example.modules.directory;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class DirectoryListModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Вывод списка файлов в каталоге";
    }

    @Override
    public void process(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(f -> System.out.println(f.getName()));
        }
    }
}

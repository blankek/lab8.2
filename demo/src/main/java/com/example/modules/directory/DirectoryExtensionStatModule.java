package com.example.modules.directory;

import com.example.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DirectoryExtensionStatModule implements FileModule {
    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Подсчёт количества файлов по расширениям";
    }

    @Override
    public void process(File file) throws IOException {
        Map<String, Long> extCount = Files.walk(file.toPath())
                .filter(Files::isRegularFile)
                .map(path -> {
                    String name = path.getFileName().toString();
                    int dot = name.lastIndexOf('.');
                    return dot != -1 ? name.substring(dot + 1).toLowerCase() : "no_ext";
                })
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        extCount.forEach((ext, count) -> System.out.println(ext + ": " + count));
    }
}

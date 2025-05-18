package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private List<FileModule> modules;

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу или директории в аргументах.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + file.getAbsolutePath());
            return;
        }

        // Найдём подходящие модули
        List<FileModule> supportedModules = modules.stream()
                .filter(m -> m.supports(file))
                .toList();

        if (supportedModules.isEmpty()) {
            System.out.println("Нет модулей, поддерживающих этот файл.");
            return;
        }

        System.out.println("Выберите модуль для обработки:");
        for (int i = 0; i < supportedModules.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, supportedModules.get(i).getDescription());
        }

        System.out.print("Введите номер: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= supportedModules.size()) {
                try {
                    supportedModules.get(choice - 1).process(file);
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении модуля: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Неверный выбор.");
            }
        } else {
            System.out.println("Ожидался числовой ввод.");
        }
    }
}

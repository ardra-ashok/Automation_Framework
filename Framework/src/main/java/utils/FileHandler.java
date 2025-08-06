package utils;

import configs.CoreParams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileHandler {

    public static File getOrCreateFile(String fileName) throws IOException {
        Path filePath = Paths.get(CoreParams.FILES_DIR, fileName);
        if (Files.exists(filePath)) {
            validateFile(fileName);
            return filePath.toFile().getCanonicalFile();
        }

        Files.createDirectories(filePath.getParent());

//        String content = generateRandomText(20);
//        Files.write(filePath, content.getBytes());
          Files.createFile(filePath);

        System.out.println("📄 Created new file: " + filePath.toAbsolutePath());
        return filePath.toFile().getCanonicalFile();
    }

    public static void validateFile(String fileName) {
        Path filePath = Paths.get(CoreParams.FILES_DIR, fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            return;
        }

        if (!file.isFile()) {
            System.err.println("Not a valid file: " + filePath);
            return;
        }

        if (!file.canRead()) {
            System.err.println("File is not readable: " + filePath);
            return;
        }

        if (file.length() == 0) {
            System.err.println("File is empty: " + filePath);
            return;
        }

        System.out.println("File is valid: " + filePath.toAbsolutePath());
    }

    private static String generateRandomText(int wordCount) {
        String[] words = ("Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua").split(" ");
        Random rand = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            builder.append(words[rand.nextInt(words.length)]).append(" ");
        }
        return builder.toString().trim() + ".";
    }

}

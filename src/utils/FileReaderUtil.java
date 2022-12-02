package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderUtil {
    public static String readString(Object day) {
        try {
            String year = day.getClass().getName().split("\\.")[0];
            File currentDir = new File("");
            String projectPath = currentDir.getAbsolutePath();
            Path path = Path.of(
                    projectPath + "/src/" + year + "/days/" + day.getClass()
                            .getSimpleName() + "/Input.txt");
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

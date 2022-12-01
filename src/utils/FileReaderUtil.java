package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderUtil {
    public static String readString(Object day) throws IOException {
        String year = day.getClass().getName().split("\\.")[0];
        Path path = Path.of(
                "/Users/laris/Projects/advent-of-code/src/" + year + "/days/" + day.getClass()
                        .getSimpleName() + "/Input.txt");
        return Files.readString(path);
    }
}

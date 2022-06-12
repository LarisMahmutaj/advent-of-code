package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderUtil {
    public static String readString(Object day) throws IOException {
        Path path = Path.of("/Users/laris/Projects/advent-of-code/src/days/" + day.getClass().getSimpleName() + "/Input.txt");
        return Files.readString(path);
    }
}

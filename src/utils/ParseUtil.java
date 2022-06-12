package utils;

import java.util.Arrays;
import java.util.List;

public class ParseUtil {
    public static List<String> linesToStringArray(String input) {
        return Arrays.stream(input.split("\n")).toList();
    }
}

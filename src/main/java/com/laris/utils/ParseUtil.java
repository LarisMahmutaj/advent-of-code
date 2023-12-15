package com.laris.utils;

import java.util.Arrays;
import java.util.List;

public class ParseUtil {
    public static List<String> linesToStringList(String input) {
        return Arrays.stream(input.split("\n")).toList();
    }
}

package com.laris.Y2023.days.Day01;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Day01 implements Day {
    private final String input = FileReaderUtil.readString(this);
    private final Map<String, Integer> numbersMap = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    @Override
    public void partOne() {
        List<String> lines = ParseUtil.linesToStringList(input);
        List<Integer> numbers = new ArrayList<>();

        for (String line : lines) {
            int firstNumber = 0;
            int secondNumber = 0;

            List<String> array = new ArrayList<>(List.of(line.split("")));

            for (String character : array) {
                try {
                    firstNumber = Integer.parseInt(character);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            }

            Collections.reverse(array);
            for (String character : array) {
                try {
                    secondNumber = Integer.parseInt(character);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            }

            int sl = Integer.parseInt(firstNumber + String.valueOf(secondNumber));

            numbers.add(sl);
        }

        int result = numbers.stream().mapToInt(Integer::intValue).sum();

        System.out.println(result);
    }

    @Override
    public void partTwo() {
        List<String> lines = ParseUtil.linesToStringList(input);
        int result = 0;
        for (String line : lines) {
            int smallestIndex = line.length() - 1;
            int biggestIndex = 0;

            int firstNumber = 0;
            int lastNumber = 0;

            for (String key : numbersMap.keySet()) {
                if (line.contains(key) && line.indexOf(key) <= smallestIndex) {
                    smallestIndex = line.indexOf(key);
                    firstNumber = numbersMap.get(key);
                }

                if (line.contains(String.valueOf(numbersMap.get(key)))
                        && line.indexOf(String.valueOf(numbersMap.get(key))) <= smallestIndex) {
                    smallestIndex = line.indexOf(String.valueOf(numbersMap.get(key)));
                    firstNumber = numbersMap.get(key);
                }
            }

            for (String key : numbersMap.keySet()) {
                if (line.contains(key) && line.lastIndexOf(key) >= biggestIndex) {
                    biggestIndex = line.lastIndexOf(key);
                    lastNumber = numbersMap.get(key);
                }

                if (line.contains(String.valueOf(numbersMap.get(key)))
                        && line.lastIndexOf(String.valueOf(numbersMap.get(key))) >= biggestIndex) {
                    biggestIndex = line.lastIndexOf(String.valueOf(numbersMap.get(key)));
                    lastNumber = numbersMap.get(key);
                }
            }
            result += Integer.parseInt(firstNumber + String.valueOf(lastNumber));
        }
        System.out.println(result);
    }
}

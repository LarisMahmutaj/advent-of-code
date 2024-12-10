package com.laris.Y2024.days.Day02;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day02 implements Day {
    private int countSafeLines = 0;

    @Override
    public void partOne() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));

        for (String line : lines) {

            List<Integer> numbers = new ArrayList<>();
            for (String s : line.split(" ")) {
                numbers.add(Integer.parseInt(s));
            }

            boolean increasing = (numbers.get(1) - numbers.get(0)) > 0;

            if (!increasing) {
                Collections.reverse(numbers);
            }
            boolean isSafe = isSafe(numbers);
            if (isSafe) countSafeLines++;

        }

        System.out.println(countSafeLines);
    }

    @Override
    public void partTwo() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));

        for (String line : lines) {

            List<Integer> numbers = new ArrayList<>();
            for (String s : line.split(" ")) {
                numbers.add(Integer.parseInt(s));
            }

            if (!checkSafety(numbers)) {
                Collections.reverse(numbers);
                if (!checkSafety(numbers)) {
                    continue;
                }
            }
            ;
        }

        System.out.println(countSafeLines);
    }

    private boolean checkSafety(List<Integer> numbers) {
        boolean isSafe = isSafe(numbers);

        if (isSafe) {
            countSafeLines++;
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                List<Integer> newNumbers = new ArrayList<>(numbers);
                newNumbers.remove(i);
                isSafe = isSafe(newNumbers);
                if (isSafe) {
                    countSafeLines++;
                    break;
                }
            }
        }
        return isSafe;
    }

    private boolean isSafe(List<Integer> numbers) {
        boolean isSafe = true;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int j = i + 1;

            if (numbers.get(j) <= numbers.get(i)) {
                isSafe = false;
                break;
            }
            if (Math.abs(numbers.get(i) - numbers.get(j)) > 3 || Math.abs(numbers.get(i) - numbers.get(j)) == 0) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

}

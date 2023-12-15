package com.laris.Y2021.days.Day01;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;

import java.util.Arrays;
import java.util.List;

public class Day01 implements Day {
    private List<Integer> getValues() {
        List<Integer> values;
        String inputString = FileReaderUtil.readString(this);
        values = Arrays.stream(inputString.split("\n")).map(Integer::parseInt).toList();
        return values;
    }

    @Override
    public void partOne() {
        int count = 0;
        List<Integer> values = getValues();

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) > values.get(i - 1)) count++;
        }

        System.out.println("Result: " + count);
    }

    @Override
    public void partTwo() {
        int count = 0;
        List<Integer> values = getValues();

        for (int i = 1; i <= values.size() - 3; i++) {
            int currentSum = values.get(i) + values.get(i + 1) + values.get(i + 2);
            int j = i - 1;
            int previousSum = values.get(j) + values.get(j + 1) + values.get(j + 2);
            if (currentSum > previousSum) count++;
        }

        System.out.printf("Result: " + count);
    }
}

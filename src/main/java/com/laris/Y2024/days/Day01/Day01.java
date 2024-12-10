package com.laris.Y2024.days.Day01;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day01 implements Day {

    @Override
    public void partOne() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        List<Integer> leftCol = new ArrayList<>();
        List<Integer> rightCol = new ArrayList<>();
        List<Integer> diffs = new ArrayList<>();


        lines.forEach(line -> {
            leftCol.add(Integer.parseInt(line.replaceAll(" +", " ").split(" ")[0]));
            rightCol.add(Integer.parseInt(line.replaceAll(" +", " ").split(" ")[1]));
        });

        Collections.sort(leftCol);
        Collections.sort(rightCol);

        for (int i = 0; i < leftCol.size(); i++) {
            int diff = Math.abs(leftCol.get(i) - rightCol.get(i));
            diffs.add(diff);
        }

        int sum = diffs.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum);
    }

    @Override
    public void partTwo() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        List<Integer> leftCol = new ArrayList<>();
        List<Integer> rightCol = new ArrayList<>();


        lines.forEach(line -> {
            leftCol.add(Integer.parseInt(line.replaceAll(" +", " ").split(" ")[0]));
            rightCol.add(Integer.parseInt(line.replaceAll(" +", " ").split(" ")[1]));
        });
        int total = 0;
        for (Integer item : leftCol) {
            int count = (int) rightCol.stream().filter(x -> Objects.equals(x, item)).count();
            total += item * count;
        }

        System.out.println(total);
    }
}

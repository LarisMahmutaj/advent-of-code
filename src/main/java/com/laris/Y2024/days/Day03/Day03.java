package com.laris.Y2024.days.Day03;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {
    private int total = 0;

    @Override
    public void partOne() {
        String input = FileReaderUtil.readString(this);
        Matcher mulMatcher = Pattern.compile("mul[(]\\d{1,3},\\d{1,3}[)]").matcher(input);

        List<String> instructions = mulMatcher.results().map(MatchResult::group).toList();
        for (String instruction : instructions) {
            String valuesString = instruction.replace("mul(", "").replace(")", "");
            String[] values = valuesString.split(",");

            int x = Integer.parseInt(values[0]);
            int y = Integer.parseInt(values[1]);
            int mul = x * y;

            total += mul;
        }
        System.out.println(total);
    }

    @Override
    public void partTwo() {
        boolean enabled = true;

        String input = FileReaderUtil.readString(this);

        Matcher matcher = Pattern.compile("mul[(]\\d{1,3},\\d{1,3}[)]|don't[(][)]|do[(][)]").matcher(input);

        List<String> instructions = matcher.results().map(MatchResult::group).toList();

        for (String instruction : instructions) {
            String function = instruction.split("[(]")[0];
            if (function.equals("do")) {
                enabled = true;
            } else if (function.equals("don't")) {
                enabled = false;
            } else if (enabled) {
                String valuesString = instruction.replace("mul(", "").replace(")", "");
                String[] values = valuesString.split(",");

                int x = Integer.parseInt(values[0]);
                int y = Integer.parseInt(values[1]);
                int mul = x * y;

                total += mul;
            }
        }
        System.out.println(total);
    }
}

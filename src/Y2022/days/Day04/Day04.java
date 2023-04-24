package Y2022.days.Day04;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 implements Day {
    @Override
    public void partOne() {
        calculate(1);
    }

    @Override
    public void partTwo() {
        calculate(2);
    }

    private void calculate(int part) {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        int count = 0;
        for (String line : lines) {
            String[] pairs = line.split(",");

            List<Integer> firstPair = getPairList(pairs[0]);
            List<Integer> secondPair = getPairList(pairs[1]);
            if (part == 1) {
                if (contains(firstPair, secondPair) || contains(secondPair, firstPair)) count++;
            } else {
                if (overlaps(firstPair, secondPair) || overlaps(secondPair, firstPair)) count++;
            }
        }

        System.out.println(count);
    }

    private boolean contains(List<Integer> firstPair, List<Integer> secondPair) {
        return (firstPair.get(0) <= secondPair.get(0) && firstPair.get(1) >= secondPair.get(1));
    }

    private boolean overlaps(List<Integer> firstPair, List<Integer> secondPair) {
        return (firstPair.get(0) >= secondPair.get(0) && firstPair.get(0) <= secondPair.get(1)
                || firstPair.get(1) >= secondPair.get(0) && firstPair.get(1) <= secondPair.get(1));
    }

    private List<Integer> getPairList(String pair) {
        return Arrays.stream(pair.split("-")).map(Integer::parseInt).collect(Collectors.toList());
    }
}


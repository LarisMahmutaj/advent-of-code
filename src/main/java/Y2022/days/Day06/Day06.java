package Y2022.days.Day06;

import interfaces.Day;
import utils.FileReaderUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 implements Day {
    @Override
    public void partOne() {
        execute(4);
    }

    @Override
    public void partTwo() {
        execute(14);
    }

    private void execute(int distinctCharacterNumber) {
        String input = FileReaderUtil.readString(this);

        int marker = 0;
        for (int i = 0; i < input.length() - distinctCharacterNumber; i++) {
            String sub = input.substring(i, i + distinctCharacterNumber);

            List<String> chars = Arrays.stream(sub.split("")).toList();
            chars = chars.stream().distinct().collect(Collectors.toList());
            if (chars.size() == distinctCharacterNumber) {
                marker = i + distinctCharacterNumber;
                break;
            }
        }

        System.out.println(marker);
    }
}

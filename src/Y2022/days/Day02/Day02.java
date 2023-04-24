package Y2022.days.Day02;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.List;

public class Day02 implements Day {
    @Override
    public void partOne() {

        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        int totalPoints = 0;
        for (String line : lines) {
            String[] signs = line.split(" ");

            if (signs[0].equals("A")) {
                if (signs[1].equals("Z")) totalPoints += 3;
                else if (signs[1].equals("Y")) totalPoints += 8;
                else totalPoints += 4;
            } else if (signs[0].equals("B")) {
                if (signs[1].equals("X")) totalPoints += 1;
                else if (signs[1].equals("Z")) totalPoints += 9;
                else totalPoints += 5;
            } else {
                if (signs[1].equals("X")) totalPoints += 7;
                else if (signs[1].equals("Y")) totalPoints += 2;
                else totalPoints += 6;
            }
        }

        System.out.println(totalPoints);
    }

    @Override
    public void partTwo() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        int totalPoints = 0;
        for (String line : lines) {
            String[] signs = line.split(" ");

            if (signs[0].equals("A")) {
                if (signs[1].equals("Z")) totalPoints += 8;
                else if (signs[1].equals("Y")) totalPoints += 4;
                else totalPoints += 3;
            } else if (signs[0].equals("B")) {
                if (signs[1].equals("Z")) totalPoints += 9;
                else if (signs[1].equals("Y")) totalPoints += 5;
                else totalPoints += 1;
            } else {
                if (signs[1].equals("Z")) totalPoints += 7;
                else if (signs[1].equals("Y")) totalPoints += 6;
                else totalPoints += 2;
            }
        }

        System.out.println(totalPoints);
    }
}

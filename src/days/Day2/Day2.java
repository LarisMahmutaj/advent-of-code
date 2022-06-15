package days.Day2;

import utils.FileReaderUtil;
import utils.ParseUtil;
import interfaces.Day;

import java.io.IOException;
import java.util.List;

public class Day2 implements Day {
    private int xAxis = 0;
    private int yAxis = 0;
    private int aim = 0;
    private int finalPosition = 0;
    private List<String> instructions;
    @Override
    public void partOne() {
        try {
            String input = FileReaderUtil.readString(this);
            List<String> instructions = ParseUtil.linesToStringArray(input);

            for (String instruction : instructions) {
                String[] instructionParts = instruction.split(" ");
                String direction = instructionParts[0];
                int units = Integer.parseInt(instructionParts[1]);

                if (direction.equals(Direction.FORWARD.getValue())) {
                    xAxis += units;
                } else if (direction.equals(Direction.DOWN.getValue())) {
                    yAxis += units;
                } else if (direction.equals(Direction.UP.getValue())) {
                    yAxis -= units;
                } else {
                    System.out.println("ERROR: Invalid direction value");
                }
            }

            finalPosition = xAxis * yAxis;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Result: " + finalPosition);
    }

    @Override
    public void partTwo() {
        try{
            String input = FileReaderUtil.readString(this);
            List<String> instructions = ParseUtil.linesToStringArray(input);

            for (String instruction : instructions) {
                String[] instructionParts = instruction.split(" ");
                String direction = instructionParts[0];
                int units = Integer.parseInt(instructionParts[1]);

                if (direction.equals(Direction.FORWARD.getValue())) {
                    xAxis += units;
                    yAxis += aim * units;
                } else if (direction.equals(Direction.DOWN.getValue())) {
                    aim += units;
                } else if (direction.equals(Direction.UP.getValue())) {
                    aim -= units;
                } else {
                    System.out.println("ERROR: Invalid direction value");
                }
            }

            finalPosition = xAxis * yAxis;
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Result: " + finalPosition);
    }
}

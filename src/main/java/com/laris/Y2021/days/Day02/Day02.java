package com.laris.Y2021.days.Day02;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.List;

public class Day02 implements Day {
    private int xAxis = 0;
    private int yAxis = 0;
    private int aim = 0;
    private int finalPosition = 0;
    private List<String> instructions;

    @Override
    public void partOne() {
        String input = FileReaderUtil.readString(this);
        this.instructions = ParseUtil.linesToStringList(input);

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

        System.out.println("Result: " + finalPosition);
    }

    @Override
    public void partTwo() {
        String input = FileReaderUtil.readString(this);
        this.instructions = ParseUtil.linesToStringList(input);

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
        System.out.println("Result: " + finalPosition);
    }
}

package Y2022.days.Day10;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.List;

public class Day10 implements Day {
    private int x;
    private int total;
    private int countdown;
    private int instructionDuration;
    private int programCounter;
    private final List<String> instructions;
    private final StringBuilder output;

    public Day10() {
        x = 1;
        instructions = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        programCounter = 0;
        output = new StringBuilder();
        instructionDuration = 0;
    }

    @Override
    public void partOne() {
        this.countdown = 20;
        logic();
    }

    @Override
    public void partTwo() {
        this.countdown = 40;
        logic();
    }

    private void logic() {
        for (int cycle = 0, lineIndex = 0; true; cycle++, lineIndex++, countdown--, instructionDuration++) {
            if (countdown == 0) {
                total += cycle * x;
                countdown = 40;
                output.append("\n");
                lineIndex = 0;
            }

            String[] instructionParts;

            try {
                instructionParts = instructions.get(programCounter).split(" ");
            } catch (IndexOutOfBoundsException e) {
                break;
            }

            Instruction instruction = Instruction.valueOf(instructionParts[0]);

            switch (instruction) {
                case noop -> {
                    if (instructionDuration == 1) {
                        this.programCounter++;
                        this.instructionDuration = 0;
                    }
                }
                case addx -> {
                    if (instructionDuration == 2) {
                        int v = Integer.parseInt(instructionParts[1]);
                        this.x += v;
                        this.instructionDuration = 0;
                        this.programCounter++;
                    }
                }
            }

            if (Math.abs(x - lineIndex) <= 1) {
                output.append("#");
            } else {
                //Replaced . with white space for better readability
                output.append(" ");
            }
        }
        System.out.println(output);

        System.out.println(total);
    }
}

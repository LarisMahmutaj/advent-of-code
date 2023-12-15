package com.laris.Y2022.days.Day11;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Day11 implements Day {
    private final List<Monkey> monkeys;
    private long modAll;

    public Day11() {
        monkeys = new ArrayList<>();
        modAll = 1;
        parseMonkeys();
    }

    @Override
    public void partOne() {
        logic(20, 1);
    }

    @Override
    public void partTwo() {
        logic(10000, 2);
    }

    private void logic(int roundNumber, int part) {
        for (int i = 0; i < roundNumber; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getItems().isEmpty()) {
                    runOperation(monkey, part);
                }
            }
        }

        // Sort descending
        monkeys.sort((a, b) -> {
            if (a.getInspectedItems() < b.getInspectedItems()) {
                return 1;
            } else if (a.getInspectedItems() > b.getInspectedItems()) {
                return -1;
            }
            return 0;
        });

        long result = (long) monkeys.get(0).getInspectedItems() * monkeys.get(1).getInspectedItems();
        System.out.println(result);
    }

    public void runOperation(Monkey monkey, int part) {
        long currentItem = monkey.getItems().remove();
        long worryLevel = currentItem;

        String[] operationParts = monkey.getOperation().split(" ");

        long firstOperand;
        long secondOperand;

        if (operationParts[0].equals("old")) {
            firstOperand = currentItem;
        } else {
            firstOperand = Long.parseLong(operationParts[0]);
        }
        if (operationParts[2].equals("old")) {
            secondOperand = currentItem;
        } else {
            secondOperand = Long.parseLong(operationParts[2]);
        }

        char operator = operationParts[1].charAt(0);

        switch (operator) {
            case '+' -> worryLevel = firstOperand + secondOperand;
            case '*' -> worryLevel = firstOperand * secondOperand;
        }

        if (part == 1) {
            worryLevel = (long) Math.floor((double) worryLevel / 3);
        } else {
            worryLevel = worryLevel % modAll;
        }

        int throwTo;

        if (worryLevel % monkey.getDivisibleBy() == 0) {
            throwTo = monkey.getMonkeyIfTrue();
        } else {
            throwTo = monkey.getMonkeyIfFalse();
        }

        Optional<Monkey> optionalMonkey = this.monkeys.stream().filter(x -> x.getId() == throwTo).findFirst();

        long finalWorryLevel = worryLevel;

        optionalMonkey.ifPresent(value -> value.getItems().offer(finalWorryLevel));
        monkey.increaseInspectedItems();
    }

    private void parseMonkeys() {
        List<String> groups = List.of(FileReaderUtil.readString(this).split("\n\n"));
        for (String group : groups) {
            List<String> lines = List.of(group.split("\n"));

            // Create Monkey and set ID
            Monkey monkey = new Monkey();
            monkey.setId(Character.getNumericValue(lines.get(0).split(" ")[1].charAt(0)));

            // Set starting items
            List<Long> items = Stream.of(lines.get(1).split(": ")[1].split(", "))
                    .map(Long::parseLong)
                    .toList();
            monkey.getItems().addAll(items);

            // Set operation
            monkey.setOperation(lines.get(2).split(" = ")[1]);

            // Set test data
            monkey.setDivisibleBy(Integer.parseInt(lines.get(3).split("by ")[1]));
            monkey.setMonkeyIfTrue(Integer.parseInt(lines.get(4).split("monkey ")[1]));
            monkey.setMonkeyIfFalse(Integer.parseInt(lines.get(5).split("monkey ")[1]));

            // This will be used in part 2
            this.modAll *= monkey.getDivisibleBy();

            monkeys.add(monkey);
        }
    }

}

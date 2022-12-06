package Y2022.days.Day5;

import interfaces.Day;
import utils.FileReaderUtil;

import java.util.*;

public class Day5 implements Day {
    SortedMap<Integer, Stack<Character>> stacks = new TreeMap<>();
    Map<Integer, Integer> indexMap = new HashMap<>();
    
    @Override
    public void partOne() {
        calculate(1);
    }
    
    @Override
    public void partTwo() {
    calculate(2);
    }
    
    private void calculate(int part){
        String input = FileReaderUtil.readString(this);

        List<String> groups = Arrays.stream(input.split("\n\n")).toList();
        List<String> moves = Arrays.stream(groups.get(1).split("\n")).toList();

        createStacks(groups.get(0));
        if(part == 1){
            moves.forEach(this::executeMove);
        } else {
            moves.forEach(this::executeMovesPart2);
        }
        StringBuilder stringBuilder = new StringBuilder();
        this.stacks.values().forEach(stack -> {
            stringBuilder.append(stack.pop());
        });

        System.out.println(stringBuilder);
    }
    
    private void executeMovesPart2(String move) {
        String[] moveParts = move.split(" ");
        int moveCount = Integer.parseInt(moveParts[1]);

        int from = Integer.parseInt(moveParts[3]);
        int to = Integer.parseInt(moveParts[5]);
        List<Character> characters = new ArrayList<>();
        
        for (int i = 0; i < moveCount; i++) {
            Character character = this.stacks.get(indexMap.get(from)).pop();
            characters.add(character);
        }
        Collections.reverse(characters);
        
        characters.forEach(character -> {
            this.stacks.get(indexMap.get(to)).push(character);          
        });
    }
    
    private void executeMove(String move) {
        String[] moveParts = move.split(" ");
        int moveCount = Integer.parseInt(moveParts[1]);
        
        int from = Integer.parseInt(moveParts[3]);
        int to = Integer.parseInt(moveParts[5]);
        for (int i = 0; i < moveCount; i++) {
            Character character = this.stacks.get(indexMap.get(from)).pop();
            this.stacks.get(indexMap.get(to)).push(character);
        }
    }
    
    private void createStacks(String stacksGroup) {
        List<String> stackLines = Arrays.stream(stacksGroup.split("\n")).toList();

        int stackStartIndex = stackLines.size() - 1;
        for (int i = stackStartIndex; i >= 0; i--) {
            String line = stackLines.get(i);

            for (int j = -1; j < line.length() - 2; j += 4) {
                String trio = (j == line.length() - 3) ? (line.substring(j + 1)) : (line.substring(j + 1, j + 4)).strip();
                
                if(trio.length() == 0) {
                    continue;
                }
                
                char character = trio.replaceAll("[\\[\\]]+", "").charAt(0);

                int stackIndex = j+2;

                if (character != ' ') {
                    Stack<Character> stack;
                    if(!stacks.containsKey(stackIndex)){
                        stack = new Stack<>();
                    } else {
                        stack = this.stacks.get(stackIndex);
                    }
                    if(Character.isDigit(character)) {
                        this.indexMap.put(Character.getNumericValue(character), stackIndex);
                    }
                    this.stacks.put(stackIndex, stack);
                    stacks.get(stackIndex).push(character);
                }
            }
        }
    }
}

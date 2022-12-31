package Y2022.days.Day9;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class Day9 implements Day {
    List<String> commands;
    private int knotCount;
    Rope rope;
    List<Coordinate> visitedLocations;
    
    public Day9() {
        commands = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        visitedLocations = new ArrayList<>();
    }

    @Override
    public void partOne() {
        rope = new Rope(1);
        this.move();
    }

    @Override
    public void partTwo() {
        rope = new Rope(9);
        this.move();
    }
    
    private void move() {
        for (String command : commands) {
            String[] commandParts = command.split(" ");
            String direction = commandParts[0];
            int count = Integer.parseInt(commandParts[1]);

            for (int j = 0; j < count; j++) {
                this.rope.move(direction);

                if (rope.getCurrentKnot().getChildKnot() == null) {
                    Coordinate c = new Coordinate(rope.getCurrentKnot().getLocation());
                    this.visitedLocations.add(c);
                }
                this.rope.setCurrentKnot(this.rope.getHead());
            }
        }
        List<Coordinate> distinctLocations = visitedLocations.stream().distinct().toList();
        System.out.println(distinctLocations.size());
        
    }
    
}

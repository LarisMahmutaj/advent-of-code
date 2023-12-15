package com.laris.Y2023.days.Day02;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;

    private List<Subset> subsets = new ArrayList<>();

    public Game(String input) {
        String[] parts = input.split(": ");
        this.id = Integer.parseInt(parts[0].split(" ")[1]);

        String[] subsetsArray = parts[1].split("; ");


        for (String subsetString : subsetsArray) {
            String[] subsetParts = subsetString.split(", ");
            List<Cube> cubes = new ArrayList<>();
            for (String cubeDetails : subsetParts) {
                String[] cubeParts = cubeDetails.split(" ");

                Cube newCube = new Cube();
                newCube.setCount(Integer.parseInt(cubeParts[0]));
                newCube.setColor(cubeParts[1]);
                if (cubes.stream().anyMatch(cube -> cube.getColor().equals(newCube.getColor()))) {
                    cubes.stream().filter(cube -> cube.getColor().equals(newCube.getColor())).findFirst().ifPresent(cube -> {
                        cube.setCount(cube.getCount() + newCube.getCount());
                    });
                } else {
                    cubes.add(newCube);
                }
            }

            subsets.add(new Subset(cubes));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Subset> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<Subset> subsets) {
        this.subsets = subsets;
    }

}

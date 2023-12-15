package com.laris.Y2022.days.Day14;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day14 implements Day {
    private final List<Point> rocks = new ArrayList<>();
    private final List<Point> sand = new ArrayList<>();
    private final List<Point> filledPoints = new ArrayList<>();
    private boolean part2 = false;
    private int floorY;
    private int floorXMin;
    private int floorXMax;

    @Override
    public void partOne() {
        part2 = false;
        calculate();
    }

    @Override
    public void partTwo() {
        part2 = true;
        calculate();
    }

    private void calculate() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        // Fill Rocks
        for (String line : lines) {
            List<String> coordinates = List.of(line.split(" -> "));
            Point prevPoint = null;
            for (String coordinateString : coordinates) {
                String[] coordinateParts = coordinateString.split(",");
                Point currPoint = new Point(Integer.parseInt(coordinateParts[0]), Integer.parseInt(coordinateParts[1]));
                if (prevPoint == null) {
                    prevPoint = currPoint;
                    continue;
                }

                if (prevPoint.getX() == currPoint.getX()) {
                    int length = Math.abs(prevPoint.getY() - currPoint.getY()) + 1;
                    Point start;
                    Point end;
                    if (prevPoint.getY() < currPoint.getY()) {
                        start = prevPoint;
                        end = currPoint;
                    } else {
                        start = currPoint;
                        end = prevPoint;
                    }
                    putRocks(start, end, length, 'Y');
                } else {
                    int length = Math.abs(prevPoint.getX() - currPoint.getX()) + 1;
                    Point start;
                    Point end;
                    if (prevPoint.getX() < currPoint.getX()) {
                        start = prevPoint;
                        end = currPoint;
                    } else {
                        start = currPoint;
                        end = prevPoint;
                    }
                    putRocks(start, end, length, 'X');
                }
                prevPoint = currPoint;
            }
        }
        int lowestRock = rocks.stream().mapToInt(Point::getY).max().getAsInt();
        if (part2) {
            floorY = lowestRock + 2;
            floorXMax = rocks.stream().mapToInt(Point::getX).max().getAsInt() + 3;
            floorXMin = rocks.stream().mapToInt(Point::getX).min().getAsInt() - 3;

            for (int i = floorXMin; i <= floorXMax; i++) {
                Point p = new Point(i, floorY);
                rocks.add(p);
                filledPoints.add(p);
            }
        }

        visualize();

        //Drop sand
        while (true) {
            int x = 500;
            Point currentPoint;

            for (int i = 0; true; i++) {
                currentPoint = new Point(x, i);

                if (!part2 && currentPoint.getY() > lowestRock) {
                    break;
                }

                Point nextPoint = new Point(x, i + 1);
                if (!filledPoints.contains(nextPoint)) {
                    continue;
                }
                nextPoint.setY(currentPoint.getY());

                nextPoint.setX(nextPoint.getX() - 1);
                nextPoint.setY(currentPoint.getY() + 1);
                if (!filledPoints.contains(nextPoint)) {
                    x--;
                    continue;
                }

                nextPoint = new Point(x, i);

                nextPoint.setX(nextPoint.getX() + 1);
                nextPoint.setY(currentPoint.getY() + 1);
                if (!filledPoints.contains(nextPoint)) {
                    x++;
                    continue;
                }
                filledPoints.add(currentPoint);
                sand.add(currentPoint);

                break;
            }

            if (!part2) {
                if (currentPoint.getY() > lowestRock) {
                    break;
                }
            } else {
                if (floorY - currentPoint.getY() == 1) {
                    widenFloor();
                }
                if (filledPoints.contains(new Point(500, 0))) {
                    break;
                }
            }

        }
        System.out.println(sand.size());
        visualize();
    }

    private void widenFloor() {
        this.floorXMin--;
        this.floorXMax++;
        Point p1 = new Point(floorXMin, floorY);
        Point p2 = new Point(floorXMax, floorY);

        rocks.addAll(List.of(p1, p2));
        filledPoints.addAll(List.of(p1, p2));
    }

    private void visualize() {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        Path path = Path.of(
                projectPath + "/src/main/java/Y2022/days/Day14/Grid.txt");
        File file = new File(path.toUri());

        try (FileWriter fw = new FileWriter(file)) {
            fw.write("");
            int minX = filledPoints.stream().mapToInt(Point::getX).min().getAsInt();
            int maxX = filledPoints.stream().mapToInt(Point::getX).max().getAsInt();
            int maxY = filledPoints.stream().mapToInt(Point::getY).max().getAsInt();

            for (int i = 0; i <= maxY; i++) {
                for (int j = minX; j <= maxX; j++) {
                    Point currPoint = new Point(j, i);
                    if (j == 500 && i == 0) {
                        fw.write("+");
                    } else if (filledPoints.contains(currPoint)) {
                        if (rocks.contains(currPoint)) {
                            fw.write("#");
                        } else {
                            fw.write("o");
                        }
                    } else {
                        fw.write(".");
                    }

                }
                fw.write("\n");
            }
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void putRocks(Point start, Point end, int length, char axis) {
        for (int i = 0; i < length; i++) {
            if (axis == 'Y') {
                Point p = new Point(start.getX(), start.getY() + i);
                if (!filledPoints.contains(p)) {
                    filledPoints.add(p);
                    rocks.add(p);
                }
            }
            if (axis == 'X') {
                Point p = new Point(start.getX() + i, start.getY());
                if (!filledPoints.contains(p)) {
                    filledPoints.add(p);
                    rocks.add(p);
                }
            }
        }
    }
}

package Y2021.days.Day05;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 implements Day {
    private final List<Point> allPoints = new ArrayList<>();
    private final List<Point> distinctPoints = new ArrayList<>();
    private boolean includeDiagonal = false;

    @Override
    public void partOne() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        for (String line : lines) {
            List<String> coordinates = Arrays.stream(line.split("->")).map(String::strip).toList();
            allPoints.addAll(addPoints(coordinates));
        }

        allPoints.forEach(point -> {
            long count = allPoints.stream().filter(p -> p.equals(point)).count();
            point.setCount(count);
            if (!distinctPoints.contains(point)) {
                distinctPoints.add(point);
            }
        });

        long intersectionCount = distinctPoints.stream().filter(p -> p != null && p.getCount() > 1).count();

        System.out.println(intersectionCount);
    }

    @Override
    public void partTwo() {
        this.includeDiagonal = true;
        partOne();
    }

    private List<Point> addPoints(List<String> line) {
        List<Point> points = new ArrayList<>();
        int lineLength = 0;
        String c1 = line.get(0);
        String c2 = line.get(1);
        Point start;
        Point end;

        Point p1 = new Point(
                Integer.parseInt(c1.split(",")[0]),
                Integer.parseInt(c1.split(",")[1]), 1);

        Point p2 = new Point(
                Integer.parseInt(c2.split(",")[0]),
                Integer.parseInt(c2.split(",")[1]), 1);

        Direction direction = getLineDirection(p1, p2);

        switch (direction) {
            case HORIZONTAL -> {
                if (p1.getY() <= p2.getY()) {
                    start = p1;
                    end = p2;
                } else {
                    start = p2;
                    end = p1;
                }

                points.add(p1);
                points.add(p2);

                lineLength = (end.getY() - start.getY());

                for (int i = 1; i < lineLength; i++) {
                    Point point = new Point(start.getX(), start.getY() + i, 1);
                    points.add(point);
                }

            }
            case VERTICAL -> {
                if (p1.getX() <= p2.getX()) {
                    start = p1;
                    end = p2;
                } else {
                    start = p2;
                    end = p1;
                }

                points.add(p1);
                points.add(p2);

                lineLength = (end.getX() - start.getX());

                for (int i = 1; i < lineLength; i++) {
                    Point point = new Point(start.getX() + i, start.getY(), 1);
                    points.add(point);
                }
            }
            case DIAGONAL -> {
                if (!includeDiagonal) {
                    break;
                }
                start = p1;
                end = p2;

                points.add(p1);
                points.add(p2);

                lineLength = Math.abs(p1.getX() - p2.getX());

                for (int i = 1; i < lineLength; i++) {
                    Point point;
                    int xValue;
                    int yValue;

                    if (start.getX() < end.getX()) {
                        xValue = start.getX() + i;
                    } else {
                        xValue = start.getX() - i;
                    }

                    if (start.getY() < end.getY()) {
                        yValue = start.getY() + i;
                    } else {
                        yValue = start.getY() - i;
                    }

                    point = new Point(xValue, yValue, 1);
                    points.add(point);
                }
            }
        }

        return points;
    }

    private Direction getLineDirection(Point start, Point end) {
        if (start.getX() == end.getX()) {
            return Direction.HORIZONTAL;
        }
        if (start.getY() == end.getY()) {
            return Direction.VERTICAL;
        }
        return Direction.DIAGONAL;
    }
}

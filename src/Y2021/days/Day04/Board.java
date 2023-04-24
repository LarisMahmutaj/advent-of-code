package Y2021.days.Day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final List<Line> lines;

    public Board() {
        this.lines = new ArrayList<>();
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Integer> getAllNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (Line line : this.lines) {
            numbers.addAll(line.getNumbers());
        }

        return numbers.stream().distinct().collect(Collectors.toList());
    }

    static class Line {

        private List<Integer> numbers;

        public Line() {
            this.numbers = new ArrayList<>();
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }

    }

    public void parseLines(String boardString) {
        String[] lines = boardString.split("\n");

        List<Line> rows = new ArrayList<>();
        for (String line : lines) {
            Line row = new Line();
            String trimmedLine = line.replace("  ", " ").strip();
            List<Integer> numbers = Arrays.stream(trimmedLine.split(" "))
                    .map(Integer::parseInt)
                    .toList();
            row.setNumbers(numbers);

            rows.add(row);
        }
        this.lines.addAll(rows);

        List<Line> columns = new ArrayList<>();
        for (int i = 0; i < rows.get(0).numbers.size(); i++) {
            Line column = new Line();
            for (Line row : rows) {
                column.numbers.add(row.numbers.get(i));
            }
            columns.add(column);
        }

        this.lines.addAll(columns);
    }
}

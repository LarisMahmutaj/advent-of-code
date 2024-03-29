package com.laris.Y2021.days.Day04;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day04 implements Day {
    private List<Integer> drawnNumbers = new ArrayList<>();
    private List<Integer> numbersToDraw = new ArrayList<>();

    @Override
    public void partOne() {
        List<Board> boards = parseBoards();

        for (Integer drawnNUmber : numbersToDraw) {
            drawnNumbers.add(drawnNUmber);

            for (Board board : boards) {
                for (Board.Line line : board.getLines()) {
                    if (new HashSet<>(drawnNumbers).containsAll(line.getNumbers())) {
                        int result = calculateBoardResult(board, drawnNUmber);

                        System.out.println("Result: " + result);
                        return;
                    }
                }
            }

        }
    }

    @Override
    public void partTwo() {
        List<Board> boards = parseBoards();
        int totalBoards = boards.size();
        List<Board> winnerBoards = new ArrayList<>();

        for (Integer drawnNUmber : numbersToDraw) {
            drawnNumbers.add(drawnNUmber);
            boards.removeAll(winnerBoards);
            for (Board board : boards) {
                for (Board.Line line : board.getLines()) {
                    if (new HashSet<>(drawnNumbers).containsAll(line.getNumbers())) {
                        winnerBoards.add(board);
                        break;
                    }
                }

            }

            if (winnerBoards.size() == totalBoards) {
                break;
            }
        }

        int result = calculateBoardResult(winnerBoards.get(winnerBoards.size() - 1),
                drawnNumbers.get(drawnNumbers.size() - 1));
        System.out.println("Result: " + result);

    }

    private List<Board> parseBoards() {
        String input = FileReaderUtil.readString(this);

        List<String> parts = new ArrayList<>(Arrays.stream(input.split("\n\n")).toList());

        this.numbersToDraw = Arrays.stream(parts.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        parts.remove(0);

        List<Board> boards = new ArrayList<>();

        for (String part : parts) {
            Board board = new Board();
            board.parseLines(part);
            boards.add(board);
        }
        return boards;
    }

    private int calculateBoardResult(Board board, int drawnNUmber) {
        List<Integer> unmarkedNumbers = board.getAllNumbers();
        unmarkedNumbers.removeAll(drawnNumbers);

        int sum = unmarkedNumbers.stream().mapToInt(Integer::intValue).sum();

        return drawnNUmber * sum;
    }
}

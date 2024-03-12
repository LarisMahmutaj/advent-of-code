package com.laris.Y2023.days.Day04;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Day04 implements Day {
    private final List<Card> cards;

    public Day04() {
        cards = new LinkedList<>();
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        for (String line : lines) {
            String[] split = line.split(":");

            String[] cardData = split[0].replaceAll(" +", " ").split(" ");
            String[] numberData = split[1].split("\\|");
            List<Integer> winningNumbers = Arrays.stream(numberData[0].trim().replaceAll(" +", " ").split(" ")).map(Integer::parseInt).toList();
            List<Integer> drawnNumbers = Arrays.stream(numberData[1].trim().replaceAll(" +", " ").split(" ")).map(Integer::parseInt).toList();

            Card card = Card.builder()
                    .count(1)
                    .id(Integer.parseInt(cardData[1]) - 1)
                    .winningNumbers(winningNumbers)
                    .drawnNumbers(drawnNumbers)
                    .build();
            cards.add(card);
        }
    }

    @Override
    public void partOne() {
        int totalPoints = 0;
        for (Card card : cards) {
            List<Integer> intersect = card.getDrawnNumbers().stream().filter(card.getWinningNumbers()::contains).toList();
            if (intersect.isEmpty()) continue;
            totalPoints += (int) Math.pow(2, intersect.size() - 1);
        }
        System.out.println(totalPoints);
    }

    @Override
    public void partTwo() {
        for (int j = 0; j < cards.size(); j++) {
            Card card = cards.get(j);
            // Repeat for all copies
            for (int k = 0; k < card.getCount(); k++) {
                List<Integer> intersect = card.getDrawnNumbers().stream().filter(card.getWinningNumbers()::contains).toList();
                int count = intersect.size();
                // Repeat for the number of matching numbers
                for (int i = 1; i <= count; i++) {
                    int nextId = card.getId() + i;
                    Optional<Card> optional = cards.stream().filter(c -> c.getId() == nextId).findFirst();
                    if (optional.isEmpty()) {
                        break;
                    }
                    optional.get().incrementCount(1);
                }
            }
        }
        System.out.println(cards.stream().mapToInt(Card::getCount).sum());
    }
}

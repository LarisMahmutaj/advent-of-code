package com.laris.Y2023.days.Day04;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private int id;
    private int count;
    private List<Integer> winningNumbers;
    private List<Integer> drawnNumbers;
    private int intersectCount;

    public Card(Card card) {
        this.setCount(card.getCount());
        this.setId(card.id);
        this.setWinningNumbers(card.winningNumbers);
        this.setDrawnNumbers(card.drawnNumbers);
        this.setIntersectCount(card.intersectCount);
    }

    public void incrementCount(int c) {
        count += c;
    }
}

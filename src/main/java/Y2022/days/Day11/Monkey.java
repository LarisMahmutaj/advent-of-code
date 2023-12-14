package Y2022.days.Day11;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Monkey {
    private int id;
    private final Queue<Long> items;
    private String operation;
    private int divisibleBy;
    private int monkeyIfTrue;
    private int monkeyIfFalse;
    private int inspectedItems;

    public Monkey() {
        this.items = new ArrayDeque<>();
    }

    public void increaseInspectedItems() {
        this.inspectedItems++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monkey monkey = (Monkey) o;
        return getId() == monkey.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public int getDivisibleBy() {
        return divisibleBy;
    }

    public void setDivisibleBy(int divisibleBy) {
        this.divisibleBy = divisibleBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Queue<Long> getItems() {
        return items;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getMonkeyIfFalse() {
        return monkeyIfFalse;
    }

    public void setMonkeyIfFalse(int monkeyIfFalse) {
        this.monkeyIfFalse = monkeyIfFalse;
    }

    public int getMonkeyIfTrue() {
        return monkeyIfTrue;
    }

    public void setMonkeyIfTrue(int monkeyIfTrue) {
        this.monkeyIfTrue = monkeyIfTrue;
    }


    public int getInspectedItems() {
        return inspectedItems;
    }

    public void setInspectedItems(int inspectedItems) {
        this.inspectedItems = inspectedItems;
    }
}

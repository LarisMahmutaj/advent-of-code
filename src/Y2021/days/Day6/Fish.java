package Y2021.days.Day6;

import java.util.Objects;

public class Fish {
    private int timer;
    private int multiplier = 1;

    public Fish() {
        this.timer = 8;
    }

    public void decreaseTimer() {
        this.timer--;
    }

    public void increaseMultiplier() {
        this.multiplier += this.multiplier;
    }

    public Fish(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return getTimer() == fish.getTimer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimer());
    }
}

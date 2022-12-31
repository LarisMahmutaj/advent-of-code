package Y2022.days.Day9;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(Coordinate c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseX(int count) {
        this.x += count;
    }

    public void increaseY(int count) {
        this.y += count;
    }
    
    public void moveUp(int count) {
        this.y += count;
    }
    
    public void moveDown(int count) {
        this.y -= count;
    }

    public void moveRight(int count) {
        this.x += count;
    }

    public void moveLeft(int count) {
        this.x -= count;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof Coordinate.class) {
//            return getX() == that.getX() && getY() == that.getY();
//        }
//        return false;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}

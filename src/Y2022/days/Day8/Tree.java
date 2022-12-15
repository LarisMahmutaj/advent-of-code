package Y2022.days.Day8;

public class Tree {
    public Tree(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    private int x;
    private int y;
    private int value;

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return this.getX() == tree.getX() && this.getY() == tree.getY();
    }

}

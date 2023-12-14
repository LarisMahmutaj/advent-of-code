package Y2022.days.Day08;

public record Tree(int x, int y, int value) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return this.x() == tree.x() && this.y() == tree.y();
    }

}

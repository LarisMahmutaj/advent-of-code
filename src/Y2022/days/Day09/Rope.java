package Y2022.days.Day09;

public class Rope {
    private final Knot head;
    private Knot currentKnot;

    public Knot getHead() {
        return head;
    }

    public Knot getCurrentKnot() {
        return currentKnot;
    }

    public void setCurrentKnot(Knot currentKnot) {
        this.currentKnot = currentKnot;
    }

    public Rope(int knotCount) {
        this.head = new Knot(0, 0);
        head.setName("H");
        currentKnot = this.head;
        for (int i = 0; i < knotCount; i++) {
            Knot knot = new Knot(0, 0);
            knot.setName(i + 1 + "");
            currentKnot.setChildKnot(knot);
            currentKnot = knot;
        }
        currentKnot = this.head;
    }

    public void move(String direction) {
        if (currentKnot == head) {
            switch (direction) {
                case "U" -> currentKnot.getLocation().moveUp(1);
                case "D" -> currentKnot.getLocation().moveDown(1);
                case "L" -> currentKnot.getLocation().moveLeft(1);
                case "R" -> currentKnot.getLocation().moveRight(1);
            }
        }
        if (currentKnot.getChildKnot() != null) {
            currentKnot.moveChild();
        }

        if (head.getLocation().getY() == 8 && head.getLocation().getX() == 3) {
            System.out.println();
        }
        if (currentKnot.getChildKnot() != null) {
            currentKnot = currentKnot.getChildKnot();
            move(direction);
        }
    }
}

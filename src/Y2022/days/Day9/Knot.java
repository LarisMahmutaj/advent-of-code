package Y2022.days.Day9;

public class Knot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Coordinate location;
    private Knot childKnot;

    public Knot getChildKnot() {
        return childKnot;
    }

    public void setChildKnot(Knot childKnot) {
        this.childKnot = childKnot;
    }

    public Knot(int x, int y) {
        this.location = new Coordinate(x, y);
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }
    
    
    public void moveChild() {
        int diffX = location.getX() - childKnot.getLocation().getX();
        int diffY = location.getY() - childKnot.getLocation().getY();
        
        if(diffX > 1) {
            childKnot.getLocation().increaseX(1);
            if(diffY > 0){
                childKnot.getLocation().increaseY(1);
            }else if(diffY < 0) {
                childKnot.getLocation().increaseY(-1);
            }
            return;
        } else if (diffX < -1) {
            childKnot.getLocation().increaseX(-1);
            if(diffY > 0){
                childKnot.getLocation().increaseY(1);
            }else if(diffY < 0) {
                childKnot.getLocation().increaseY(-1);
            }
            return;
        }

        if(diffY > 1) {
            childKnot.getLocation().increaseY(1);
            if(diffX > 0){
                childKnot.getLocation().increaseX(1);
            }else if(diffX < 0) {
                childKnot.getLocation().increaseX(-1);
            }
        } else if (diffY < -1) {
            childKnot.getLocation().increaseY(-1);
            if(diffX > 0){
                childKnot.getLocation().increaseX(1);
            }else if(diffX < 0) {
                childKnot.getLocation().increaseX(-1);
            }
        }
    }
    
}

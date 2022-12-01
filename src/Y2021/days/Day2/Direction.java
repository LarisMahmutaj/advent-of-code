package Y2021.days.Day2;

public enum Direction {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");

    Direction(String value) {
        this.value = value;
    }

    final String value;


    public String getValue() {
        return value;
    }
}

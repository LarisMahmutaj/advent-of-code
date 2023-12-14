package Y2023.days.Day03;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PartNumber {
    private int startX;
    private int endX;
    private int y;
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartNumber that)) return false;
        return startX == that.startX && endX == that.endX && y == that.y && value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, endX, y, value);
    }
}

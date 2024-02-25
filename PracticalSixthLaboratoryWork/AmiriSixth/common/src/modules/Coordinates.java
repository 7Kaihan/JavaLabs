package modules;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x;
    private int y;

    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Long getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (y != that.y) return false;
        return x.equals(that.x);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

package modules;

import colorizedConsole.ConsoleColor;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
    private int x;
    private double y;
    private Long z;

    public Location(int x, double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return ConsoleColor.YELLOW_BOLD + "{" + ConsoleColor.RESET + "\n" +
                ConsoleColor.BLUE_BOLD + "      x=" + ConsoleColor.RESET + x + "\n" +
                ConsoleColor.BLUE_BOLD + "      y=" + ConsoleColor.RESET + y + "\n" +
                ConsoleColor.BLUE_BOLD + "      z=" + ConsoleColor.RESET + z + "\n" +
                ConsoleColor.YELLOW_BOLD + "    }" + ConsoleColor.RESET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (Double.compare(location.y, y) != 0) return false;
        return Objects.equals(z, location.z);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = x;
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }
}

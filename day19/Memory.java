package day19;

import java.util.Objects;

public class Memory {
    private final String pattern;
    private final long startingPoint;

    public Memory(String pattern, int startingPoint){
        this.pattern = pattern;
        this.startingPoint = startingPoint;
    }

    public String getPattern(){return this.pattern;}
    public long getStartingPoint(){return this.startingPoint;}

    // Override equals for correct key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memory that = (Memory) o;
        return this.startingPoint == that.getStartingPoint() && this.pattern.equals(that.getPattern());
    }

    // Override hashCode for efficient hashing
    @Override
    public int hashCode() {
        return Objects.hash(pattern, startingPoint);
    }
}

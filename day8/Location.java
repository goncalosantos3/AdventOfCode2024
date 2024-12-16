package day8;

import java.util.Objects;

public class Location{
    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  
        if (obj == null || getClass() != obj.getClass()) return false; 

        Location location = (Location) obj;
        return x == location.getX() && y == location.getY();  
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y); 
    }
}

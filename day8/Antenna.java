package day8;

public class Antenna {
    private char antenna;
    private Location location;

    public Antenna(char antenna, int x, int y){
        this.antenna = antenna;
        this.location = new Location(x, y);
    }

    public char getAntenna(){return this.antenna;}
    public Location getLocation(){return this.location;}
}

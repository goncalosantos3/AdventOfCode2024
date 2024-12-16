package day8;

import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class A {
    private Set<Location> antiNodeLocations;
    
    public A(){
        this.antiNodeLocations = new HashSet<>();
    }

    public int distanceX(Location l1, Location l2){
        return Math.abs(l1.getX() - l2.getX());
    }

    public int distanceY(Location l1, Location l2){
        return Math.abs(l1.getY() - l2.getY());
    }

    public void calculateAntiNodeLocations(List<char[]> map, Map<Character, List<Antenna>> antennas){
        for(Character c: antennas.keySet()){
            List<Antenna> antennaList = antennas.get(c);

            // Get all possible pairs of the same frequence antennas
            for(int i = 0; i<antennaList.size(); i++){
                for(int j=i+1; j<antennaList.size(); j++){
                    Location l1 = antennaList.get(i).getLocation();
                    Location l2 = antennaList.get(j).getLocation();

                    int dX = distanceX(l1, l2);
                    int dY = distanceY(l1, l2);

                    int newX1, newX2, newY1, newY2;
                    if(l1.getX() < l2.getX()){
                        newX1 = l1.getX() - dX;
                        newX2 = l2.getX() + dX;
                    }else{
                        newX1 = l1.getX() + dX;
                        newX2 = l2.getX() - dX;
                    }

                    if(l1.getY() < l2.getY()){
                        newY1 = l1.getY() - dY;
                        newY2 = l2.getY() + dY;
                    }else{
                        newY1 = l1.getY() + dY; 
                        newY2 = l2.getY() - dY;
                    }

                    if(newX1 >= 0 && newY1 >= 0 && newX1 < map.get(0).length && newY1 < map.size()){
                        antiNodeLocations.add(new Location(newX1, newY1));
                    }
                    if(newX2 >= 0 && newY2 >= 0 && newX2 < map.get(0).length && newY2 < map.size()){
                        antiNodeLocations.add(new Location(newX2, newY2));
                    }
                }
            }
        }
    }

    public Set<Location> getLocations(){return this.antiNodeLocations;}
}

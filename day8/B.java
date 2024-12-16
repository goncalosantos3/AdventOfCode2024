package day8;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class B {
    private Set<Location> antiNodeLocations;
    
    public B(){
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

                    List<Integer> xs = new ArrayList<>();
                    List<Integer> ys = new ArrayList<>();
                    if(l1.getX() < l2.getX() && l1.getY() < l2.getY()){
                        Boolean t1 = true; Boolean t2 = true; 
                        int current1 = l1.getX() - dX;
                        int current2 = l2.getX() + dX;

                        while(t1 && t2){
                            if(current1 > 0 && t1){
                                xs.add(current1);
                                current1 -= dX;
                            }else{
                                t1 = false;
                            }

                            if(current2 < map.get(0).length && t2){
                                xs.add(current2);
                                current2 += dX;
                            }else{
                                t2 = false;
                            }
                        }
                    }else{
                        Boolean t1 = true; Boolean t2 = true; 
                        int current1 = l1.getX() + dX;
                        int current2 = l2.getX() - dX;

                        while(t1 && t2){
                            if(current1 <  map.get(0).length && t1){
                                xs.add(current1);
                                current1 += dX;
                            }else{
                                t1 = false;
                            }

                            if(current2 > 0 && t2){
                                xs.add(current2);
                                current2 -= dX;
                            }else{
                                t2 = false;
                            }
                        }
                    }

                    if(l1.getY() < l2.getY()){
                        Boolean t1 = true; Boolean t2 = true; 
                        int current1 = l1.getY() - dY;
                        int current2 = l2.getY() + dY;

                        while(t1 && t2){
                            if(current1 > 0 && t1){
                                ys.add(current1);
                                current1 -= dY;
                            }else{
                                t1 = false;
                            }

                            if(current2 < map.size() && t2){
                                ys.add(current2);
                                current2 += dY;
                            }else{
                                t2 = false;
                            }
                        }
                    }else{
                        Boolean t1 = true; Boolean t2 = true; 
                        int current1 = l1.getY() + dY;
                        int current2 = l2.getY() - dY;

                        while(t1 && t2){
                            if(current1 < map.size() && t1){
                                ys.add(current1);
                                current1 += dY;
                            }else{
                                t1 = false;
                            }

                            if(current2 > 0 && t2){
                                ys.add(current2);
                                current2 -= dY;
                            }else{
                                t2 = false;
                            }
                        }
                    }

                    for(int index=0; index<xs.size(); index++){
                        this.antiNodeLocations.add(new Location(xs.get(index), ys.get(index)));
                    }
                }
            }
        }
    }

    public Set<Location> getLocations(){return this.antiNodeLocations;}
}

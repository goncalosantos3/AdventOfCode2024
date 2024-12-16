package day8;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution2{
    public static void main(String []args){
        if(args.length < 1){
            System.out.println("You need to specify the input file location!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        ri.read();
        List<char[]> map = ri.getMap();
        Map<Character, List<Antenna>> antennas = ri.getAntennas();

        B b = new B();
        b.calculateAntiNodeLocations(map, antennas);
        Set<Location> locations = b.getLocations();
        System.out.println("There are " + locations.size() + " distinct antinode locations!");
    }
}
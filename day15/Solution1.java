package day15;

import java.util.List;

public class Solution1{
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("You have to provide the input file location!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        ri.read();
        Robot r = ri.getRobot();
        List<List<Character>> map = ri.getMap();

        System.out.println(r.toString());
        for(int i=0; i < map.size(); i++){
            for(int j=0; j < map.get(0).size(); j++){
                System.out.print(map.get(i).get(j));
            }
            System.out.print("\n");
        }

        A a = new A(map, r);
        a.performMovements();
        System.out.println(a.mapToString());
        
        int sum = a.getSumGPSBoxes();
        System.out.println("The final sum of the GPS for the boxes is: " +  sum);
    }
}
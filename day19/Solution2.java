package day19;

import java.util.List;

public class Solution2{
    public static void main(String []args){
        if(args.length < 1){
            System.out.println("You have to provide the input file path!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        Onsen os = ri.read();
        if(os == null){
            System.out.println("Invalid input file!");
            return;
        }
        System.out.println(os.toString());

        B b = new B(os);
        System.out.println("The total number of combinations are: " + b.possiblePatterns());
    }
}
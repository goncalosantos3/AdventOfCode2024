package day19;

public class Solution1{
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

        A a = new A(os);
        System.out.println("The number of possible patterns is: " + a.possiblePatterns());;
    }
}
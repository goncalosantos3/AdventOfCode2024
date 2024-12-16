package day9;

public class Solution2{
    public static void main(String []args){
        if(args.length < 1){
            System.out.println("You have to specify the input file location!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        String fileSystem = ri.read();

        B b = new B(fileSystem);
        b.convert();
        b.compact();
        long checksum = b.calculateCheckSum();
        System.out.println("The checksum is: " + checksum);
    }
}
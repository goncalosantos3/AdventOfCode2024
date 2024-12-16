package day9;

public class Solution1{
    public static void main(String []args){
        if(args.length < 1){
            System.out.println("You need to specify the input file location!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        String fileSystem = ri.read();
        // System.out.println(fileSystem);

        A a = new A(fileSystem);
        a.convert();
        a.compact();
        long checkSum = a.calculateCheckSum();
        System.out.println("The checksum is: " + checkSum);
    }
}
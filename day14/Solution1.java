import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Solution1{
    public static void main(String []args){
        if(args.length < 3){
            System.out.println("You have to specify the input file location and map width and height!");
            return;
        }

        ReadInput ri = new ReadInput(args[0]);
        List<Robot> robots = ri.read();

        StringBuilder output_content_builder = new StringBuilder();
        A a = new A(robots, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        for(int i=0; i<100; i++){
            a.simulateSecond();
            output_content_builder.append("Second " + i+1 + ":\n");
            output_content_builder.append(a.mapWithBots());
            output_content_builder.append("\n");
        }
        System.out.println("After 100 seconds:");
        System.out.println(a.mapWithBots());

        int safetyFactor = a.calculateSafetyFactor();
        System.out.println("The safety factor is: " + safetyFactor);

        // Write output to file (Part 2)
        try (FileWriter fileWriter = new FileWriter("tree.txt", true)) {
            fileWriter.write(output_content_builder.toString()); 
            System.out.println("Text appended successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }
}
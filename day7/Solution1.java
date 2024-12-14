import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution1{
    public static void main(String []args){
        long final_result = 0;
        if(args.length < 1){
            System.out.println("You need to specify the input file location!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(":");
                long test_value = Long.parseLong(parts[0].trim());

                String[] valuesString = parts[1].trim().split(" ");
                List<Integer> values = new ArrayList<>();
                for (String value : valuesString) {
                    values.add(Integer.parseInt(value));
                }
                
                A a = new A(test_value, values);
                Boolean result = a.isTrue();
                if(result){
                    final_result += a.getTestValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        

        System.out.println("The answer is " +  final_result);
    }
}
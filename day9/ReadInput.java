package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput{
    private String filePath;

    public ReadInput(String filePath){
        this.filePath = filePath;
    }

    public String read(){
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);  // Append each part of the line to the content
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
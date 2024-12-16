package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
 
public class ReadInput {
    String filePath;
    List<char[]> map;
    Map<Character, List<Antenna>> antennas;

    public ReadInput(String filePath){
        this.filePath = filePath;
        this.map = new ArrayList<>();
        this.antennas = new HashMap<>();
    }

    public void read(){
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;

            // Read each line and add it as a char array to the matrix
            int i = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                this.map.add(line.toCharArray());
                int j = 0;
                for(char c: chars){
                    if(c != '.'){
                        if(!this.antennas.containsKey(c)){
                            this.antennas.put(c, new ArrayList<>());
                        }
                        this.antennas.get(c).add(new Antenna(c,j,i));
                    } 
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public List<char[]> getMap(){
        return this.map;
    }

    public Map<Character, List<Antenna>> getAntennas(){
        return this.antennas;
    }
}

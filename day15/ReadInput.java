package day15;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput{
    private Robot r;
    private List<List<Character>> map;
    private String filePath; 

    public ReadInput(String filePath){
        this.filePath = filePath;
        this.map = new ArrayList<>();
    }

    public void read(){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isMoves = false;
            List<Integer> moves = new ArrayList<>();

            int i = 0;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty() && !isMoves){ // Map
                    this.map.add(new ArrayList<>());
                    for(int j=0; j<line.length(); j++){
                        map.get(i).add(line.charAt(j));
                        if(line.charAt(j) == '@'){
                            this.r = new Robot(j, i);
                        }
                    }
                }else if(!line.isEmpty() && isMoves){ // Robot Movements
                    for(int j=0; j<line.length(); j++){
                        if(line.charAt(j) == '^'){ // Up
                            moves.add(0);
                        }else if(line.charAt(j) == '>'){ // Right
                            moves.add(1);
                        }else if(line.charAt(j) == 'v'){ // Down
                            moves.add(2);
                        }else if(line.charAt(j) == '<'){ // Left
                            moves.add(3);
                        }else{
                            System.out.println("Invalid map input!");
                            return;
                        }
                    }
                }else{ // The separation line
                    isMoves = true;
                }
                i++;
            }
            this.r.setMoves(moves);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot(){
        return this.r;
    }

    public List<List<Character>> getMap(){
        return this.map;
    }
}
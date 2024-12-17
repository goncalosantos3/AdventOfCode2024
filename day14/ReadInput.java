import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ReadInput {
    private String filePath;
    private List<Robot> robots;

    public ReadInput(String filePath){
        this.filePath = filePath;
        this.robots = new ArrayList<>();
    }

    public List<Robot> read(){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String []parts = line.split(" ");
                String []part1 = parts[0].split("=");
                String []part2 = parts[1].split("=");
                String []pos = part1[1].split(",");
                String []vels = part2[1].split(",");
                this.robots.add(new Robot(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), 
                                            Integer.parseInt(vels[0]), Integer.parseInt(vels[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.robots;
    }
}

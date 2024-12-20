package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadInput{
    private String filePath;

    public ReadInput(String filePath){
        this.filePath = filePath;
    }
    
    public Onsen read(){
        List<String> ats = null;
        List<String> ps = new ArrayList<>();
        Map<Character, List<String>> m_ats = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean patterns = false;

            while ((line = br.readLine()) != null) {
                if(patterns == false && !line.isEmpty()){ // Available towels
                    String clear_line = line.replaceAll("\s", "");
                    String []towels = clear_line.split(","); 
                    ats = Arrays.asList(towels);
                    for(int i=0; i<towels.length; i++){
                        if(!m_ats.containsKey(towels[i].charAt(0)))
                            m_ats.put(towels[i].charAt(0), new ArrayList<>());
                        m_ats.get(towels[i].charAt(0)).add(towels[i]);
                    }
                }else if(line.isEmpty()){
                    // System.out.println("Separating line!");
                    patterns = true;
                }else{ // Desired Patterns
                    ps.add( line.replaceAll("\s", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ats == null || ps.size() == 0)
            return null;

        return new Onsen(ats, ps, m_ats);
    }
}
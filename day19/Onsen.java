package day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Onsen {
    private List<String> availableTowels;
    private List<String> patterns;
    private Map<Character, List<String>> mapped_towels;

    public Onsen(List<String> ats, List<String> ps, Map<Character, List<String>> m){
        this.availableTowels = new ArrayList<>(ats);
        this.patterns = new ArrayList<>(ps);
        this.mapped_towels = m;
    }

    public List<String> getAvailableTowels(){
        return this.availableTowels;
    }

    public void setAvailableTowels(List<String> ats){
        this.availableTowels = new ArrayList<>(ats);
    }

    public List<String> getPatterns(){
        return this.patterns;
    }

    public void setPatterns(List<String> ps){
        this.patterns = new ArrayList<>(ps);
    }

    public Map<Character, List<String>> getMappedTowels(){
        return this.mapped_towels;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Available towels: ");
        for(String towel: this.availableTowels){
            sb.append(towel + ", ");
        }
        sb.append("\nDesired patterns:\n");
        for(String p: this.patterns){
            sb.append(p + "\n");
        }
        sb.append("Mapped towels: \n");
        for(Character c: this.mapped_towels.keySet()){
            sb.append("Towels starting with " + c + ": ");
            for(String towel: this.mapped_towels.get(c)){
                sb.append(towel + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

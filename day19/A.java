package day19;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class A{
    private Onsen onsen;

    public A(Onsen o){  
        this.onsen = o;
    }

    public List<String> getMatching(String pattern, int startingPoint, List<String> towels){
        List<String> matching = new ArrayList<>();

        for(String towel: towels){
            if(startingPoint + towel.length() <= pattern.length() && 
                pattern.substring(startingPoint, startingPoint + towel.length()).equals(towel))
                matching.add(towel);
        }
        return matching;
    }

    public boolean isPossible(String pattern, int startingPoint, Map<Character, List<String>> mapped_towels){
        if(startingPoint == pattern.length()){
            return true;
        }
        Character c = pattern.charAt(startingPoint);
        System.out.println("Called for beginning character: " + c);
        List<String> towels = mapped_towels.get(c);
        System.out.println(towels);
        if(towels != null){
            List<String> matching_towels = getMatching(pattern, startingPoint, towels);
            for(int i=0; i<matching_towels.size(); i++){
                System.out.println("Using towel: " + matching_towels.get(i));
                if(isPossible(pattern, startingPoint + matching_towels.get(i).length(), mapped_towels))
                    return true;
            }
        }
        return false;
    }
    
    public int possiblePatterns(){
        int nr_possible = 0;

        Map<Character, List<String>> mts = this.onsen.getMappedTowels();
        List<String> ps = this.onsen.getPatterns();

        for(String pattern : ps){
            System.out.println(pattern + "!!!!!!!!");
            boolean possible = isPossible(pattern, 0, mts);
            if(possible)
                nr_possible++;
        }

        return nr_possible;
    }
}

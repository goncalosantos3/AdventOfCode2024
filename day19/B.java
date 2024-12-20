package day19;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class B{
    private Onsen onsen;

    public B(Onsen o){  
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

    public long isPossible(String pattern, int startingPoint, Map<Character, List<String>> mapped_towels, Map<Memory, Long> memoization){
        long matching = 0;
        if(startingPoint == pattern.length()){
            // System.out.println("Found!");
            return 1;
        }
        Character c = pattern.charAt(startingPoint);
        // System.out.println("Called for beginning character: " + c);
        List<String> towels = mapped_towels.get(c);
        // System.out.println(towels);
        if(towels != null){
            List<String> matching_towels = getMatching(pattern, startingPoint, towels);
            for(int i=0; i<matching_towels.size(); i++){
                // System.out.println("Using towel: " + matching_towels.get(i));
                Memory m = new Memory(pattern, startingPoint + matching_towels.get(i).length());
                if(!memoization.containsKey(m)){
                    long local_matching = isPossible(pattern, startingPoint + matching_towels.get(i).length(), mapped_towels, memoization);
                    memoization.put(m, local_matching);
                    matching += local_matching;
                }else{
                    matching += memoization.get(m);
                }
                // if(local_matching > 0)
                //     matching += local_matching;
            }
        }
        return matching;
    }

    public long possiblePatterns(){
        long nr_combs = 0;

        Map<Character, List<String>> mts = this.onsen.getMappedTowels();
        List<String> ps = this.onsen.getPatterns();

        for(String pattern : ps){
            Map<Memory, Long> memoization = new HashMap<>();
            System.out.println(pattern + "!!!!!!!!");
            nr_combs += isPossible(pattern, 0, mts, memoization);
            for(Memory m: memoization.keySet()){
                System.out.println("For params " + m.getPattern() + " " + m.getStartingPoint() + " the result is: " + memoization.get(m));
            }
        }

        return nr_combs;
    }

    
    //public List<List<String>> possiblePatterns(){
    //    Map<String, List<List<String>> mappedCombinations = new HashMap<>();
//
    //    Map<Character, List<String>> mts = this.onsen.getMappedTowels();
    //    List<String> ps = this.onsen.getPatterns();
//
    //    for(String pattern : ps){
    //        System.out.println(pattern + "!!!!!!!!");
    //        List<List<String>> combinations = isPossible(pattern, 0, mts);
    //        if(combinations != null)
    //            mappedCombinations.put(pattern, );
    //    }
//
    //    return mappedCombinations;
    //}
}

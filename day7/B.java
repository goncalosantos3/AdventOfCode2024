import java.util.ArrayList;
import java.util.List;

public class B {
    private long test_value;
    private List<Integer> values;

    public B(long tv, List<Integer> vs){
        this.test_value = tv;
        this.values = new ArrayList<>(vs);
    }

    public void generateCombinations(String []operators, String current, int depth, List<String> combinations){
        if (depth == 0) {
            combinations.add(current);
            return;
        }

        for (String op : operators) {
            generateCombinations(operators, current + op, depth - 1, combinations);
        }
    }

    public Boolean isTrue(){
        // char[] operators = {'+', '*', "||"};
        String []operators = {"+", "*", "||"};
        int depth = values.size() - 1;

        List<String> combinations = new ArrayList<>();
        generateCombinations(operators, "", depth, combinations);

        for(String comb: combinations){
            long value = this.values.get(0);
            int numberIndex = 1;
            for (int i = 0; i < comb.length(); i++) {
                char c = comb.charAt(i);
                if(c == '*'){ // Multiplication
                    value *= this.values.get(numberIndex);
                }else if(c == '+'){ // Sum
                    value += this.values.get(numberIndex);
                }else if(c == '|'){ // || Operator
                    i++; // Jump the second '|'
                    value = Long.parseLong(Long.toString(value) + Integer.toString(this.values.get(numberIndex)));
                }
                numberIndex++;
            }

            if(value == this.test_value){
                return true;
            }
        }

        return false;
    }

    public long getTestValue(){
        return this.test_value;
    }
}

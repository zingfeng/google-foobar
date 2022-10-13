import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    static ArrayList<Integer> list = new ArrayList<>();
    static HashMap<Integer, ArrayList<Integer>> divisibleIndexes = new HashMap<>();

    public static int solution(int[] l) {
        // Create ArrayList<Integer>
        for (int k : l) {
            list.add(k);
        }

        // Find divisible numbers for all elements
        for (int index = 0; index < list.size(); index++) {
            findDivisibleIndexes(index);
        }

        // Count lucky triple
        int total = 0;
        for (int index = 0; index < list.size(); index++) {
            ArrayList<Integer> second = divisibleIndexes.get(index);
            for (Integer integer : second) {
                total += divisibleIndexes.get(integer).size();
            }
        }
        return total;
    }

    public static void findDivisibleIndexes(Integer index) {
        ArrayList<Integer> divisibleIndexes = new ArrayList<>();
        Integer element = list.get(index);
        for (int nextIndex = index + 1; nextIndex < list.size(); nextIndex++) {
            if ((list.get(nextIndex) % element) == 0) {
                divisibleIndexes.add(nextIndex);
            }
        }
        Solution.divisibleIndexes.put(index, divisibleIndexes);
    }
}
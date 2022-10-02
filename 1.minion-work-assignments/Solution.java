import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class Solution {
    public static int[] solution(int[] data, int n) {
        // Return result in special cases
        if (n < 1) return new int[]{};
        if (n > data.length) return data;

        // Initialize the result variable
        ArrayList<Integer> result = new ArrayList<>();

        // Count occurrences of values in input data
        HashMap<Integer, Integer> countByValue = new HashMap<>();
        for (Integer value : data) {
            countByValue.put(value, countByValue.getOrDefault(value, 0) + 1);
        }

        // Remove values that appear more than n
        countByValue.entrySet().removeIf(e -> e.getValue() > n);

        // Get passed values in order
        Set<Integer> passed = countByValue.keySet();
        for (Integer value : data) {
            if (passed.contains(value)) result.add(value);
        }

        // Convert ArrayList to int[] then return
        return result.stream().mapToInt(i -> i).toArray();
    }
}
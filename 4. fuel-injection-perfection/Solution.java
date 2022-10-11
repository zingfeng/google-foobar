import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static Map<BigInteger, Integer> result = new HashMap<>();
    public static ArrayList<BigInteger> all = new ArrayList<>();
    public static ArrayList<BigInteger> payloads = new ArrayList<>();
    public static Map<Integer, ArrayList<BigInteger>> collectionByNumLength = new HashMap<>();

    public static BigInteger ZERO = new BigInteger("0");
    public static BigInteger ONE = new BigInteger("1");
    public static BigInteger TWO = new BigInteger("2");

    public static int solution(String x) {
        // Initialize results: Map<target, minimumStep>
        result.put(ONE, 0); //
        result.put(TWO, 1); // It takes at least 1 step to convert TWO to ONE

        // step 1: diminish payload
        BigInteger target = new BigInteger(x);
        payloads.add(target);
        while (payloads.size() > 0) {
            int sizeBefore = payloads.size();
            for (int i = 0; i < sizeBefore; i++) {
                diminishPayload(payloads.get(i));
            }
            int sizeAfter = payloads.size();
            payloads = new ArrayList<>(payloads.subList(sizeBefore, sizeAfter));
        }

        // step 2: group by the length of number
        ArrayList<Integer> numLengths = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Integer numLength = all.get(i).toString().length();
            ArrayList<BigInteger> current = collectionByNumLength.get(numLength);

            if (current == null) {
                current = new ArrayList<>();
                numLengths.add(numLength);
            }

            current.add(all.get(i));
            collectionByNumLength.put(numLength, current);
        }

        // step 3: handle from small to large
        Collections.sort(numLengths); // sort by the length of number
        for (int i = 0; i < numLengths.size(); i++) {
            ArrayList<BigInteger> myCollection = collectionByNumLength.get(numLengths.get(i));

            // sort ascending
            Collections.sort(myCollection);

            for (int j = 0; j < myCollection.size(); j++) {
                findStep(myCollection.get(j));
            }
        }

        return result.get(target);
    }

    public static void diminishPayload(BigInteger target) {
        if (all.contains(target)) {
            return;
        }

        BigInteger[] res = target.divideAndRemainder(TWO);

        if (res[1].equals(ZERO)) {
            if (res[0].equals(ZERO)) return;

            if (!all.contains(res[0])) {
                payloads.add(res[0]);
            }
        } else {
            // handle smaller number before larger number
            BigInteger sub1 = target.subtract(ONE);
            if (!all.contains(sub1)) {
                payloads.add(sub1);
            }

            BigInteger plus1 = target.add(ONE);
            if (!all.contains(plus1)) {
                payloads.add(plus1);
            }
        }
        all.add(target);
    }

    public static int findStep(BigInteger target) {
        if (result.getOrDefault(target, -1) >= 0) {
            return result.get(target);
        }

        BigInteger[] res = target.divideAndRemainder(TWO);
        ArrayList<Integer> possibleSteps = new ArrayList<>();
        if (res[1].equals(ZERO)) {
            int childStep = findStep(res[0]);
            possibleSteps.add(childStep);
        } else {
            BigInteger sub1 = target.subtract(ONE);
            int subStep = findStep(sub1);
            possibleSteps.add(subStep);

            BigInteger plus1 = target.add(ONE);
            int plusStep = findStep(plus1);
            possibleSteps.add(plusStep);
        }

        Integer min = Collections.min(possibleSteps);
        result.put(target, min + 1);
        return min + 1;
    }
}
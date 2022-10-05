import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static int solution(int[] al) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int val : al) list.add(val);

        ArrayList<ArrayList<Integer>> candidates = getCandidateCollections(list);

        ArrayList<Integer> candidateNumber = new ArrayList<>();
        for (ArrayList<Integer> candidate : candidates) {
            Integer maxNumber = getMax(candidate);
            candidateNumber.add(maxNumber);
        }
        return Collections.max(candidateNumber);
    }

    public static ArrayList<ArrayList<Integer>> getCandidateCollections(ArrayList<Integer> al) {
        ArrayList<ArrayList<Integer>> candidates = new ArrayList<>();
        ArrayList<Integer> C0 = new ArrayList<>();
        ArrayList<Integer> C1 = new ArrayList<>();
        ArrayList<Integer> C2 = new ArrayList<>();

        int sum = 0;
        for (Object o : al) {
            sum += ((int) o);
        }

        int remainder = sum % 3;

        for (Object val : al) {
            int value = (int) val;
            if ((value % 3) == 0) C0.add(value);
            if ((value % 3) == 1) C1.add(value);
            if ((value % 3) == 2) C2.add(value);
        }

        Collections.sort(C1);
        Collections.sort(C2);

        switch (remainder) {
            case 0:
                C0.addAll(C2);
                C0.addAll(C1);
                candidates.add(C0);
                break;
            case 1:
                if (C1.size() > 0) {
                    ArrayList<Integer> A11 = new ArrayList<>(C1.subList(1, C1.size()));
                    A11.addAll(C0);
                    A11.addAll(C2);
                    candidates.add(A11);
                }
                if (C2.size() > 1) {
                    ArrayList<Integer> A21 = new ArrayList<>(C2.subList(2, C2.size()));
                    A21.addAll(C0);
                    A21.addAll(C1);
                    candidates.add(A21);
                }
                break;
            case 2:
                if (C1.size() > 1) {
                    ArrayList<Integer> A12 = new ArrayList<>(C1.subList(2, C1.size()));
                    A12.addAll(C0);
                    A12.addAll(C2);
                    candidates.add(A12);
                }
                if (C2.size() > 0) {
                    ArrayList<Integer> A22 = new ArrayList<>(C2.subList(1, C2.size()));
                    A22.addAll(C0);
                    A22.addAll(C1);
                    candidates.add(A22);
                }
                break;
            default:
        }

        return candidates;
    }

    public static Integer getMax(ArrayList<Integer> candidate) {
        if (candidate.size() == 0) return 0;

        Collections.sort(candidate);
        Collections.reverse(candidate);
        StringBuilder number = new StringBuilder();
        for (Integer integer : candidate) {
            number.append(integer);
        }
        return Integer.parseInt(number.toString());
    }
}
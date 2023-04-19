package Skyscrapers;

import java.util.ArrayList;

public class Skyscrapers {
    private Steps steps = new Steps();
    private SkyscrapersInfo info = new SkyscrapersInfo();

    public Skyscrapers(int n, ArrayList<Integer> arrayList) {
        info.setBoardSize(n);
        info.setGivenHints(arrayList);
        info.setAnswerBoard(new int[n * n]);

        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 1; i <= n; i++) t.add(i);
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < n * n; i++) temp.add(new ArrayList<>());
        for (int i = 0; i < n * n; i++) temp.get(i).addAll(t);

        info.setCandidate(temp);
        info.setCountUnansweredCell(n * n);
    }

    // String input
    public Skyscrapers(int n, String[] givenHints) {
        this(n, convertStringArrayToArrayList(givenHints, n));
    }

    public Skyscrapers(int n, String[] givenHints, String[] givenDigits) {
        this(n, givenHints);

        int[] arr = convertStringArrayToIntegerArray(givenDigits, n);
        for (int i = 0; i < n * n; i++)
            if (arr[i] > 0)
                info.resolveCell(i, arr[i]);
    }

    public void solveBoard() {
        steps.edgeClueInitialization(info);
        steps.processOfElimination(info);
        steps.clueElimination(info);
    }

    public void printAnswerBoard() {
        int n = info.getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(info.getAnswerBoard()[i * n + j] + " ");
            }
            System.out.println();
        }
    }

    // 주어진 String[]을 ArrayList<Integer>로 변환하는 메소드
    private static ArrayList<Integer> convertStringArrayToArrayList(String[] givenHints, int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String hints : givenHints) {
            while (hints.length() < n) hints = "0" + hints;
            for (int i = 0; i < hints.length(); i++) {
                char c = hints.charAt(i);
                int num = Character.getNumericValue(c);
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    private static int[] convertStringArrayToIntegerArray(String[] givenDigits, int n) {
        int[] arr = new int[n * n];
        int idx = 0;
        for (String digits : givenDigits) {
            while (digits.length() < n) digits = "0" + digits;
            for (int i = 0; i < digits.length(); i++) {
                char c = digits.charAt(i);
                int num = Character.getNumericValue(c);
                arr[idx] = num;
                idx++;
            }
        }
        return arr;
    }
}

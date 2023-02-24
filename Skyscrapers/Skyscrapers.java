package Skyscrapers;

import java.util.ArrayList;

public class Skyscrapers {
    private Steps steps = new Steps();
    SkyscrapersInfo sky = new SkyscrapersInfo();

    public Skyscrapers(int n, ArrayList arrayList) {
        sky.setBoardSize(n);
        sky.setGivenHints(arrayList);
        sky.setAnswerBoard(new int[n * n]);

        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 1; i <= n; i++) t.add(i);
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < n * n; i++) temp.add(new ArrayList<>());
        for (int i = 0; i < n * n; i++) temp.get(i).addAll(t);

        sky.setCandidate(temp);
    }

    public void solveBoard() {
        steps.edgeClueInitialization(sky);
    }

    public void answerBoard() {
        int n = sky.getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(sky.getAnswerBoard()[i * n + j] + " ");
                // for debug purposes
                System.out.println("data " + i + "," + j + ": " + sky.getCandidate().get(i * n + j) + " ");
            }
            System.out.println();
        }
    }
}

package Skyscrapers;

import java.util.ArrayList;

public class Skyscrapers {
    private Steps steps = new Steps();
    SkyscrapersInfo info = new SkyscrapersInfo();

    public Skyscrapers(int n, ArrayList arrayList) {
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

    public void solveBoard() {
        steps.edgeClueInitialization(info);
        steps.processOfElimination(info);
        steps.clueElimination(info);
    }

    public void answerBoard() {
        int n = info.getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(info.getAnswerBoard()[i * n + j] + " ");
                // for debug purposes
//                System.out.println("data " + i + "," + j + ": " + info.getCandidate().get(i * n + j) + " ");
                System.out.print(info.getCandidate().get(i * n + j) + ",\t ");
            }
            System.out.println();
        }
    }
}

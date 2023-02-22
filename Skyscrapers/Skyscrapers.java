package Skyscrapers;

import java.util.ArrayList;

public class Skyscrapers {
    private Steps steps;
    SkyscrapersInfo sky = new SkyscrapersInfo();

    public Skyscrapers(int n, ArrayList arrayList) {
        sky.setBoardSize(n);
        sky.setGivenHints(arrayList);
        sky.setAnswerBoard(new int[n * n]);

        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 1; i <= n; i++) t.add(i);
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < n * n; i++) temp.add(t);

        sky.setCandidate(temp);
    }

    public void Solve() {
//        steps.EdgeClueInitialization(sky);
    }

    public void Answer() {
        for (int i = 0; i < sky.getBoardSize(); i++) {
            for (int j = 0; j < sky.getBoardSize(); j++) {
//                System.out.print(sky.getAnswerBoard()[i * 6 + j] + " ");
                // for debug purposes
                System.out.println("data " + i + "," + j + ": " + sky.getCandidate().get(i * 6 + j) + " ");
            }
            System.out.println();
        }
    }
}

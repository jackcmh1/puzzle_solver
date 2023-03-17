package Skyscrapers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Steps {
    private static int N;
    private int[] index;

    // initial setting for given hints
    public void edgeClueInitialization(SkyscrapersInfo info) {
        N = info.getBoardSize();
        index = new int[N];
        for (int i = 0; i < 4 * N; i++) {
            if (info.getGivenHints().get(i) != 0) {
                indexSetting(i);
                edgeConstraintRule(info.getGivenHints().get(i), info);
            }
        }
    }

    // taking one column/row for each given "hint"
    private void indexSetting(int hintNum) {
        int num = hintNum % N;
        switch (hintNum / N) {
            case 0: // direction : down
                for (int i = 0; i < N; i++)
                    index[i] = i * N + num;
                break;
            case 1: // direction : left
                for (int i = 0; i < N; i++) {
                    index[i] = (num + 1) * N - (i + 1);
                }
                break;
            case 2: // direction : up
                for (int i = 0; i < N; i++) {
                    index[i] = (N - i) * N - (num + 1);
                }
                break;
            case 3: // direction : right
                for (int i = 0; i < N; i++) {
                    index[i] = (N - 1 - num) * N + i;
                }
                break;
        }
    }

    // eliminate some candidate for given hint.
    // possible of finding certain digits from this process
    // example : 1 or N hint.
    private void edgeConstraintRule(int hint, SkyscrapersInfo info) {
        if (hint == 1) {
            // the first square is always N.
            // the rest of the square remove the N from candidate.
            info.resolveCell(index[0], N);
        } else if (hint == N) {
            // the answer is 1 2 ... N-1 N (the only way)
            for (int i = 0; i < N; i++) info.resolveCell(index[i], i + 1);
        } else {
            // 1 < hint < N
            for (int k = 0; k < N; k++)
                for (int i = N + k + 2 - hint; i <= N; i++)
                    info.getCandidate().get(index[k]).remove(Integer.valueOf(i));
        }
    }

    public void processOfElimination(SkyscrapersInfo info) {
        while (true) {
            int tmp = info.getCountUnansweredCell();

            for (int i = N; i >= 1; i--) {
                processOfEliminationByNumber(info, i);
            }

            if (tmp == info.getCountUnansweredCell()) break;
        }
    }

    public void processOfEliminationByNumber(SkyscrapersInfo info, int hint) {
        while (true) {
            int tmp = info.getCountUnansweredCell();

            for (int i = 0; i < 2 * N; i++) {
                indexSetting(i);
                int cnt = 0;
                int idx = -1;
                for (int x = 0; x < N; x++) {
                    if (info.getCandidate().get(index[x]).contains(hint)) {
                        cnt++;
                        idx = index[x];
                    }
                }
                if (cnt == 1 && info.getAnswerBoard()[idx] == 0) {
                    info.resolveCell(idx, hint);
                }
            }

            if (tmp == info.getCountUnansweredCell()) break;
        }
    }

    public int currentPositionBuildingCount(Stack<Integer> stack) {
        int cnt = 0;
        int currentHeight = 0;

        for (int s : stack) {
            if (currentHeight < s) {
                currentHeight = s;
                cnt++;
            }
        }

        return cnt;
    }

    public void clueElimination(SkyscrapersInfo info) {
        while(true) {
            System.out.println("clueElimination one time"); // TODO for debug purpose
            info.printCandidateBoard(); // TODO for debug purpose
            if(!clueEliminationOnce(info)) break;
        }
    }

    public boolean clueEliminationOnce(SkyscrapersInfo info) {
        if (info.getCountUnansweredCell() == 0) return false; // if everything is answered, just return

        int cycle = 0;

        while (true) {
            boolean isChanged = false;

            for (int i = 0; i < 4 * N; i++) {
                if (info.getGivenHints().get(i) == 0) continue;
                indexSetting(i);
                if (clueEliminationByLine(info, i)) isChanged = true;
            }

            if (!isChanged) break;
            cycle++;
        }

        if (cycle == 0) return false;

        processOfElimination(info);
        return true;
    }

    private boolean clueEliminationByLine(SkyscrapersInfo info, int idx) { // true if changed anything, if not false
        boolean isChanged = false;

        int hint = info.getGivenHints().get(idx);
        ArrayList<ArrayList<Integer>> compare = new ArrayList<>();
        for (int i = 0; i < N; i++) compare.add(new ArrayList<>());

//        System.out.println("number #" + idx); // TODO debug purpose

        dfs(info, hint, new Stack<Integer>(), 0, compare);
        for (int i = 0; i < N; i++) Collections.sort(compare.get(i));

//        System.out.println("current stack is :"); // TODO debug purpose
//        for (int i = 0; i < N; i++) { // TODO debug purpose
//            System.out.println(info.getCandidate().get(index[i]) + " - "
//                    + compare.get(i).containsAll(info.getCandidate().get(index[i]))
//            );
//        }

        for (int i = 0; i < N; i++) {
            if (!compare.get(i).containsAll(info.getCandidate().get(index[i]))) {
                isChanged = true;
                info.getCandidate().get(index[i]).clear();
                info.getCandidate().get(index[i]).addAll(compare.get(i));
            }
        }

//        System.out.println("final stack : " + compare); // TODO debug purpose
//        System.out.println("ischanged : " + isChanged); // TODO debug purpose
//        for (int i = 0; i < N; i++) System.out.println("check stack : " + info.getCandidate().get(index[i])); // TODO debug

        return isChanged;
    }

    private void dfs(SkyscrapersInfo info, int hint, Stack<Integer> stack, int x, ArrayList<ArrayList<Integer>> arr) {
        if (x == N) {
            if (currentPositionBuildingCount(stack) == hint) {
                //System.out.println(stack + " : " + currentPositionBuildingCount(stack)); // TODO debug purpose
                for (int i = 0; i < N; i++) {
                    int tmp = stack.get(i);
                    if (!arr.get(i).contains(tmp)) arr.get(i).add(tmp);
                }
                //System.out.println(arr); // TODO debug purpose
            }
            return;
        }

        for (int num : info.getCandidate().get(index[x])) {
            if (!stack.contains(num)) {
                stack.push(num);
                dfs(info, hint, stack, x + 1, arr);
                stack.pop();
            }
        }
    }
}

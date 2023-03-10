package Skyscrapers;

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
}

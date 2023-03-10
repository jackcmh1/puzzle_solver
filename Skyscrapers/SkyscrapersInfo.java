package Skyscrapers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SkyscrapersInfo {
    private int boardSize; // a square board which one side is length N.
    private ArrayList<Integer> givenHints = new ArrayList<>(); // 4 * N long
    private int[] answerBoard; // N * N square board
    private ArrayList<ArrayList<Integer>> candidate; // 1D N * N long list which each has "1~N" list in it

    // when we resolve a cell, we put the value in the answerBoard
    // and eliminate candidate that is the same row or column.
    public void resolveCell(int index, int value) {
        answerBoard[index] = value;
        eliminateCandidateFromFoundDigit(index);
    }

    public void eliminateCandidateFromFoundDigit(int index) {
        Set<Integer> indexSet = new HashSet<>();
        int row = index / boardSize;
        int col = index % boardSize;
        int answer = answerBoard[index];

        for (int i = 0; i < boardSize; i++) {
            indexSet.add(row * boardSize + i);
            indexSet.add(i * boardSize + col);
        }

        indexSet.remove(index);
        candidate.get(index).clear();
        candidate.get(index).add(answer);

        for(int i : indexSet)
            candidate.get(i).remove(Integer.valueOf(answer));
    }
}

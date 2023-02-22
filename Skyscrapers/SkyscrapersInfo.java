package Skyscrapers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SkyscrapersInfo {
    private int boardSize; // -> square which one side is length N.
    private ArrayList<Integer> givenHints = new ArrayList<>(); // 4 * N long
    private int[] answerBoard; // N * N square board
    private ArrayList<ArrayList<Integer>> candidate;
}

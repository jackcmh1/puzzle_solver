import Skyscrapers.Skyscrapers;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
//        Skyscrapers example1 = new Skyscrapers(6, new ArrayList<>(
//                Arrays.asList(
//                        1, 2, 3, 4, 5, 6,
//                        0, 0, 0, 0, 0, 0,
//                        0, 0, 0, 0, 0, 0,
//                        0, 0, 0, 0, 0, 0)
//        ));
//        example1.solveBoard();
//        example1.answerBoard();

//        Skyscrapers example2 = new Skyscrapers(4, new ArrayList<>(
//                Arrays.asList(
//                        0, 0, 1, 2,
//                        0, 2, 0, 0,
//                        0, 3, 0, 0,
//                        0, 1, 0, 0)
//        ));
//        example2.solveBoard();
//        example2.answerBoard();

        Skyscrapers example3 = new Skyscrapers(7, new ArrayList<>(
                Arrays.asList(
                        0, 2, 3, 0, 2, 0, 0,
                        5, 0, 4, 5, 0, 4, 0,
                        0, 4, 2, 0, 0, 0, 6,
                        0, 0, 0, 0, 0, 4, 0)
        ));
        example3.solveBoard();
        example3.answerBoard();

//        Skyscrapers skyscrapers = new Skyscrapers(6, new ArrayList<>(
//                Arrays.asList(
//                        0, 0, 3, 0, 3, 3,
//                        0, 4, 4, 4, 0, 0,
//                        0, 0, 5, 0, 5, 0,
//                        0, 2, 2, 0, 2, 0)
//        ));
//        skyscrapers.solveBoard();
//        skyscrapers.answerBoard();
    }
}

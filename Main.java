import Skyscrapers.Skyscrapers;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Skyscrapers example1 = new Skyscrapers(6, new ArrayList<>(
                Arrays.asList(
                        1, 2, 3, 4, 5, 6,
                        0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0)
        ));
        example1.Solve();
        example1.Answer();
//        Skyscrapers skyscrapers = new Skyscrapers(6, new ArrayList<>(
//                Arrays.asList(
//                        0, 0, 3, 0, 3, 3,
//                        0, 4, 4, 4, 0, 0,
//                        0, 0, 5, 0, 5, 0,
//                        0, 2, 2, 0, 2, 0)
//        ));
//        skyscrapers.Solve();
//        skyscrapers.Answer();
    }
}

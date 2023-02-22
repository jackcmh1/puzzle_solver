import Skyscrapers.Skyscrapers;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Skyscrapers skyscrapers = new Skyscrapers(6, new ArrayList<>(
                Arrays.asList(
                        0, 0, 3, 0, 3, 3,
                        0, 4, 4, 4, 0, 0,
                        0, 0, 5, 0, 5, 0,
                        0, 2, 2, 0, 2, 0)
        ));
        skyscrapers.Solve();
        skyscrapers.Answer();
    }
}

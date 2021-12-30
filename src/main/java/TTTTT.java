import Services.Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TTTTT {
    public static void main(String[] args) throws Exception {
        Testing testing = new Testing();
        ArrayList<Testing> testings = testing.selectQuery();
        for (Testing t: testings) {
            System.out.println(t.getName());
        }

    }
}

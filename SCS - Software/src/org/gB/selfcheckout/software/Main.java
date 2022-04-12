package org.gB.selfcheckout.software;

import java.util.ArrayList;

public class Main {
    static ArrayList<State> states = new ArrayList<State>();

    public static State init(int scaleMaxWeight, int scaleSensitivity) throws Exception {
        State state = new SCSMain().init(scaleMaxWeight, scaleSensitivity);
        states.add(state);
        return state;
    }

    public static void error(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) { // Not sure how we want to create all the stations, so just added this as a temporary thing.
        for (int i = 0; i < 4; i++) {
            try {
                init(100, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

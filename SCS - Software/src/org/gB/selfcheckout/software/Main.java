package org.gB.selfcheckout.software;

import java.util.ArrayList;

public class Main {
    // public static ArrayList<State> states = new ArrayList<State>();
    public ArrayList<State> states = new ArrayList<State>();

    public State createState(int scaleMaxWeight, int scaleSensitivity) throws Exception {
        State state = new SCSMain().init(scaleMaxWeight, scaleSensitivity);
        states.add(state);
        return state;
    }

    // public static State init(int scaleMaxWeight, int scaleSensitivity) throws Exception {
    //     State state = new SCSMain().init(scaleMaxWeight, scaleSensitivity);
    //     states.add(state);
    //     return state;
    // }

    public static void error(String message) {
        // System.out.println(message);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            try {
                // init(100, 10);
                new Main().createState(100, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ac = new AttendantControl(states);
        aFrame = new AttendantFrame(states, cFrames, ac);
    }
}

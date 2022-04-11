package org.gB.selfcheckout.software;

import java.util.ArrayList;

import org.gB.selfcheckout.software.UI.AttendantFrame;
import org.gB.selfcheckout.software.UI.CustomerFrame;

public class Main {
    static ArrayList<State> states = new ArrayList<State>();
    static ArrayList<CustomerFrame> cFrames = new ArrayList<CustomerFrame>();

    public static State init(int scaleMaxWeight, int scaleSensitivity) throws Exception {
        State state = new SCSMain().init(scaleMaxWeight, scaleSensitivity);
        states.add(state);
        return state;
    }

    public static void error(String message) {
        // System.out.println(message);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            try {
                State st = init(100, 10);
                cFrames.add(new CustomerFrame(i, st));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AttendantControl ac = new AttendantControl(states);
        AttendantFrame aFrame = new AttendantFrame(states, cFrames, ac);
    }
}

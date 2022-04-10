package org.gB.selfcheckout.software;

import java.util.ArrayList;

import org.gB.selfcheckout.software.UI.AttendantFrame;
import org.gB.selfcheckout.software.UI.CustomerFrame;

public class Main {
    public static ArrayList<State> states = new ArrayList<State>();
    static AttendantFrame aFrame;
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
            	State s = init(100, 10);
                states.add(s);
                cFrames.add(new CustomerFrame(i, states.get(i)));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        aFrame = new AttendantFrame(states, cFrames);
    }
}

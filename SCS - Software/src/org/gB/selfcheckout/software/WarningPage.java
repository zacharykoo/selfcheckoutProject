import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
public class Warningpage extends JFrame {
	public static void main(String[] args) {
	      JFrame frame = new JFrame("Customer warning");
	      JPanel panel = new JPanel();
	      JLabel label = new JLabel("Warning Please wait for an employee to attend to you");
	      panel.setLayout(new GridBagLayout());
	      panel.add(label);
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.add(panel);
	      frame.setSize(600, 400);
	      frame.setVisible(true);
	   }
	}  

}
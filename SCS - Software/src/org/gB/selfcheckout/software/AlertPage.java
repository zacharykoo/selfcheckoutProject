import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
	public class AlertPage extends JFrame {
		public static void main(String[] args) {
			 JFrame f= new JFrame("Label Example");  
			    JLabel label;
			    JButton button = new JButton("accept");  
			    label=new JLabel("Alert.");  
			    label.setBounds(50,50, 100,30);   
			    button.setBounds(50,100, 100,30);  
			    f.add(label); f.add(button);  
			    f.setSize(300,300);  
			    f.setLayout(null);  
			    f.setVisible(true);  
			    
		}  
        
	}  
package org.gB.selfcheckout.software.UI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends JPanel {
	private JLabel loginlabel, passwordlabel;
	private JTextField loginfield;
	private JPasswordField passwordfield;
	private JButton loginbutton;

    public LoginScreen() {
		this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 100));
        JPanel subpanel = new JPanel();;
        subpanel.setLayout(new GridBagLayout());
        subpanel.setPreferredSize(new Dimension(200, 100));
		GridBagConstraints c = new GridBagConstraints();
		
		loginlabel = new JLabel("Login: ", SwingConstants.RIGHT);
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		subpanel.add(loginlabel, c);
		
		loginfield = new JTextField();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,0,5,5);
		c.gridx = 1;
		c.gridy = 0;
		subpanel.add(loginfield, c);
		
		passwordlabel = new JLabel("Password:", SwingConstants.RIGHT);
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		subpanel.add(passwordlabel, c);

		passwordfield = new JPasswordField();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,0,5,5);
		c.gridx = 1;
		c.gridy = 1;
		subpanel.add(passwordfield, c);

		loginbutton = new JButton("Login");
		c.ipady = 0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10,0,5,5);
		c.gridx = 1;
		c.gridy = 3;
        loginbutton.addActionListener(e -> {
    		//TODO: idk it does something though
    	});
		subpanel.add(loginbutton, c);
        this.add(subpanel);
    }
}

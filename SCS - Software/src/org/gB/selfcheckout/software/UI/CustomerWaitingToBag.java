package org.gB.selfcheckout.software.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * JPanel that implements the interface shown to the customer when the system is
 * waiting for them to bag an item.
 */
public class CustomerWaitingToBag extends JPanel {
	private static final long serialVersionUID = 1L;
	// Message to inform the user to bag their item.
	private JLabel msg =
			new JLabel("Please place the scanned item in the bagging area", SwingConstants.CENTER);
	// Button to for the user to indicate they don't want to bag the item.
	private JButton doNotBag = new JButton("Skip Bagging This Item");
	private Timer attendantTimer; // A 5 second timeout for the bagging.
	private CustomerFrame customerFrame;
	
	/**
	 * Initializes the waiting to bag item interface for the self-checkout
	 * station.
	 */
	public CustomerWaitingToBag(CustomerFrame customerFrame) {
		super();

		this.customerFrame = customerFrame;
		setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		doNotBag.setAlignmentX(Component.CENTER_ALIGNMENT);
		msg.setAlignmentX(Component.CENTER_ALIGNMENT);
		doNotBag.setAlignmentY(Component.CENTER_ALIGNMENT);
		msg.setAlignmentY(Component.CENTER_ALIGNMENT);
		msg.setFont(new Font("serif", Font.PLAIN, 20));
		
		// Have the button press cancel the timer and call the attendant.
		doNotBag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Reset the timer.
				attendantTimer.cancel();
				attendantTimer.purge();
				itemNotBagged(); // Handle the item not being bagged.
			}});
		this.add(msg);
		this.add(doNotBag);
	}
	
	/**
	 * Commences a 5 second timer, after which time the interface will change to
	 * a blocked screen and alert the attendant that the user has failed to bag
	 * their item. If the user pushes the "Do Not Bag Item" button on the
	 * screen, the timer is ended early and the same actions are taken. If the
	 * customer puts the item in the bagging area instead, the timer is
	 * cancelled and the interface is transitioned to the scan and bag screen.
	 */
	public void waiting() {
		attendantTimer = new Timer();
		attendantTimer.schedule(new TimerTask() {
			@Override
			public void run() { itemNotBagged(); }}, 
			5000);
	}
	
	/**
	 * In the event that a scanned item was not bagged, changes the UI to the
	 * blocked screen and alerts the attendant interface of the issue.
	 */
	private void itemNotBagged() {
		// TODO: Transition to the blocked screen.
		customerFrame.cardLayout.show(customerFrame.getContentPane(), "blockedScreen");

		// TODO: Alert the attendant that the item was not bagged.
	}
	
	/**
	 * Cancels the 5 second timer associated with a user scanning an item and
	 * not yet bagging it.
	 */
	public void cancelWait() {
		// Reset the timer.
		attendantTimer.cancel();
		attendantTimer.purge();
		// TODO: Return to the scan and bag interface.
	}
}

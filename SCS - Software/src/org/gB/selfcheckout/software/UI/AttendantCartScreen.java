package org.gB.selfcheckout.software.UI;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.*;

import javax.swing.*;
import javax.swing.event.*;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

/*
Based around the list example found on the oracle site:
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
*/
public class AttendantCartScreen extends JPanel implements ListSelectionListener {
    //TODO: Modify to add the actual productCart
    private Map<Product,Integer> productCart;
    private JList<Entry<Product,Integer>> items;
    private DefaultListModel<Entry<Product,Integer>> itemModel;
    private JButton removeButton, backButton;
    public AttendantCartScreen() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(500, 500));
        this.setMinimumSize(new Dimension(200, 200));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // TODO: Fill itemModel with Customer Cart
        BarcodedProduct test = new BarcodedProduct(new Barcode(null), "test", BigDecimal.ONE, 1.0);
        productCart.put(test, 1);
        itemModel = new DefaultListModel<Entry<Product,Integer>>();
        for(Entry<Product, Integer> entry: productCart.entrySet()) {
            itemModel.addElement(entry);
        }

        //Create the list and put it in a scroll pane.
        items = new JList<Entry<Product,Integer>>(itemModel);
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        items.setSelectedIndex(0);
        items.addListSelectionListener(this);
        items.setVisibleRowCount(10);

        JScrollPane listScrollPane = new JScrollPane(items);
        c.weightx = 0.5;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.add(listScrollPane, c);
        
        removeButton = new JButton("Remove");
        RemoveListener removeListener = new RemoveListener();
        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(removeListener);

        backButton = new JButton("Back");
        BackListener backListener = new BackListener();
        backButton.setActionCommand("Back");
        backButton.addActionListener(backListener);

        String name = itemModel.getElementAt(items.getSelectedIndex()).toString();
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(backButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(removeButton);
        
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        c.weightx = 0.8;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 1;
        c.gridy = 1;
        this.add(buttonPane, c);
    }
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting() == false) {

            if (items.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }
    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO: Back button implementation
        }
    }
    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            //TODO: Remove Item from Checkout
            productCart.remove(items.getSelectedValue().getKey());
            int index = items.getSelectedIndex();
            itemModel.remove(index);

            int size = itemModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == itemModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                items.setSelectedIndex(index);
                items.ensureIndexIsVisible(index);
            }
        }
    }
}

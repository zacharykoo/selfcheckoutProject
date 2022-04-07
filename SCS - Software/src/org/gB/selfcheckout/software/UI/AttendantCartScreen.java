package swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/*
Based around the list example found on the oracle site:
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
*/
public class AttendantCartScreen extends JPanel implements ListSelectionListener {
    //TODO: Modify to accomodate for actual items
    private JList<String> items;
    private DefaultListModel<String> itemModel;
    private JTextField itemName;
    private JButton addButton, removeButton, backButton;
    public AttendantCartScreen() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(500, 500));
        this.setMinimumSize(new Dimension(200, 200));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // TODO: Fill itemModel with Customer Cart
        itemModel = new DefaultListModel<String>();
        itemModel.addElement("Items");
        itemModel.addElement("Are");
        itemModel.addElement("Complicated");

        //Create the list and put it in a scroll pane.
        items = new JList<String>(itemModel);
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
        
        /*
        addButton = new JButton("Add");
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand("Add");
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);
        */

        removeButton = new JButton("Remove");
        RemoveListener removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(removeListener);

        backButton = new JButton("Back");
        BackListener backListener = new BackListener(backButton);
        backButton.setActionCommand("Back");
        backButton.addActionListener(backListener);

        /*
        itemName = new JTextField(10);
        itemName.addActionListener(addListener);
        itemName.getDocument().addDocumentListener(addListener);
        */
        String name = itemModel.getElementAt(items.getSelectedIndex()).toString();
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(backButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(removeButton);
        
        //buttonPane.add(itemName);
        //buttonPane.add(addButton);
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
        private JButton button;

        public BackListener(JButton button) {
            this.button = button;
        }
        public void actionPerformed(ActionEvent e) {
            //TODO: Back button implementation
        }
    }
    class RemoveListener implements ActionListener {
        private JButton button;

        public RemoveListener(JButton button) {
            this.button = button;
        }
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = items.getSelectedIndex();
            //TODO: Remove Item from Checkout
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
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = itemName.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                itemName.requestFocusInWindow();
                itemName.selectAll();
                return;
            }

            int index = items.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            itemModel.insertElementAt(itemName.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            itemName.requestFocusInWindow();
            itemName.setText("");

            //Select the new item and make it visible.
            items.setSelectedIndex(index);
            items.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return itemModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
}

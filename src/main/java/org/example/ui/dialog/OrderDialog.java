package org.example.ui.dialog;

import org.example.model.MenuItem;
import org.example.model.Order;
import org.example.ui.MenuPanel;

import javax.swing.*;
import java.awt.*;
import org.example.ui.theme.TwoButton;
import org.example.ui.theme.TwoLabel;

public class OrderDialog extends JDialog {
    public OrderDialog(JFrame parent, MenuItem item, MenuPanel menuPanel) {
        super(parent, "PLACE ORDER", true);
        setLayout(new BorderLayout());

        // Display product name and price at the top
        JLabel itemLabel = new JLabel(item.getName() + " - "
                + item.getPrice() + " won", SwingConstants.CENTER);
        add(itemLabel, BorderLayout.NORTH);

        // Place quantity and option panel in the center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configure quantity panel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.add(new TwoLabel("Quantity:"));
        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(model);
        quantitySpinner.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        quantitySpinner.setPreferredSize(new Dimension(50, 25));
        quantityPanel.add(quantitySpinner);

        // Configure option panel
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.add(new TwoLabel("Option:"));
        JCheckBox optionHot = new JCheckBox("HOT");
        optionHot.setFont(new Font("Times New Roman", Font.BOLD, 13));
        optionPanel.add(optionHot);

        // 수량 및 옵션 패널을 중앙 패널에 추가
        centerPanel.add(quantityPanel);
        centerPanel.add(optionPanel);

        add(centerPanel, BorderLayout.CENTER);
        // test

        // Add order button at the bottom
        JPanel buttonPanel = new JPanel();
        TwoButton confirmButton = new TwoButton("Order");

        // Define the action to be executed when the order button is clicked
        confirmButton.addActionListener(e -> {
            // Get the selected quantity from the spinner
            // Create a new order with the selected menu item
            // and quantity and add it to the menu panel
            int quantity = (int) quantitySpinner.getValue();
            menuPanel.addOrder(new Order(item, quantity));
            dispose();
        });
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}

package org.example.ui;

import javax.swing.*;

public class KioskManager {
    private JFrame frame;

    public KioskManager() {
        frame = new JFrame("A TWOSOME PLACE Cafe Kiosk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        // Display initial main panel
        showMainPanel();
        frame.setVisible(true);
    }

    // Method to display the main panel (home screen)
    public void showMainPanel() {
        MainPanel mainPanel = new MainPanel(this);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(mainPanel.getPanel());
        frame.revalidate();
        frame.repaint();
    }

    // Method to display the menu panel (menu selection screen)
    public void showMenuPanel() {
        MenuPanel menuPanel = new MenuPanel(this);
        frame.setContentPane(menuPanel.getPanel());
        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}
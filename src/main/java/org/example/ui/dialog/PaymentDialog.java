package org.example.ui.dialog;

import org.example.model.OrderManager;
import org.example.ui.KioskManager;
import org.example.ui.theme.TwoButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PaymentDialog extends JDialog {

    private JLabel cardLogoLabel;

    public PaymentDialog(JFrame parent, OrderManager orderManager, KioskManager kioskManager) {
        super(parent, "Payment Confirmation", true);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // 상단 로고 패널
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/util/logo4.jpg"))
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.NORTH);

        // 컨텐츠 패널 주위에 패딩을 추가하는 외부 패널
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        outerPanel.setBackground(Color.WHITE);

        // 검은색 테두리가 있는 메인 컨텐츠 패널
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // // Payment amount label
        JLabel amountLabel = new JLabel("Payment Amount: " + orderManager.calculateTotal() + " ₩");
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(amountLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Create panel for card selection and logo
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Card issuer selection panel
        JPanel cardSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cardSelectionPanel.setBackground(Color.WHITE);
        JLabel selectCardLabel = new JLabel("Select Card Issuer:");
        String[] cardIssuers = {"BC Card", "Hyundai Card", "KB Card", "Shinhan Card", "Samsung Pay"};
        JComboBox<String> cardIssuerComboBox = new JComboBox<>(cardIssuers);
        cardSelectionPanel.add(selectCardLabel);
        cardSelectionPanel.add(cardIssuerComboBox);
        cardPanel.add(cardSelectionPanel);

        // 콤보 박스와 로고 사이 최소 간격 추가
        cardPanel.add(Box.createVerticalStrut(5));

        // Card logo display label
        cardLogoLabel = new JLabel();
        cardLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLogoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel logoContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        logoContainerPanel.setBackground(Color.WHITE);
        logoContainerPanel.add(cardLogoLabel);
        cardPanel.add(logoContainerPanel);

        // Add card panel to content panel
        contentPanel.add(cardPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Display initial logo
        updateCardLogo((String) cardIssuerComboBox.getSelectedItem());

        // Update logo when card issuer changes
        cardIssuerComboBox.addActionListener(e -> updateCardLogo((String) cardIssuerComboBox.getSelectedItem()));

        outerPanel.add(contentPanel, BorderLayout.CENTER);
        add(outerPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        // Confirm Payment Button with validation and receipt generation
        TwoButton confirmButton = new TwoButton("Confirm Payment");
        confirmButton.addActionListener(e -> {
            if (cardIssuerComboBox.getSelectedItem() == null) {
                // Display warning message if no card issuer is selected
                JOptionPane.showMessageDialog(this,
                        "Please select a card issuer.",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            } else {
                // Store selected card issuer, close payment window, and show receipt dialog
                String selectedCardIssuer = (String) cardIssuerComboBox.getSelectedItem();
                dispose();
                new ReceiptDialog(parent, orderManager, kioskManager, selectedCardIssuer);
            }
        });
        // Close button to cancel payment process
        TwoButton closeButton = new TwoButton("Close");
        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 다이얼로그 크기 및 위치 조정
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private Map<String, String> getCardLogos() {
        Map<String, String> logos = new HashMap<>();
        logos.put("BC Card", "images/cards/bc.png");
        logos.put("Hyundai Card", "images/cards/hd.png");
        logos.put("KB Card", "images/cards/kb.jpg");
        logos.put("Shinhan Card", "images/cards/sh.png");
        logos.put("Samsung Pay", "images/cards/samsung.png");
        return logos;
    }

    private void updateCardLogo(String cardIssuer) {
        Map<String, String> cardLogos = getCardLogos();
        String logoPath = cardLogos.getOrDefault(cardIssuer, null);

        if (logoPath != null) {
            ImageIcon logoIcon = new ImageIcon(
                    new ImageIcon(getClass().getClassLoader().getResource(logoPath))
                    .getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
            cardLogoLabel.setIcon(logoIcon);
        } else {
            cardLogoLabel.setIcon(null);
        }
    }
}

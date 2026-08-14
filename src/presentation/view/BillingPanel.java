package presentation.view;

import presentation.controller.BillingController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class BillingPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private BillingController billingController;
    private JTextField bimarIdField;

    
    
    public BillingPanel() {
        billingController = new BillingController();
        setTitle("صورتحساب بیمار");
        setSize(500, 380);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createForm() {
        JLabel bimarIdLabel = new JLabel("شماره بیمار:");
        bimarIdField = new JTextField();
        JButton adiButton = new JButton("صدور صورتحساب عادی");
        JButton jarahiButton = new JButton("صدور صورتحساب جراحی");
        JButton emergencyButton = new JButton("صدور صورتحساب اورژانسی");
        JButton viewButton = new JButton("مشاهده صورتحسابها");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        bimarIdField.setPreferredSize(fieldSize);
        bimarIdField.setMaximumSize(fieldSize);
        
        Dimension buttonSize = new Dimension(200, 40);

        adiButton.setPreferredSize(buttonSize);
        adiButton.setMaximumSize(buttonSize);
        adiButton.setMinimumSize(buttonSize);
        jarahiButton.setPreferredSize(buttonSize);
        jarahiButton.setMaximumSize(buttonSize);
        jarahiButton.setMinimumSize(buttonSize);

        emergencyButton.setPreferredSize(buttonSize);
        emergencyButton.setMaximumSize(buttonSize);
        emergencyButton.setMinimumSize(buttonSize);

        viewButton.setPreferredSize(buttonSize);
        viewButton.setMaximumSize(buttonSize);
        viewButton.setMinimumSize(buttonSize);
        
        
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);
        

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        bimarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bimarIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        adiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jarahiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        emergencyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(bimarIdLabel);
        panel.add(bimarIdField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(adiButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(jarahiButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(emergencyButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(viewButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(exitButton);

        add(panel);

        adiButton.addActionListener(e -> sabtBil(1));
        jarahiButton.addActionListener(e -> sabtBil(2));
        emergencyButton.addActionListener(e -> sabtBil(3));
        viewButton.addActionListener(e -> namayeshBil());
        exitButton.addActionListener(e -> dispose());
    }

    private int getBimarId() {
        return Integer.parseInt(bimarIdField.getText());
    }

    private void sabtBil(int noe) {
        try {
            int bimarId = getBimarId();
            boolean natije;
            if (noe == 1) {
                natije = billingController.sabtBilAdi(bimarId);
            } else if (noe == 2) {
                natije = billingController.sabtBilJarahi(bimarId);
            } else {
                natije = billingController.sabtBilEmergency(bimarId);
            }

            String payam = billingController.getLastMessage();
            JOptionPane.showMessageDialog(this, payam != null ? payam
                    : (natije ? "صورتحساب با موفقیت صادر شد." : "صدور صورتحساب انجام نشد."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار را به صورت عدد وارد کنید.");
        }
    }

    
    
    
    private void namayeshBil() {
        try {
            int bimarId = getBimarId();
            ArrayList<String> bilHa = billingController.getSoorteshesab(bimarId);
            if (bilHa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "برای این بیمار صورتحساب ثبت نشده است.");
            } else {
                JOptionPane.showMessageDialog(this, String.join("\n", bilHa), "صورتحسابها", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار را به صورت عدد وارد کنید.");
        }
    }
}

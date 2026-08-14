package presentation.view;


import presentation.controller.EmergencyController;
import javax.swing.*;
import java.awt.*;

public class EmergencyPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private EmergencyController emergencyController;
    private JTextField bimarIdField;

    public EmergencyPanel() {
        emergencyController = new EmergencyController();
        setTitle("حالت اورژانس");
        setSize(400, 290);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createForm() {
        JLabel bimarIdLabel = new JLabel("شماره بیمار:");
        bimarIdField = new JTextField();
        JButton startButton = new JButton("فعال کردن اورژانس");
        JButton endButton = new JButton("پایان اورژانس");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        bimarIdField.setPreferredSize(fieldSize);
        bimarIdField.setMaximumSize(fieldSize);
        
        Dimension buttonSize = new Dimension(200, 40);

        startButton.setPreferredSize(buttonSize);
        startButton.setMaximumSize(buttonSize);
        startButton.setMinimumSize(buttonSize);
        endButton.setPreferredSize(buttonSize);
        endButton.setMaximumSize(buttonSize);
        endButton.setMinimumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);
        

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        bimarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bimarIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(25));
        panel.add(bimarIdLabel);
        panel.add(bimarIdField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(endButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        add(panel);

        startButton.addActionListener(e -> changeEmergency(true));
        endButton.addActionListener(e -> changeEmergency(false));
        exitButton.addActionListener(e -> dispose());
    }

    private void changeEmergency(boolean start) {
        try {
            int bimarId = Integer.parseInt(bimarIdField.getText());
            boolean natije = start
                    ? emergencyController.faalKardanEmergency(bimarId)
                    : emergencyController.payanEmergency(bimarId);

            String payam = emergencyController.getLastMessage();
            JOptionPane.showMessageDialog(this, payam != null ? payam
                    : (natije ? "عملیات با موفقیت انجام شد." : "عملیات انجام نشد."));

            String hoshdar = emergencyController.getLastAlertMessage();
            if (hoshdar != null) {
                JOptionPane.showMessageDialog(this, hoshdar, "هشدار بحرانی", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار را به صورت عدد وارد کنید.");
        }
    }
}

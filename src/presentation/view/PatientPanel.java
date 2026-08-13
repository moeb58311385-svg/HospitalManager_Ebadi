package presentation.view;

import presentation.controller.PatientController;
import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JFrame {

  
	private static final long serialVersionUID = -137296375048645861L;
	private PatientController patientController;
    private JTextField naamField;
    private JTextField meliField;
    private JTextField senField;

    public PatientPanel() {
        patientController = new PatientController();
        setTitle("ثبت بیمار جدید");
        setSize(400, 350);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createForm() {
        JLabel naamLabel = new JLabel("نام بیمار:");
        JLabel meliLabel = new JLabel("شماره ملی:");
        JLabel senLabel = new JLabel("سن:");
        naamField = new JTextField();
        meliField = new JTextField();
        senField = new JTextField();
        JButton sabtButton = new JButton("ثبت بیمار");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        naamField.setPreferredSize(fieldSize);
        naamField.setMaximumSize(fieldSize);

        meliField.setPreferredSize(fieldSize);
        meliField.setMaximumSize(fieldSize);

        senField.setPreferredSize(fieldSize);
        senField.setMaximumSize(fieldSize);

        Dimension buttonSize = new Dimension(200, 40);
        sabtButton.setPreferredSize(buttonSize);
        sabtButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        naamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        naamField.setAlignmentX(Component.CENTER_ALIGNMENT);
        meliLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        meliField.setAlignmentX(Component.CENTER_ALIGNMENT);
        senLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sabtButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(naamLabel);
        panel.add(naamField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(meliLabel);
        panel.add(meliField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(senLabel);
        panel.add(senField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(sabtButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        sabtButton.addActionListener(e -> sabtBimar());
        exitButton.addActionListener(e -> dispose());
    }

    private void sabtBimar() {
        try {
            String naam = naamField.getText();
            String meli = meliField.getText();
            int sen = Integer.parseInt(senField.getText());

            boolean natije = patientController.sabtBimar(naam, meli, sen);

            if (natije) {
                JOptionPane.showMessageDialog(this, "بیمار با موفقیت ثبت شد.");
                naamField.setText("");
                meliField.setText("");
                senField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "ثبت بیمار انجام نشد.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا سن را به صورت عدد وارد کنید.");
        }
    }
}

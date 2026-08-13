package presentation.view;

import presentation.controller.AppointmentController;
import javax.swing.*;
import java.awt.*;

public class VisitPanel extends JFrame {

	private static final long serialVersionUID = -329046268705248246L;
	private AppointmentController appointmentController;
    private JTextField nobatIdField;

    public VisitPanel() {
        appointmentController = new AppointmentController();
        setTitle("انجام ویزیت");
        setSize(350, 220);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createForm() {
        JLabel nobatIdLabel = new JLabel("شماره نوبت:");
        nobatIdField = new JTextField();
        JButton vizitButton = new JButton("انجام ویزیت");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        nobatIdField.setPreferredSize(fieldSize);
        nobatIdField.setMaximumSize(fieldSize);

        Dimension buttonSize = new Dimension(200, 40);
        vizitButton.setPreferredSize(buttonSize);
        vizitButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nobatIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nobatIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        vizitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(nobatIdLabel);
        panel.add(nobatIdField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(vizitButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        vizitButton.addActionListener(e -> anjamVizit());
        exitButton.addActionListener(e -> dispose());
    }

    private void anjamVizit() {
        try {
            int nobatId = Integer.parseInt(nobatIdField.getText());

            boolean natije = appointmentController.anjamVizit(nobatId);

            if (natije) {
                JOptionPane.showMessageDialog(this, "ویزیت با موفقیت انجام شد.");
                nobatIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "انجام ویزیت با مشکل مواجه شد. اطلاعات بیشتر را در کنسول ببینید.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره نوبت را به صورت عدد وارد کنید.");
        }
    }
}

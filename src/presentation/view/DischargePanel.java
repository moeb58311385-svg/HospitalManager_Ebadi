package presentation.view;

import presentation.controller.AdmissionController;
import business.model.Department;
import data.DepartmentDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DischargePanel extends JFrame {

   
	private static final long serialVersionUID = -4639395985839851633L;
	private AdmissionController admissionController;
    private JTextField bimarIdField;
    private JTextField bakhshIdField;

    public DischargePanel() {
        admissionController = new AdmissionController();
        setTitle("ترخیص بیمار");
        setSize(400, 420);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private JScrollPane createDepartmentGuide() {
        JTextArea guideArea = new JTextArea();
        guideArea.setEditable(false);
        guideArea.setLineWrap(true);
        guideArea.setBackground(new Color(240, 240, 240));

        StringBuilder sb = new StringBuilder("لیست بخش‌ها (شماره - نام - ظرفیت):\n");
        ArrayList<Department> bakhshHa = new DepartmentDAO().getAllDepartments();
        if (bakhshHa.isEmpty()) {
            sb.append("بخشی یافت نشد.");
        } else {
            for (Department d : bakhshHa) {
                sb.append(d.getId()).append(" - ").append(d.getNaamBakhsh())
                  .append(" - ظرفیت: ").append(d.getZarfiat()).append("\n");
            }
        }
        guideArea.setText(sb.toString());
        guideArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JScrollPane scrollPane = new JScrollPane(guideArea);
        scrollPane.setPreferredSize(new Dimension(360, 100));
        return scrollPane;
    }

    private void createForm() {
        JLabel bimarIdLabel = new JLabel("شماره بیمار:");
        JLabel bakhshIdLabel = new JLabel("شماره بخش:");

        bimarIdField = new JTextField();
        bakhshIdField = new JTextField();

        JButton tarkhisButton = new JButton("مرخص کن");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        bimarIdField.setPreferredSize(fieldSize);
        bimarIdField.setMaximumSize(fieldSize);

        bakhshIdField.setPreferredSize(fieldSize);
        bakhshIdField.setMaximumSize(fieldSize);

        Dimension buttonSize = new Dimension(200, 40);
        tarkhisButton.setPreferredSize(buttonSize);
        tarkhisButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane guidePane = createDepartmentGuide();
        guidePane.setAlignmentX(Component.CENTER_ALIGNMENT);

        bimarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bimarIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        bakhshIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bakhshIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarkhisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(guidePane);
        panel.add(Box.createVerticalStrut(15));
        panel.add(bimarIdLabel);
        panel.add(bimarIdField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(bakhshIdLabel);
        panel.add(bakhshIdField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(tarkhisButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        tarkhisButton.addActionListener(e -> tarkhisBimar());
        exitButton.addActionListener(e -> dispose());
    }

    private void tarkhisBimar() {
        try {
            int bimarId = Integer.parseInt(bimarIdField.getText());
            int bakhshId = Integer.parseInt(bakhshIdField.getText());

            boolean natije = admissionController.tarkhisBimar(bimarId, bakhshId);

            if (natije) {
                JOptionPane.showMessageDialog(this, "بیمار با موفقیت مرخص شد.");
                bimarIdField.setText("");
                bakhshIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "ترخیص بیمار انجام نشد.اطلاعات بیشتر را در کنسول ببینید.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار و شماره بخش را به صورت عدد وارد کنید.");
        }
    }
}

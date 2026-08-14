package presentation.view;
import presentation.controller.AdmissionController;
import business.model.Department;
import data.DepartmentDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class AdmissionPanel extends JFrame {

   
	private static final long serialVersionUID = 1590299647899258898L;
    private AdmissionController admissionController;
    private JTextField bimarIdField;
    private JTextField bakhshIdField;
    public AdmissionPanel() {
        admissionController = new AdmissionController();
        setTitle("پذیرش و بستری بیمار");
        setSize(400, 400);
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
        JButton bastariButton = new JButton("بستری کن");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        bimarIdField.setPreferredSize(fieldSize);
        bimarIdField.setMaximumSize(fieldSize);

        bakhshIdField.setPreferredSize(fieldSize);
        bakhshIdField.setMaximumSize(fieldSize);

        Dimension buttonSize = new Dimension(200, 40);
        bastariButton.setPreferredSize(buttonSize);
        bastariButton.setMaximumSize(buttonSize);
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
        bastariButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        panel.add(bastariButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        bastariButton.addActionListener(e -> bastariBimar());
        exitButton.addActionListener(e -> dispose());
    }
    
    
    
    private void bastariBimar() {
        try {
            int bimarId = Integer.parseInt(bimarIdField.getText());
            int bakhshId = Integer.parseInt(bakhshIdField.getText());
            boolean natije = admissionController.bastariBimar(bimarId, bakhshId);
            String payam = admissionController.getLastMessage();
            if (natije) {
                JOptionPane.showMessageDialog(this, payam != null ? payam : "بیمار با موفقیت بستری شد.");
                bimarIdField.setText("");
                bakhshIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, payam != null ? payam : "بستری بیمار انجام نشد.",
                        "خطا", JOptionPane.ERROR_MESSAGE);
            }

            String hoshdar = admissionController.getLastAlertMessage();
            if (hoshdar != null) {
                JOptionPane.showMessageDialog(this, hoshdar, "هشدار بحرانی", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار و شماره بخش را به صورت عدد وارد کنید.");
        }
    }
}
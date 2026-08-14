package presentation.view;


import presentation.controller.AppointmentController;
import business.model.Doctor;
import data.DoctorDAO;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AppointmentPanel extends JFrame {

  
	private static final long serialVersionUID = 8166105585392082907L;
	private AppointmentController appointmentController;
    private JTextField bimarIdField;
    private JTextField doctorIdField;
    private JTextField tarikhField;
    private JTextField saatField;

    public AppointmentPanel() {
        appointmentController = new AppointmentController();
        setTitle("ثبت نوبت");
        setSize(430, 440);
        setLocationRelativeTo(null);
        createForm();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
    
    

    private JScrollPane createDoctorGuide() {
        JTextArea guideArea = new JTextArea();
        guideArea.setEditable(false);
        guideArea.setLineWrap(true);
        guideArea.setBackground(new Color(240, 240, 240));

        StringBuilder sb = new StringBuilder("لیست پزشکان (شماره - نام - تخصص - بخش - ساعت کاری):\n");
        ArrayList<Doctor> pezeshkan = new DoctorDAO().getAllDoctors();
        if (pezeshkan.isEmpty()) {
            sb.append("پزشکی یافت نشد.");
        } else {
            for (Doctor d : pezeshkan) {
                sb.append(d.getId()).append(" - ").append(d.getNaam())
                  .append(" - ").append(d.getTakhasos())
                  .append(" - ").append(d.getNaamBakhsh())
                  .append(" - ساعت ").append(d.getSaatShoru())
                  .append(" تا ").append(d.getSaatPayan()).append("\n");
            }
        }
        guideArea.setText(sb.toString());
        guideArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JScrollPane scrollPane = new JScrollPane(guideArea);
        scrollPane.setPreferredSize(new Dimension(390, 130));
        return scrollPane;
    }

    
    
    
    private void createForm() {
        JLabel bimarIdLabel = new JLabel("شماره بیمار:");
        JLabel doctorIdLabel = new JLabel("شماره پزشک:");
        JLabel tarikhLabel = new JLabel("تاریخ (yyyy-mm-dd):");
        JLabel saatLabel = new JLabel("ساعت:");
        bimarIdField = new JTextField();
        doctorIdField = new JTextField();
        tarikhField = new JTextField();
        saatField = new JTextField();
        JButton sabtButton = new JButton("ثبت نوبت");
        JButton exitButton = new JButton("خروج");

        Dimension fieldSize = new Dimension(200, 35);
        bimarIdField.setPreferredSize(fieldSize);
        bimarIdField.setMaximumSize(fieldSize);
        doctorIdField.setPreferredSize(fieldSize);
        doctorIdField.setMaximumSize(fieldSize);
        tarikhField.setPreferredSize(fieldSize);
        tarikhField.setMaximumSize(fieldSize);
        saatField.setPreferredSize(fieldSize);
        saatField.setMaximumSize(fieldSize);

        
        
        
        Dimension buttonSize = new Dimension(200, 40);
        sabtButton.setPreferredSize(buttonSize);
        sabtButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane guidePane = createDoctorGuide();
        guidePane.setAlignmentX(Component.CENTER_ALIGNMENT);

        bimarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bimarIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarikhLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarikhField.setAlignmentX(Component.CENTER_ALIGNMENT);
        saatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saatField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sabtButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(guidePane);
        panel.add(Box.createVerticalStrut(15));
        panel.add(bimarIdLabel);
        panel.add(bimarIdField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(doctorIdLabel);
        panel.add(doctorIdField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(tarikhLabel);
        panel.add(tarikhField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(saatLabel);
        panel.add(saatField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(sabtButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        sabtButton.addActionListener(e -> sabtNobat());
        exitButton.addActionListener(e -> dispose());
    }

    
    
    private void sabtNobat() {
        try {
            int bimarId = Integer.parseInt(bimarIdField.getText());
            int doctorId = Integer.parseInt(doctorIdField.getText());
            LocalDate tarikh = LocalDate.parse(tarikhField.getText());
            int saat = Integer.parseInt(saatField.getText());

            boolean natije = appointmentController.sabtNobat(bimarId, doctorId, tarikh, saat);

            String payam = appointmentController.getLastMessage();
            if (natije) {
                JOptionPane.showMessageDialog(this, payam != null ? payam : "نوبت با موفقیت ثبت شد.");
                bimarIdField.setText("");
                doctorIdField.setText("");
                tarikhField.setText("");
                saatField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, payam != null ? payam : "ثبت نوبت انجام نشد.",
                        "خطا", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "لطفا تاریخ را به فرمت yyyy-mm-dd وارد کنید (به طور مثال 2026-08-05).");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بیمار، شماره پزشک و ساعت را به صورت عدد وارد کنید.");
        }
    }
}

package presentation.view;

import presentation.controller.DepartmentController;
import presentation.controller.StaffController;
import business.model.Department;
import business.model.Staff;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class DepartmentPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private DepartmentController departmentController;
    private StaffController staffController;
    private JTextField bakhshNaamField;
    private JTextField bakhshZarfiatField;
    private JTextArea bakhshListArea;
    private JTextField personelNaamField;
    private JTextField personelSematField;
    private JTextField personelBakhshIdField;
    private JTextArea personelListArea;

    public DepartmentPanel() {
        departmentController = new DepartmentController();
        staffController = new StaffController();
        setTitle("مدیریت بخش‌ها و پرسنل");
        setSize(480, 560);
        setLocationRelativeTo(null);
        createTabs();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("بخش‌ها", createDepartmentTab());
        tabs.add("پرسنل", createStaffTab());
        add(tabs);
        refreshDepartmentList();
        refreshStaffList();
    }

    private JPanel createDepartmentTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel naamLabel = new JLabel("نام بخش جدید:");
        bakhshNaamField = new JTextField();
        JLabel zarfiatLabel = new JLabel("ظرفیت:");
        bakhshZarfiatField = new JTextField();
        JButton addButton = new JButton("افزودن بخش");
        JButton exitButton = new JButton("خروج");

        bakhshListArea = new JTextArea();
        bakhshListArea.setEditable(false);
        bakhshListArea.setLineWrap(true);
        bakhshListArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JScrollPane scrollPane = new JScrollPane(bakhshListArea);
        scrollPane.setPreferredSize(new Dimension(400, 280));

        Dimension fieldSize = new Dimension(200, 35);
        bakhshNaamField.setMaximumSize(fieldSize);
        bakhshNaamField.setPreferredSize(fieldSize);
        bakhshZarfiatField.setMaximumSize(fieldSize);
        bakhshZarfiatField.setPreferredSize(fieldSize);
        
        Dimension buttonSize = new Dimension(200, 40);
        addButton.setPreferredSize(buttonSize);
        addButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        
        
        naamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bakhshNaamField.setAlignmentX(Component.CENTER_ALIGNMENT);
        zarfiatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bakhshZarfiatField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(10));
        panel.add(naamLabel);
        panel.add(bakhshNaamField);
        panel.add(Box.createVerticalStrut(8));
        panel.add(zarfiatLabel);
        panel.add(bakhshZarfiatField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(addButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("لیست بخش‌های موجود:"));
        panel.add(scrollPane);

        addButton.addActionListener(e -> sabtBakhsh());
        exitButton.addActionListener(e -> dispose());

        return panel;
    }
    
    

    private JPanel createStaffTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // راست‌چین کردن کل پنل

        JLabel naamLabel = new JLabel("نام پرسنل:");
        personelNaamField = new JTextField();
        JLabel sematLabel = new JLabel("سمت (مثال: پرستار):");
        personelSematField = new JTextField();
        JLabel bakhshIdLabel = new JLabel("شماره بخش:");
        personelBakhshIdField = new JTextField();
        JButton addButton = new JButton("افزودن پرسنل");
        JButton exitButton = new JButton("خروج");

        personelListArea = new JTextArea();
        personelListArea.setEditable(false);
        personelListArea.setLineWrap(true);
        personelListArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JScrollPane scrollPane = new JScrollPane(personelListArea);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        Dimension fieldSize = new Dimension(200, 35);
        personelNaamField.setMaximumSize(fieldSize);
        personelNaamField.setPreferredSize(fieldSize);
        personelSematField.setMaximumSize(fieldSize);
        personelSematField.setPreferredSize(fieldSize);
        personelBakhshIdField.setMaximumSize(fieldSize);
        personelBakhshIdField.setPreferredSize(fieldSize);

        Dimension buttonSize = new Dimension(200, 40);
        addButton.setPreferredSize(buttonSize);
        addButton.setMaximumSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        // **همه کامپوننت‌ها رو وسط‌چین می‌کنیم**
        naamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personelNaamField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sematLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personelSematField.setAlignmentX(Component.CENTER_ALIGNMENT);
        bakhshIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personelBakhshIdField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // **لیبل "لیست پرسنل..." رو هم وسط‌چین می‌کنیم**
        JLabel infoLabel = new JLabel("لیست پرسنل و پزشکان به تفکیک بخش را می‌توانید در «مشاهده گزارشات» ببینید.");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(10));
        panel.add(naamLabel);
        panel.add(personelNaamField);
        panel.add(Box.createVerticalStrut(8));
        panel.add(sematLabel);
        panel.add(personelSematField);
        panel.add(Box.createVerticalStrut(8));
        panel.add(bakhshIdLabel);
        panel.add(personelBakhshIdField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(addButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(infoLabel);  // استفاده از متغیر جداگانه
        panel.add(scrollPane);

        addButton.addActionListener(e -> sabtPersonel());
        exitButton.addActionListener(e -> dispose());

        return panel;
    }
   
    
    private void sabtBakhsh() {
        try {
            String naam = bakhshNaamField.getText();
            int zarfiat = Integer.parseInt(bakhshZarfiatField.getText());
            boolean natije = departmentController.sabtBakhsh(naam, zarfiat);
            String payam = departmentController.getLastMessage();

            JOptionPane.showMessageDialog(this, payam != null ? payam
                    : (natije ? "بخش با موفقیت اضافه شد." : "افزودن بخش انجام نشد."));

            if (natije) {
                bakhshNaamField.setText("");
                bakhshZarfiatField.setText("");
                refreshDepartmentList();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا ظرفیت را به طور عددی وارد کنید.");
        }
    }

    private void sabtPersonel() {
        try {
            String naam = personelNaamField.getText();
            String semat = personelSematField.getText();
            String bakhshIdText = personelBakhshIdField.getText().trim();
            int bakhshId = bakhshIdText.isEmpty() ? 0 : Integer.parseInt(bakhshIdText);

            boolean natije = staffController.sabtPersonel(naam, semat, bakhshId);
            String payam = staffController.getLastMessage();

            JOptionPane.showMessageDialog(this, payam != null ? payam
                    : (natije ? "پرسنل با موفقیت ثبت شد." : "ثبت پرسنل انجام نشد."));

            if (natije) {
                personelNaamField.setText("");
                personelSematField.setText("");
                personelBakhshIdField.setText("");
                refreshStaffList();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "لطفا شماره بخش را به صورت عدد وارد کنید.");
        }
    }
    
    
    
    

    private void refreshDepartmentList() {
        ArrayList<Department> bakhshHa = departmentController.namayeshBakhshHa();
        StringBuilder sb = new StringBuilder();
        if (bakhshHa.isEmpty()) {
            sb.append("بخشی یافت نشد.");
        } else {
            for (Department d : bakhshHa) {
                sb.append(d.getId()).append(" - ").append(d.getNaamBakhsh())
                  .append(" - ظرفیت: ").append(d.getZarfiat()).append("\n");
            }
        }
        bakhshListArea.setText(sb.toString());
    }
    
    

    private void refreshStaffList() {
        ArrayList<Staff> personelList = staffController.namayeshPersonel();
        StringBuilder sb = new StringBuilder();
        if (personelList.isEmpty()) {
            sb.append("پرسنلی یافت نشد.");
        } else {
            for (Staff s : personelList) {
                sb.append(s.getId()).append(" - ").append(s.getNaam())
                  .append(" - ").append(s.getSemat())
                  .append(" - بخش: ").append(s.getNaamBakhsh() != null ? s.getNaamBakhsh() : "نامشخص")
                  .append("\n");
            }
        }
        personelListArea.setText(sb.toString());
    }
}

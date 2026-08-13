package presentation.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

 
	private static final long serialVersionUID = 3770858136515257675L;

	public MainFrame() {
        setTitle("سیستم مدیریت بیمارستان");
        setSize(300, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createMenu() {
        //JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(7, 1, 10, 10));

        JButton bimarButton = new JButton("ثبت بیمار جدید");
        JButton bastariButton = new JButton("پذیرش و بستری بیمار");
        JButton nobatButton = new JButton("ثبت نوبت");
        JButton vizitButton = new JButton("انجام ویزیت");
        JButton tarkhisButton = new JButton("ترخیص بیمار");
        JButton reportButton = new JButton("مشاهده گزارشات");
        JButton billingButton = new JButton("صورتحساب بیمار");
        JButton emergencyButton = new JButton("حالت اورژانس");
        JButton exitButton = new JButton("خروج");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Dimension buttonSize = new Dimension(200, 40);
  
        
        bimarButton.setPreferredSize(buttonSize);
        bimarButton.setMaximumSize(buttonSize);
        bimarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bastariButton.setPreferredSize(buttonSize);
        bastariButton.setMaximumSize(buttonSize);
        bastariButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nobatButton.setPreferredSize(buttonSize);
        nobatButton.setMaximumSize(buttonSize);
        nobatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        vizitButton.setPreferredSize(buttonSize);
        vizitButton.setMaximumSize(buttonSize);
        vizitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tarkhisButton.setPreferredSize(buttonSize);
        tarkhisButton.setMaximumSize(buttonSize);
        tarkhisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        reportButton.setPreferredSize(buttonSize);
        reportButton.setMaximumSize(buttonSize);
        reportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        billingButton.setPreferredSize(buttonSize);
        billingButton.setMaximumSize(buttonSize);
        billingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        emergencyButton.setPreferredSize(buttonSize);
        emergencyButton.setMaximumSize(buttonSize);
        emergencyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        panel.add(Box.createVerticalGlue());
        	
        panel.add(bimarButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(bastariButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nobatButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(vizitButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(tarkhisButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(reportButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(billingButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(emergencyButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        
        panel.add(Box.createVerticalGlue());

        add(panel);

        exitButton.addActionListener(e -> System.exit(0));

        bimarButton.addActionListener(e -> {
            PatientPanel panelBimar = new PatientPanel();
            panelBimar.setVisible(true);
        });

        bastariButton.addActionListener(e -> {
            AdmissionPanel admissionPanel = new AdmissionPanel();
            admissionPanel.setVisible(true);
        });

        nobatButton.addActionListener(e -> {
            AppointmentPanel appointmentPanel = new AppointmentPanel();
            appointmentPanel.setVisible(true);
        });

        vizitButton.addActionListener(e -> {
            VisitPanel visitPanel = new VisitPanel();
            visitPanel.setVisible(true);
        });

        tarkhisButton.addActionListener(e -> {
            DischargePanel dischargePanel = new DischargePanel();
            dischargePanel.setVisible(true);
        });

        reportButton.addActionListener(e -> {
            ReportPanel reportPanel = new ReportPanel();
            reportPanel.setVisible(true);
        });

        billingButton.addActionListener(e -> {
            BillingPanel billingPanel = new BillingPanel();
            billingPanel.setVisible(true);
        });

        emergencyButton.addActionListener(e -> {
            EmergencyPanel emergencyPanel = new EmergencyPanel();
            emergencyPanel.setVisible(true);
        });
    }
}

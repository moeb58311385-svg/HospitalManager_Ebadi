package presentation.view;



import presentation.controller.ReportController;
import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JFrame {

  
	private static final long serialVersionUID = 8443659046594203883L;
	private ReportController reportController;

    public ReportPanel() {
        reportController = new ReportController();
        setTitle("گزارش بیمارستان");
        setSize(560, 620);
        setLocationRelativeTo(null);
        createPanel();
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    private void createPanel() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JButton reportButton = new JButton("نمایش گزارش");
        JButton exitButton = new JButton("خروج");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(reportButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        exitButton.addActionListener(e -> dispose());

        reportButton.addActionListener(e -> {
            String gozaresh = reportController.getGozareshMatni();
            textArea.setText(gozaresh);
            textArea.setCaretPosition(0);
        });
    }
}
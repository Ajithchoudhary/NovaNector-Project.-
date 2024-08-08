import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculatorGUI extends JFrame {
    private JTextField totalField, partField, percentageField;
    private JLabel resultLabel;
    private JComboBox<String> operationComboBox;

    public PercentageCalculatorGUI() {
        setTitle("Percentage Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel operationLabel = new JLabel("Select Operation:");
        operationComboBox = new JComboBox<>(new String[]{
                "Calculate Percentage", 
                "Percentage Increase", 
                "Percentage Decrease", 
                "Find Whole from Part and Percentage"
        });
        operationComboBox.setSelectedIndex(0);

        JLabel totalLabel = new JLabel("Total:");
        totalField = new JTextField();

        JLabel partLabel = new JLabel("Part:");
        partField = new JTextField();

        JLabel percentageLabel = new JLabel("Percentage:");
        percentageField = new JTextField();

        resultLabel = new JLabel("Result: ");
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());

        panel.add(operationLabel);
        panel.add(operationComboBox);
        panel.add(totalLabel);
        panel.add(totalField);
        panel.add(partLabel);
        panel.add(partField);
        panel.add(percentageLabel);
        panel.add(percentageField);
        panel.add(new JLabel());
        panel.add(calculateButton);
        panel.add(new JLabel());
        panel.add(resultLabel);

        add(panel);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String operation = (String) operationComboBox.getSelectedItem();
                double total = totalField.getText().isEmpty() ? 0 : Double.parseDouble(totalField.getText());
                double part = partField.getText().isEmpty() ? 0 : Double.parseDouble(partField.getText());
                double percentage = percentageField.getText().isEmpty() ? 0 : Double.parseDouble(percentageField.getText());

                double result = 0;
                switch (operation) {
                    case "Calculate Percentage":
                        result = (part / total) * 100;
                        break;
                    case "Percentage Increase":
                        result = ((part - total) / total) * 100;
                        break;
                    case "Percentage Decrease":
                        result = ((total - part) / total) * 100;
                        break;
                    case "Find Whole from Part and Percentage":
                        result = (part / percentage) * 100;
                        break;
                }
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PercentageCalculatorGUI calculator = new PercentageCalculatorGUI();
            calculator.setVisible(true);
        });
    }
}

package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDetailsGUI extends JFrame {
    
    static double sssContribution;
    static double philhealthContribution;
    static double pagIbigContribution;
    static double employeeTax;

    private JTextArea textArea;
    private JTextField employeeNumberField;

    public EmployeeDetailsGUI() {
        setTitle("Employee Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create UI elements
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        JLabel employeeNumberLabel = new JLabel("Enter employee number:");
        employeeNumberField = new JTextField(10);
        JButton displayButton = new JButton("Display Employee Details");
        JButton calculateButton = new JButton("Calculate Salary");
        
        topPanel.add(employeeNumberLabel);
        topPanel.add(employeeNumberField);
        topPanel.add(displayButton);
        topPanel.add(calculateButton);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);

        // Add action listeners
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayEmployeeDetails();
            }
        });
        
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateSalary();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDetailsGUI().setVisible(true);
            }
        });
    }

    public void calculateSalary() {
        String csvFilePath = "src/csvfiles/salarycalc.csv";
        int targetLine = Integer.parseInt(employeeNumberField.getText());
        int lineNumber = -1;
        textArea.setText("");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == targetLine) {
                    String[] parts = line.split(",");
                    textArea.append(String.format("Employee ID: %s\nFirst Name: %s\nLast Name: %s\n", parts[0], parts[2], parts[1]));
                    textArea.append(String.format("Basic Salary: %s\nGross Semi-monthly Rate: %s\nHourly Rate: %s\n", parts[10], parts[11], parts[12]));
                    textArea.append(String.format("SSS#: %s\nPhilhealth#: %s\nTIN#: %s\nPagIbig#: %s\n", parts[3], parts[4], parts[5], parts[6]));
                    textArea.append(String.format("Rice Subsidy: %s\nPhone Allowance: %s\nClothing Allowance: %s\n", parts[7], parts[8], parts[9]));

                    double basicSalary = Double.parseDouble(parts[10].replace(" ", ""));
                    double grossRate = Double.parseDouble(parts[11].replace(" ", ""));
                    double hourlyRate = Double.parseDouble(parts[12].replace(" ", ""));

                    double riceSubsidy = Double.parseDouble(parts[7]);
                    double phoneAllowance = Double.parseDouble(parts[8]);
                    double clothingAllowance = Double.parseDouble(parts[9]);

                    double totalAllowances = riceSubsidy + phoneAllowance + clothingAllowance;
                    textArea.append("Total Allowances: " + totalAllowances + "\n");

                    calculateSSSContribution(basicSalary);
                    calculatePhilHealthContribution(basicSalary);
                    calculatePagIbigContribution(basicSalary);
                    calculateEmployeeTax(grossRate);

                    double grossDeduction = sssContribution + philhealthContribution + pagIbigContribution + employeeTax;
                    textArea.append("Gross Deduction: " + grossDeduction + "\n");

                    textArea.append("SSS Contribution: " + sssContribution + "\n");
                    textArea.append("PhilHealth Contribution: " + philhealthContribution + "\n");
                    textArea.append("Pag-IBIG Contribution: " + pagIbigContribution + "\n");
                    textArea.append("Employee Tax: " + employeeTax + "\n");

                    textArea.append(String.format("Gross Income: \t\t%,.2f\n", grossRate));
                    textArea.append(String.format("Benefits: \t\t%,.2f\n", totalAllowances));
                    textArea.append(String.format("Total Deductions: \t%,.2f\n", grossDeduction));
                    textArea.append(String.format("TAKE HOME PAY: \t\t%,.2f\n", (grossRate + totalAllowances - grossDeduction)));

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayEmployeeDetails() {
        String csvFilePath = "src/csvfiles/Employeedetails.csv";
        int targetLine = Integer.parseInt(employeeNumberField.getText());
        int lineNumber = -1;
        textArea.setText("");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == targetLine) {
                    String[] parts = line.split(",");
                    textArea.append(String.format("Employee ID: %s\nFirst Name: %s\nLast Name: %s\n", parts[0], parts[2], parts[1]));
                    textArea.append(String.format("Birthday: %s\nAddress: %s\nPhone Number: %s\n", parts[3], parts[4], parts[5]));
                    textArea.append(String.format("Status: %s\nPosition: %s\nImmediate Supervisor: %s\n", parts[10], parts[11], parts[12]));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void calculateSSSContribution(double basicSalary) {
        // Calculation logic as provided in the original code...
    }

    static void calculatePhilHealthContribution(double basicSalary) {
        // Calculation logic as provided in the original code...
    }

    static void calculatePagIbigContribution(double basicSalary) {
        // Calculation logic as provided in the original code...
    }

    static void calculateEmployeeTax(double grossRate) {
        // Calculation logic as provided in the original code...
    }
}

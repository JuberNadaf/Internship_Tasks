import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryToDecimalConverter extends JFrame implements ActionListener {
    private JTextField binaryTextField;
    private JLabel decimalLabel;

    public BinaryToDecimalConverter() {
        setTitle("Binary to Decimal Converter");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel binaryLabel = new JLabel("Enter Binary Number:");
        binaryTextField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        decimalLabel = new JLabel("Decimal Number:");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(binaryLabel);
        panel.add(binaryTextField);
        panel.add(convertButton);
        panel.add(decimalLabel);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            String binaryString = binaryTextField.getText();
            try {
                int decimalNumber = Integer.parseInt(binaryString, 2);
                decimalLabel.setText("Decimal Number: " + decimalNumber);
            } catch (NumberFormatException ex) {
                decimalLabel.setText("Invalid binary number!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BinaryToDecimalConverter converter = new BinaryToDecimalConverter();
                converter.setVisible(true);
            }
        });
    }
}

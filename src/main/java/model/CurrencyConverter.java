package model;

import service.CurrencyConverterService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;



// need to make a GUI window/frame
public class CurrencyConverter extends JFrame {

    private JTextField txtAmount;  //user enters the dollar amount
    private JComboBox<String> cmbFromCurrency; //a combo box component that allows the user to select the currency they want to convert from.
    private JComboBox<String> cmbToCurrency; //a combo box component that allows the user to select the currency they want to convert to.
    private JLabel lblConversion; //a label component that will display the conversion.
    private JLabel lblTimestamp; //a label component that will display the timestamp.
    private CurrencyConverterService converterService; //an instance of the CurrencyConverterService class that will perform conversions and fetch live exchange rates.


    //set up the constructor for the CurrencyConverter class
    public CurrencyConverter() {
        setTitle("Currency Converter"); //icon title
        setSize(400, 250); //set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when the user closes the window, the application will exit.
        setLocationRelativeTo(null); //centers the JFrame window on the screen.

        // Set up font
        Font font = new Font("Arial", Font.PLAIN, 12);


        // Create & initialize the components

        JLabel lblAmount = new JLabel("Amount:");
        txtAmount = new JTextField(10);
        lblAmount.setFont(font);
        txtAmount.setFont(font);

        JLabel lblFromCurrency = new JLabel("From:"); //currency options
        cmbFromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "MXN", "CHF", "JPY", "CAD"});

        JLabel lblToCurrency = new JLabel("To:");
        cmbToCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "MXN", "CHF", "JPY", "CAD"});


        JButton btnConvert = new JButton("Convert");
        lblConversion = new JLabel("Conversion: "); //displays the conversion


        JButton btnSwap = new JButton("Swap");
        btnSwap.addActionListener(e -> {
            int fromIndex = cmbFromCurrency.getSelectedIndex();
            int toIndex = cmbToCurrency.getSelectedIndex();

            cmbFromCurrency.setSelectedIndex(toIndex);
            cmbToCurrency.setSelectedIndex(fromIndex);
        });

        JLabel lblTimestampLabel = new JLabel("Last Updated:");
        lblTimestampLabel.setFont(font);
        lblTimestamp = new JLabel();
        lblTimestamp.setFont(font);

        // Initialize the service
        converterService = new CurrencyConverterService();

        // Add action listener to the button
        btnConvert.addActionListener(e -> { //Adds an action listener to call the function when the button is clicked
            convertCurrency();
        });

        // Create panels (container) to add (store) components with GridBagLayout

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Margin/padding


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblAmount, gbc);

        gbc.gridx = 1;
        panel.add(txtAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblFromCurrency, gbc);

        gbc.gridx = 1;
        panel.add(cmbFromCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblToCurrency, gbc);

        gbc.gridx = 1;
        panel.add(cmbToCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnSwap, gbc);

        gbc.gridx = 1;
        panel.add(btnConvert, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(lblConversion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(lblTimestampLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(lblTimestamp, gbc);


        // Set panel font
        panel.setFont(font);

        // Add panels to the frame
        add(panel);

        // Display the frame
        setVisible(true);
    }


    /**
     * This method performs the currency conversion based on the user input.
     */
    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            String fromCurrency = (String) cmbFromCurrency.getSelectedItem();
            String toCurrency = (String) cmbToCurrency.getSelectedItem();

            double conversion = converterService.convertCurrency(amount, fromCurrency, toCurrency);

            lblConversion.setText("Conversion: " + conversion);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format the current date and time.
            lblTimestamp.setText(sdf.format(new Date()));
        } catch (NumberFormatException ex) {
            lblConversion.setText("Invalid Currency Amount");
        } catch (Exception ex) {
            lblConversion.setText("Error: " + ex.getMessage());
        }
    }

     //method ensures the GUI components are created and shown correctly when the application is executed.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}






















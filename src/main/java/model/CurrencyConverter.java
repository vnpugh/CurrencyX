package model;

import service.CurrencyConverterService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;



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
        setTitle("Currency Converter");
        setSize(400, 250); //set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when the user closes the window, the application will exit.
        setLocationRelativeTo(null); //centers the JFrame window on the screen.

        // Create & initialize the components
        JLabel lblAmount = new JLabel("Amount:");
        txtAmount = new JTextField(10); //width of 10 characters

        JLabel lblFromCurrency = new JLabel("From:"); //currency options
        cmbFromCurrency = new JComboBox<>(new String[]{"USD", "EURO", "GBP", "MXN", "CHF", "JPY", "CAD"});

        JLabel lblToCurrency = new JLabel("To:");
        cmbToCurrency = new JComboBox<>(new String[]{"USD", "EURO", "GBP", "MXN", "CHF", "JPY", "CAD"});

        JButton btnConvert = new JButton("Convert");
        lblConversion = new JLabel("Conversion: "); //displays the conversion

        JLabel lblTimestampLabel = new JLabel("Last Updated:");
        lblTimestamp = new JLabel();

        // Initialize the service
        converterService = new CurrencyConverterService();

        // Add action listener to the button
        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //Adds an action listener to call the function when the button is clicked
                convertCurrency();
            }
        });

        // Create panels (container) to add (store) components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(lblAmount);
        panel.add(txtAmount);
        panel.add(lblFromCurrency);
        panel.add(cmbFromCurrency);
        panel.add(lblToCurrency);
        panel.add(cmbToCurrency);
        panel.add(btnConvert);
        panel.add(lblConversion);
        panel.add(lblTimestampLabel);
        panel.add(lblTimestamp);

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






















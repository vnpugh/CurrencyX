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
    private JLabel lblConversion; //a label component that will display the result of the conversion.
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






















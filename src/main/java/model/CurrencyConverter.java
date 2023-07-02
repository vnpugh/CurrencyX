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
public class CurrencyConverter extends JFrame{

    private JTextField txtAmount;  //user enters the dollar amount
    private JComboBox<String> cmbFromCurrency; //a combo box component that allows the user to select the currency they want to convert from.
    private JComboBox<String> cmbToCurrency; //a combo box component that allows the user to select the currency they want to convert to.
    private JLabel lblResult; //a label component that will display the result of the conversion.
    private JLabel lblTimestamp; //a label component that will display the timestamp.
    private CurrencyConverterService converterService; //an instance of the CurrencyConverterService class that will perform conversions and fetch live exchange rates.


}


















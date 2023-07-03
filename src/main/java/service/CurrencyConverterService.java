package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverterService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/"; //fetch live exchange rates

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) throws Exception {
        double fromCurrencyRate = getExchangeRate(fromCurrency);
        double toCurrencyRate = getExchangeRate(toCurrency);

        double result = (amount / fromCurrencyRate) * toCurrencyRate;
        return result;
    }

    /**
     * This method will get the exchange rate for a specific currency code from the API.
     * @param currencyCode
     * @return
     * @throws Exception
     */
    private double getExchangeRate(String currencyCode) throws Exception {
        String urlString = API_URL + currencyCode; //concatenate the base API URL

        URL url = new URL(urlString); //new URL object created
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET"); //creating a connection to set the request and retrieve the response from the API.
    

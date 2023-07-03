package service;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import com.google.gson.JsonObject;




//see https://www.exchangerate-api.com/docs/standard-requests for setup & free API key

public class CurrencyConverterService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/36bc1c75eaf01375944ce3cc/latest/USD"; // fetch live exchange rates

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) throws Exception {
        double fromCurrencyRate = getExchangeRate(fromCurrency);
        double toCurrencyRate = getExchangeRate(toCurrency);

        double result = (amount / fromCurrencyRate) * toCurrencyRate;
        return result;
    }

    /**
     * This method will get the exchange rate for a specific currency code from the API.
     *
     * @param currencyCode
     * @return
     * @throws Exception
     */
    private double getExchangeRate(String currencyCode) throws Exception {
        String apiUrl = API_URL + "?to=" + currencyCode; // append currency code as a query parameter


        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            double exchangeRate = rates.get(currencyCode).getAsDouble();

            return exchangeRate;
            

        } else {
            throw new Exception("Failed to fetch exchange rate for " + currencyCode);
        }
    }
}
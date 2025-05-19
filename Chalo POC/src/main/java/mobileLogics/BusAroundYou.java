package mobileLogics;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BusAroundYou {
    public static void main(String[] args) {
        try {
            // Define the endpoint URL
            URL url = new URL("https://chalo.com/app/api/nearbybus/v2/city/MUMBAI");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set HTTP request method to POST
            connection.setRequestMethod("POST");

            // Add request headers
            connection.setRequestProperty("Authorization", 
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI4OTA0Mzk5NjM3IiwiZGV2aWNlSWQiOiI4ZGE1NTBkZjg1N2NhNGUxMTA3MTQ4YTE5YTZkYjA4NyIsImlhdCI6MTczNzYxNjE1NSwiZXhwIjoxNzM3NjIzMzU1LCJqdGkiOiJpa3kwMW02OHpzbWhsIn0.LAWP3fTJZOZR3V4f3RlvU_-asE2_QAkFKl4nuSNBn0c");
            connection.setRequestProperty("User-Agent", "FireFlink");
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable input and output streams
            connection.setDoOutput(true);

            // JSON payload
            String jsonPayload = "{\n" +
                    "  \"metaData\": {\n" +
                    "    \"source\": \"web\"\n" +
                    "  },\n" +
                    "  \"requiredFields\": {\n" +
                    "    \"nearbyBuses\": {\n" +
                    "      \"lat\": 19.076,\n" +
                    "      \"lng\": 72.8777,\n" +
                    "      \"radius\": \"1000.0\"\n" +
                    "    },\n" +
                    "    \"cardsInfo\": {}\n" +
                    "  }\n" +
                    "}";

            // Write JSON payload to the request body
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            // Check the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                System.out.println("Response: " + response.toString());
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


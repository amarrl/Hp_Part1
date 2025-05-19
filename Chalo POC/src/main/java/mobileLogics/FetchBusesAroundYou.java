
package mobileLogics;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class FetchBusesAroundYou implements Nlp {
	//@InputParams({ @InputParam(name = "JSON Data", type = "java.lang.String") })
	@ReturnType(name = "Live Buses Details", type = "Map")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String jSONData = (String) programElementsInput.get("JSON Data");
		Map returnValue = null;

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		 StringBuilder response;
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
                 response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                System.out.println("Response: " + response.toString());
            }

            // Close the connection
            connection.disconnect();
			
			System.out.println("Extracting `vNo` from routeLiveInfo...");
			returnValue =	extractVNo(response);

			
			nlpResponseModel.setMessage("Buses around you details has fetched: "+returnValue);

			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			// Your program element Exception handling goes here ...

			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			nlpResponseModel.setMessage("Failed to fetch Bus details around you: " + exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		// Your program element business logic ends here ...
		nlpResponseModel.getAttributes().put("Live Buses Details", returnValue);
		return nlpResponseModel;
	}

	private static Map extractVNo(StringBuilder parentNode) {
		Map<String, String> map = new HashMap<>();

		// The JSON response
        String jsonResponse = parentNode.toString(); // Paste your JSON response here

        // Parse the JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray busesArray = jsonObject.getJSONArray("buses");

        // Loop through each bus and fetch _routeName and nextStopName
        for (int i = 0; i < busesArray.length(); i++) {
            JSONObject bus = busesArray.getJSONObject(i);

            // Extract session and parameters objects
            JSONObject session = bus.getJSONObject("session");
            JSONObject parameters = bus.getJSONObject("parameters");

            // Fetch _routeName and nextStopName
            String routeName = session.getString("_routeName");
            String nextStopName = parameters.getString("nextStopName");
            
            map.put(routeName, nextStopName);

            // Print the result
            System.out.println("Route Name: " + routeName);
            System.out.println("Next Stop Name: " + nextStopName);
            System.out.println("-------------------------------");
        }
		return map;
	}

}
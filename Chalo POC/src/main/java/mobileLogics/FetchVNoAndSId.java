
package mobileLogics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class FetchVNoAndSId implements Nlp {
	//@InputParams({ @InputParam(name = "JSON Data", type = "java.lang.String") })
	@ReturnType(name = "Live Buses Details", type = "Map")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String jSONData = (String) programElementsInput.get("JSON Data");
		Map returnValue = null;

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			// URL of the API endpoint
			String apiUrl = "https://chalo.com/app/api/vasudha/track/route-live-info/mumbai/vgbahdwJ?stopIds=wRnxrxYj";

			// Open a connection
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set request method
			connection.setRequestMethod("GET"); // cURL uses GET by default if no data is sent

			// Set headers
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("accept-language", "en-US,en;q=0.9");
			connection.setRequestProperty("accesstoken",
					"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKMWMyVnlTV1FpT2lJNE9UQTBNems1TmpNM0lpd2laR1YyYVdObFNXUWlPaUprWmpoak1HUXhPVEl3TjJaak5ETXhaR1JpWldNM05HRTBZV000TjJZeE5TSXNJbWxoZENJNk1UY3pOemN4TlRneU5IMC5zMng1eUhUOHVvMklXajA1R3FIZTNBYTNva19keFl1c2Z5SGFubkJhZTRZIiwiZGV2aWNlSWQiOiJkZjhjMGQxOTIwN2ZjNDMxZGRiZWM3NGE0YWM4N2YxNSIsInVzZXJJZCI6Ijg5MDQzOTk2MzciLCJpYXQiOjE3MzgwNDE5NTMsImV4cCI6MTczODA0OTE1MywianRpIjoiMXRoMW02ZzFheWtpIn0.MfHbc2h380pQi1y_jOo9XzsoBoAZVRekfCTpC5-sn1A");
			connection.setRequestProperty("cache-control", "no-cache");
			connection.setRequestProperty("cookie",
					"_fbp=fb.1.1737715779905.53657261861877089; _gcl_au=1.1.823594356.1737715780; _ga=GA1.1.1686778009.1737715781; _ga_5PRF9T2GLN=GS1.1.1737715780.1.0.1737715785.0.0.0; mp_b1925cf6c0b3db7d5f3904a66abf8ec7_mixpanel=%7B%22distinct_id%22%3A%20%22df8c0d19207fc431ddbec74a4ac87f15%22%2C%22%24device_id%22%3A%20%2219497eeae52caf-0451c6dfcaa82a-26011851-100200-19497eeae538c0%22%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fchalo.com%2Fmumbai%22%2C%22%24initial_referring_domain%22%3A%20%22chalo.com%22%2C%22%24user_id%22%3A%20%22df8c0d19207fc431ddbec74a4ac87f15%22%2C%22clientSource%22%3A%20%22PWA%22%2C%22appVersionCode%22%3A%20%221001%22%2C%22selected%20language%22%3A%20%22English%22%2C%22timeZone%22%3A%20%22%2B05%3A30%22%2C%22deviceId%22%3A%20%22df8c0d19207fc431ddbec74a4ac87f15%22%2C%22mailId%22%3A%20%22%22%2C%22phone%22%3A%20%228904399637%22%2C%22gender%22%3A%20%22%22%2C%22firstName%22%3A%20%22%22%2C%22lastName%22%3A%20%22%22%2C%22mobileNumber%22%3A%20%228904399637%22%2C%22userId%22%3A%20%228904399637%22%2C%22selectedCity%22%3A%20%22mumbai%22%7D; _ga_SEWPQ4G3XZ=GS1.1.1738041926.3.1.1738042012.0.0.0");
			connection.setRequestProperty("pragma", "no-cache");
			connection.setRequestProperty("priority", "u=1, i");
			connection.setRequestProperty("referer", "https://chalo.com/app/live-tracking/route-map/vgbahdwJ");
			connection.setRequestProperty("sec-ch-ua",
					"\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"");
			connection.setRequestProperty("sec-ch-ua-mobile", "?0");
			connection.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
			connection.setRequestProperty("sec-fetch-dest", "empty");
			connection.setRequestProperty("sec-fetch-mode", "cors");
			connection.setRequestProperty("sec-fetch-site", "same-origin");
			connection.setRequestProperty("source", "1");
			connection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
			connection.setRequestProperty("x-type", "pass");

			// Connect and get the response code
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Print the response
//            System.out.println("Response: " + response.toString());
			// Parse the JSON
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(response.toString());

			// Extract "routeLiveInfo" node
			JsonNode routeLiveInfoNode = rootNode.path("routeLiveInfo");

			System.out.println("Extracting `vNo` from routeLiveInfo...");
			returnValue =	extractVNo(routeLiveInfoNode);

			// Disconnect the connection
			connection.disconnect();
			
			nlpResponseModel.setMessage("Live Bus details has fetched: "+returnValue);

			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			// Your program element Exception handling goes here ...

			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			nlpResponseModel.setMessage("Failed to fetch Live Bus details: " + exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		// Your program element business logic ends here ...
		nlpResponseModel.getAttributes().put("Live Buses Details", returnValue);
		return nlpResponseModel;
	}

	private static Map extractVNo(JsonNode parentNode) {
		Map<String, String> map = new HashMap<>();

		Iterator<JsonNode> elements = parentNode.elements();

		while (elements.hasNext()) {
			String nestedJson = elements.next().asText();
			try {
				// Parse nested JSON
				ObjectMapper mapper = new ObjectMapper();
				JsonNode nestedNode = mapper.readTree(nestedJson);

				// Extract `vNo`
				if (nestedNode.has("vNo")) {
					System.out.println("vNo: " + nestedNode.get("vNo").asText());
				}

				else if (nestedNode.has("sId")) {
					System.out.println("sId: " + nestedNode.get("sId").asText());
				}

				map.put(nestedNode.get("vNo").asText(), nestedNode.get("sId").asText());

			} catch (Exception e) {
				System.out.println("Failed to parse nested JSON: " + nestedJson);
			}
		}
		return map;
	}

}
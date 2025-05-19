package default_package;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractVNoFromJson {
	public static void main(String[] args) {
		String json = "{\n" + "  \"routeLiveInfo\": {\n"
				+ "    \"-OHbYyoC2MOQ2zyrJT_7\": \"{\\\"lSId\\\":\\\"QSAxzwnY\\\",\\\"_longitude\\\":72.81723022460938,\\\"eta\\\":147,\\\"vNo\\\":\\\"749\\\",\\\"opId\\\":\\\"1909\\\",\\\"psId\\\":\\\"scqlmLON\\\",\\\"_isHalted\\\":false,\\\"_latitude\\\":18.953020095825195,\\\"psTime\\\":1737979373000,\\\"tS\\\":1737979683000,\\\"sId\\\":\\\"gABbegZg\\\"}\",\n"
				+ "    \"-OHbYFBFs3y4TSHxhvoB\": \"{\\\"lSId\\\":\\\"QSAxzwnY\\\",\\\"_longitude\\\":72.8328628540039,\\\"eta\\\":95,\\\"vNo\\\":\\\"750\\\",\\\"opId\\\":\\\"1909\\\",\\\"psId\\\":\\\"MPpSwpld\\\",\\\"_isHalted\\\":false,\\\"_latitude\\\":18.988380432128906,\\\"psTime\\\":1737979665000,\\\"tS\\\":1737979685000,\\\"sId\\\":\\\"yQeQRVTL\\\"}\",\n"
				+ "    \"-OHbWPEACsiViAaj-HgJ\": \"{\\\"lSId\\\":\\\"QSAxzwnY\\\",\\\"_longitude\\\":72.82286834716797,\\\"eta\\\":9,\\\"vNo\\\":\\\"748\\\",\\\"opId\\\":\\\"1909\\\",\\\"psId\\\":\\\"YPOEcYOS\\\",\\\"_isHalted\\\":false,\\\"_latitude\\\":18.973590850830078,\\\"psTime\\\":1737979589000,\\\"tS\\\":1737979659000,\\\"sId\\\":\\\"KMyFgupD\\\"}\"\n"
				+ "  },\n" + "  \"stopsEta\": {\n" + "    \"wRnxrxYj\": {\n"
				+ "      \"-OHbYyoC2MOQ2zyrJT_7\": \"{\\\"lSId\\\":\\\"QSAxzwnY\\\",\\\"eta\\\":-1,\\\"isHalted\\\":false,\\\"vNo\\\":\\\"749\\\",\\\"ag\\\":\\\"best\\\",\\\"dist\\\":0,\\\"dest\\\":\\\"Ballard Pier\\\",\\\"rN\\\":\\\"66\\\",\\\"tS\\\":1737979683000}\"\n"
				+ "    }\n" + "  }\n" + "}";

		try {
			// Parse the JSON
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(json);

			// Extract "routeLiveInfo" node
			JsonNode routeLiveInfoNode = rootNode.path("routeLiveInfo");

			System.out.println("Extracting `vNo` from routeLiveInfo...");
			extractVNo(routeLiveInfoNode);

//            // Extract "stopsEta" -> Nested "wRnxrxYj" node
//            JsonNode stopsEtaNode = rootNode.path("stopsEta").path("wRnxrxYj");
//
//            System.out.println("\nExtracting `vNo` from stopsEta...");
//            extractVNo(stopsEtaNode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void extractVNo(JsonNode parentNode) {
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
				
				if (nestedNode.has("sId")) {
					System.out.println("sId: " + nestedNode.get("sId").asText());
				}

				map.put(nestedNode.get("vNo").asText(), nestedNode.get("sId").asText());

			} catch (Exception e) {
				System.out.println("Failed to parse nested JSON: " + nestedJson);
			}
		}
		System.out.println(map);

		// return map;
	}
}

package PayloadFiles;

import io.restassured.path.json.JsonPath;

public class ReUsableMethodOFJsonParse {



public static JsonPath  rawToJason(String res) {
	return new JsonPath(res);
}
	

}

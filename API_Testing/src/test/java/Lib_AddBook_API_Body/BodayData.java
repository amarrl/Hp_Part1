package Lib_AddBook_API_Body;

import java.util.HashMap;
import java.util.Map;

public class BodayData {

	public Map<String, Object> Boday() {
		
		
		
		Map<String, Object>mapData=new HashMap<>();
		
		mapData.put("name", "Learn Appium Automation with Java");
		mapData.put("isbn", "Trst1");
		mapData.put("aisle", "227");
		mapData.put("author", "John foe");
		return mapData;
		
	
		/*
		Map<String, Object>mapData2=new HashMap<>();
		
		mapData2.put("lat", "-124");
		mapData2.put("lan", "123");
		mapData.put("location", mapData2);
		*/
		
		/*
		 {
	
	{
  "name" : "Learn Appium Automation with Java",
  "isbn" : "Trst1",
  "aisle" : "227",
  "author" : "John foe",
  
  "location" : {
    "lat" : "123",
    "lng" : "141.11"
  }
}

		 */
		
		
		

	}

}

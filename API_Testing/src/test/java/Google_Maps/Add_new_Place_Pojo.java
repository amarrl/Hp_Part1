package Google_Maps;

import java.util.List;

public class Add_new_Place_Pojo {
	
	private LocationPojo location;
	private String accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;
	
	public LocationPojo getLocation() {
		return location;
	}
	
	public void setLocation(LocationPojo location) {
		this.location=location;
	}
	
	public String getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(String accuracy) {
		this.accuracy=accuracy;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getPhone_number() {
		return name;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number=phone_number;
	}
	
	
	public String getAddress() {
		return name;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	public List<String>getTypes(){
		return types;
	}
	
	public void setTypes(List<String>types) {
		this.types=types;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website=website;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language=language;
	}
	
	
	

}

package EcommercePojo2;

public class LoginResponse1 {
	
	private String token;
	private String userId; 
	private String message;
	
	public void setToken(String token) {
		this.token=token;
	}
	
	public String getToken() {
		return token;
	}
	
	
	public void setUserId(String userId) {
		this.userId=userId;
	}
	
	public String getUserID() {
		return userId;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	

}

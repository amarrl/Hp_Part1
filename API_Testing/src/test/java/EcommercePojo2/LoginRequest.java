package EcommercePojo2;

public class LoginRequest {
	private String userEmail;
	
	private String userPassword;
	
	
	public void setUserEmail(String userEmail) {
		this.userEmail=userEmail;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	
	public void setUserPassword(String userPassword) {
		this.userPassword=userPassword;
	}
	
	public String getuserPassword() {
		return userPassword; 
	}
}

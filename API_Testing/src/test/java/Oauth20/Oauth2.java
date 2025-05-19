package Oauth20;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class Oauth2 {

	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fv2%25252Fauth&dsh=S807736953%3A1746097873599999&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&ddm=1&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAPAaVWvZQuqfXrkX7NgrKyQ_RgZo1rcXxVgHYf6PaQeutDWQYOhbFM3VFkbzWzPBqEhgN4RNZ8r8E5RkU3kFI4l2UVE4NI9TGvyzB8r5OR945zgLt7yLh2USbJyACH2negewRGtvvdsIGx5JSSH3calYsmoNbuLIKhSgwQHDUmgW_XnKme9DV37GKy1V92vmXjXmmnXnKuoXrkIWmI5YUyvwSEojQSEU6hqA9DQqXpzf2QPHDTTI93vVq0WPAXxe-6vwXPUnfJt1yGJO9UmyV1HNwtdN51IIL--JNOU0tFQ0tCGzdNrWMq4T4wbdr5h2RYBwkt3MClitl0o2DpgvTa4FFXwAt2HLOZsGRQb6SgUOnPnBYUTZ8S4S_1N8womvwtaoJvXr9SuMwEI5PdHcb48O9VIVKRebNeZDhM52F8pzJQs2R0ov5h4ZgJrvk1F5cyWTFBtcK921sCXHUqS7-8tDIZSFA%26flowName%3DGeneralOAuthFlow%26as%3DS807736953%253A1746097873599999%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxcfR72GXjcX6bZDQrr4UKMfyT1LrmtefFpUtvMPfljhqXjpkX5vKrvFJ4rGeWbEAU6OZLz8UiO79MA7kn2gOcl9pCkqPkzd5ZnQhYnL-VnEej46k4tg");

		driver.findElement(By.xpath("//input[@aria-label='Email or phone']")).sendKeys("amarkumar9r9@gmail.com");
		driver.findElement(By.xpath("//input[@aria-label='Enter your password']")).sendKeys("amarkumar9r9@gmail.com");
		
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		String cureentUrl=driver.getCurrentUrl();
		
		//String[]arr=cureentUrl.split("code=");
		
		   String arr[] =cureentUrl.split("code=|&scope", 3);
			 String code=arr[1];
			 
			 System.out.println(code);
			 
		
		
		String tokenResponse=given().urlEncodingEnabled(false).log().all()
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(tokenResponse);
		 
		String access_tokenIs=js.get("access_token");
		
		
		String ActualRes=given().log().all().queryParam("access_token", access_tokenIs)
		.when().post("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(ActualRes);
		

	}

}

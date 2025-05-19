package business_Logics;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Captcha_Sundar implements Nlp {
@InputParams({@InputParam(name = "FilePath", type = "java.lang.String"),
@InputParam(name = "apiKey", type = "java.lang.String")})
// @ReturnType(name = "text", type = "java.lang.String")
@Override
public List<String> getTestParameters() throws NlpException {
List<String> params = new ArrayList<>();
return params;
}
@Override
public StringBuilder getTestCode() throws NlpException {
StringBuilder sb = new StringBuilder();
return sb;
}
@Override
public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
NlpResponseModel nlpResponseModel = new NlpResponseModel();
Map<String, Object> attributes = nlpRequestModel.getAttributes();
String filePath = (String) attributes.get("FilePath");
String apiKey=(String) attributes.get("apiKey");
try {
String xpath="//div[@class=\"captchablock\"]//img";
WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
int count=0;
while(count<5) {
String responseString= takeElementScreenshot(driver,xpath,apiKey,filePath);
driver.findElement(By.xpath("//input[@placeholder=\"Enter captcha *\"]")).clear();
driver.findElement(By.xpath("//input[@placeholder=\"Enter captcha *\"]")).sendKeys(fixAllText(responseString));
driver.findElement(By.xpath("//button[@class=\"proceedBtn js_login_btn\"]")).click();
Thread.sleep(4000);
int size=driver.findElements(By.xpath("//div[text()='Please enter valid captcha value']")).size();
System.out.println("Size "+size);
if(size>0) {
driver.findElement(By.xpath("//i[@class=\"icon-refresh2\"]")).click();
Thread.sleep(3000);
}else {
break;
}
count++;
}
nlpResponseModel.setMessage("Fetched text from Image");
nlpResponseModel.setStatus(CommonConstants.pass);
} catch (Exception e) {
nlpResponseModel.setMessage("Failed to Fetch text from Image "+e);
nlpResponseModel.setStatus(CommonConstants.fail);
}
return nlpResponseModel;
}
public static String takeElementScreenshot(WebDriver driver, String xpath,String apikey,String filepath) throws IOException{
File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
System.out.println(screenshot);
BufferedImage fullImg = ImageIO.read(screenshot);
WebElement ele=driver.findElement(By.xpath(xpath));
Point point = ele.getLocation();
int eleWidth = ele.getSize().getWidth();
int eleHeight = ele.getSize().getHeight();
BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
ImageIO.write(eleScreenshot, "png", screenshot);
File screenshotLocation = new File(filepath);
FileUtils.copyFile(screenshot, screenshotLocation);
String responseText=GetTextFromImage(screenshot.toString(),apikey);
System.out.println(responseText);
return responseText;
}
public static String GetTextFromImage(String filePath, String apikey) {
String responseText = "";
try {
byte[] bodyBytes = readFileToBytes(filePath);
Response response = RestAssured.given().header("apikey", apikey)
.contentType("application/octet-stream")
.body(bodyBytes).post("https://api.apilayer.com/image_to_text/upload");
responseText = response.jsonPath().get("all_text");
System.out.println("Fetched text from Image");
} catch (Exception e) {
System.out.println("failed to Fetch text from Image");
}
return responseText;
}
public static byte[] readFileToBytes(String filePath) {
try {
return Files.readAllBytes(Paths.get(filePath));
} catch (Exception e) {
e.printStackTrace();
return null;
}
}
public static String fixAllText(String allText) {
if (allText.contains("\n")) {
int newlineIndex = allText.indexOf("\n");
String beforeNewline = allText.substring(0, newlineIndex);
String afterNewline = allText.substring(newlineIndex + 1); // e.g., "L"
int lastSpaceIndex = beforeNewline.lastIndexOf(" ");
if (lastSpaceIndex != -1) {
String part1 = beforeNewline.substring(0, lastSpaceIndex);
String part2 = beforeNewline.substring(lastSpaceIndex + 1);
return part1 + afterNewline + part2;
} else {
return allText.replace("\n", "");
}
}
return allText;
}

}

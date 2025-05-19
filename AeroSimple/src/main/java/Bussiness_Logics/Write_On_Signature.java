
      package Bussiness_Logics;

      import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
      import com.tyss.optimize.nlp.util.NlpException;
      import com.tyss.optimize.nlp.util.NlpRequestModel;
      import com.tyss.optimize.nlp.util.NlpResponseModel;
      import com.tyss.optimize.nlp.util.annotation.InputParam;
      import com.tyss.optimize.nlp.util.annotation.InputParams;
      import com.tyss.optimize.nlp.util.annotation.ReturnType;
      import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.util.ArrayList;
      import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

   
    
      






















@Component("LIC15630_PJT1001_PE_NLP02b6fca0-fb98-48e7-95e7-f0efd70f62b7")
public class Write_On_Signature implements Nlp {
          @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement")})//, @InputParam(name = "Operand2", type = "java.lang.Integer")})
//          @ReturnType(name = "Assign step to returne Value", type = "java.lang.Integer")

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
                  WebElement canvas = (WebElement) attributes.get("Element");
                  
                  // Your program element business logic goes here ...
                
                  
                  try {
                	System.setProperty("java.awt.headless", "false");
                	WebDriver driver = (WebDriver)nlpRequestModel.getDriver().getSpecificIDriver();
                  	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                  	JavascriptExecutor js = (JavascriptExecutor)driver;
                  	Actions ac = new Actions(driver);
                  	Robot rt = new Robot();
                  	int count=0;
                  	                		
                		int X = canvas.getLocation().getX();
                		int Y = canvas.getLocation().getY();

                		int height = (((canvas.getSize().getHeight())/4)/3)*2;
                		int width=(((canvas.getSize().getWidth())/3)/3)*2;

                		
                		Thread.sleep(1000);
                		
                		ac.moveToElement(canvas, -width, height).clickAndHold().build().perform();
                		
                		Thread.sleep(1000);
                		for(int i=X;i<X+canvas.getSize().getWidth()-50;i=i+canvas.getSize().getWidth()/5) {
                			rt.mouseMove(i+10, (Y+10));
                			rt.delay(1500);
                			rt.mouseMove((i+40), (Y-80));
                			
                			count++;
                			if(count==5) {
                				break;
                			}
                			
                		}
                		Thread.sleep(1000);
                		ac.click().build().perform();     
              		nlpResponseModel.setStatus(CommonConstants.pass);
               	  nlpResponseModel.setMessage("Successfully written");
               	  }catch (Exception e) {
        			// TODO: handle exception
               		 
                	  nlpResponseModel.setStatus(CommonConstants.fail);
                	  nlpResponseModel.setMessage("Failed write");
                	  
        		}
//                  String string3 = "Return Value";
//                  nlpResponseModel.getAttributes().put("Assign step to returne Value",  quotient);
                  return nlpResponseModel;
              }
        } 
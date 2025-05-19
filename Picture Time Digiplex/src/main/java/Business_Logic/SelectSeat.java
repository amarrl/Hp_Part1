
package Business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.util.Map;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;



public class SelectSeat implements Nlp {
	static AndroidDriver driver=null;
public static void main(String[] args) throws NlpException, MalformedURLException {
	
	DesiredCapabilities capability = new DesiredCapabilities();

   capability.setCapability("platformName", "Android");
    
   	driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capability);
		NlpRequestModel nlpRequestModel = new NlpRequestModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		attributes.put("Enter Seat Number", "D4,F7");
		
		SelectSeat ss=new SelectSeat();
		

	ss.execute(nlpRequestModel);
		
	}
	
    @InputParams({@InputParam(name = "Enter Seat Number", type = "java.lang.String")})
//    @ReturnType(name = "string3", type = "java.lang.String")

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
          
          String string1 = (String) attributes.get("Enter Seat Number");
          System.out.println(string1);
//          String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...
          
          //AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          String[] list = null;
          ArrayList array=null;
         
          
        	 try { 		 			  			
		  			if (string1.contains(",")==true) {
		  			    list = string1.split(",");
		  			    array = new ArrayList<>(Arrays.asList(list));
		  			    System.out.println(array);
		  			} else {
		  			    array = new ArrayList<>();
		  			    array.add(string1);
		  			}

       
       
			       for(int i=0;i<array.size();i++) {
			    	   String seat = array.get(i).toString();
					    char seatName = seat.charAt(0);
					    char seatNumber = seat.charAt(1);
					    System.out.println(seatName + "  " + seatNumber);
			      			       
			       int Final=0;
			       int temp=0;
			       for (char ch = 'K'; ch >= seatName; ch--) {
			            
					    List<WebElement> A = driver.findElements(AppiumBy.xpath("//android.view.View[@content-desc='"+seatNumber+"']//following-sibling::android.widget.ImageView[contains(@clickable,'true') and not(@clickable='false')]"));
					    int intial = A.size();
					    temp=intial-Final;
					    Final=temp+Final;
			        }
			//       System.out.println(temp);
				     if(temp<=12) {
				       driver.findElement(By.xpath("//android.view.View[@content-desc='"+seatName+"']//following-sibling::android.widget.ImageView["+seatNumber+"]")).click();
				     }
			       }
       
			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Seats selected successfully ");
  		} catch (Exception e) {
  			e.printStackTrace();
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Seats selected successfully :" + e.getLocalizedMessage().toString());
  		}

//          String string3 = "Return Value";
//          nlpResponseModel.getAttributes().put("string3", string3);
          return nlpResponseModel;
      }
  } 
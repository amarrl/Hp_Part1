
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
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;


@Component("LIC15630_PJT1001_PE_NLP080db1e5-8a5c-4b58-8a95-bb0facf06ddc")
public class Select_Points_on_Map implements Nlp {
    @InputParams({@InputParam(name = "NumberOfPoints", type = "java.lang.Integer")})

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
          Integer NumberOfPoints = (Integer) attributes.get("NumberOfPoints");
          WebDriver driver=nlpRequestModel.getWebDriver();
          try 
          {
        	  WebElement totalMap = driver.findElement(By.xpath("//div[@id=\"map\"]"));
      		org.openqa.selenium.Point point= driver.findElement(By.xpath("//div[@id=\"map\"]")).getLocation();
      	
      	
      		 Dimension mapSize = totalMap.getSize();
      	
      		driver.findElement(By.xpath("//span[normalize-space(.)='Create Property']")).click();
      		
      		driver.findElement(By.xpath("//label[normalize-space(.)='Single Location']")).click();
      		driver.findElement(By.xpath("//label[normalize-space(.)='Area']")).click();
      		driver.findElement(By.xpath("//span[normalize-space(.)='Select Area']")).click();
      		driver.findElement(By.xpath("//a[@title='Draw a New Surface!']")).click();
      		
            Dimension ddd=  driver.findElement(By.xpath("//a[@title=\"Draw a New Surface!\"]")).getSize();

            org.openqa.selenium.Point  s= driver.findElement(By.xpath("//a[@title=\"Draw a New Surface!\"]")).getLocation();
          
            
            int ty=s.getY()-point.getY()+ddd.getHeight();
            System.out.println(ty +" "+"This is the ty value");
              
      		Actions action = new Actions(driver);
      		int xco= mapSize.getWidth();
      		int yco= mapSize.getHeight()-ty;
      		Dimension smallframe=driver.findElement(By.xpath("//div[contains(@class,\"form_popup\")]")).getSize();
      		int a=smallframe.getWidth();
      		int b=smallframe.getHeight();
      		int finalx=xco-a;		
      		action.moveByOffset(point.getX(),point.getY()+ty).build().perform();
      		int a1=NumberOfPoints;
      		int point1=(finalx*1)/(a1);
      		int point2=(yco*1)/(a1);
      		action.moveByOffset(point1,point2).click().build().perform();
      		for (int i = 0; i < a1; i++) {

      			
      			if (i%2==0){

      				action.moveByOffset(0,point2).click().build().perform();

      			}else if (i%2==1) {
      				action.moveByOffset(point1,0).click().build().perform();

      			}
			
          }
      		nlpResponseModel.setStatus(CommonConstants.pass);
      		nlpResponseModel.setMessage("Plotted the "+NumberOfPoints+" Points on the Map");
          }
          catch (Exception e) 
          {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
        	  nlpResponseModel.setMessage("Failed to Plot the Points on the Map");
          }

          return nlpResponseModel;
      }
  } 
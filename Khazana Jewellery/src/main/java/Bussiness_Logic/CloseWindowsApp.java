package Bussiness_Logic;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Component;

//@Slf4j
//@Component("LIC20134_PJT1001_PE_NLP0f290f10-d4b5-43d3-95f7-8b8b112dedab")
public class CloseWindowsApp implements Nlp {
    @InputParams({@InputParam(name = "Application Name", type = "java.lang.String")})

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
     
          String applicationName = (String) attributes.get("Application Name");
          System.setProperty("java.awt.headless", "false");
         try
         {          String command = "taskkill /f /im " + applicationName;

        	 Process process = Runtime.getRuntime().exec(command);

         process.waitFor();

         int exitCode = process.exitValue();

         if (exitCode == 0) {
        	  nlpResponseModel.setStatus(CommonConstants.pass);
      		nlpResponseModel.setMessage("Successfully Closed  "+applicationName);	        
      		} 
         else {
      			
        	 nlpResponseModel.setStatus(CommonConstants.fail);
 			nlpResponseModel.setMessage("Failed to close "+ applicationName);		
         }
         }
        catch(Exception e) {
        	StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
           // log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to close Application "+exceptionAsString);	
        }
          return nlpResponseModel;
      }
  } 

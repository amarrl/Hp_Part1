
package Bussiness_logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;




public class GenerateRandomNumberWithInRange implements Nlp {
    @InputParams({@InputParam(name = "Range Of The Number", type = "java.lang.Integer")})
    @ReturnType(name = "Assign Step Return Value To", type = "java.lang.Integer")

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
          Integer rangeOfTheNumber = (Integer) attributes.get("Range Of The Number");
          Integer randomNumber = null;

          try {
        	  
   	         Random random = new Random();
  	         randomNumber = random.nextInt(rangeOfTheNumber) + 1;
  	         
  	          nlpResponseModel.setStatus(CommonConstants.pass);
	          nlpResponseModel.setMessage("Successfully generated Random Number");

		} catch (Exception e) {
			
			  nlpResponseModel.setStatus(CommonConstants.fail);
	          nlpResponseModel.setMessage("failed to generate Random Number" + e);
			
		}

         
          nlpResponseModel.getAttributes().put("Assign Step Return Value To", randomNumber);
          return nlpResponseModel;
      }
  } 

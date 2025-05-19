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
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



@Component("LIC15630_PJT1001_PE_NLP6accbae0-cf2e-4b85-b667-8fe913ac2020")
public class Generate_Random_Num_WithRange implements Nlp {
    @InputParams({@InputParam(name = "xValue", type = "java.lang.Double"), @InputParam(name = "yValue", type = "java.lang.Double")})
    @ReturnType(name = "Integer3", type = "java.lang.Integer")

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
          double s1 = (Double) attributes.get("xValue");
          double s2 = (Double) attributes.get("yValue");

     Integer randomNumber =0;         
          try {
        	  int startNumber=(int) s1;
      		  int endNumber=(int)s2;
              Random random = new Random();
              randomNumber = random.nextInt(endNumber - startNumber + 1) + startNumber;
              nlpResponseModel.setStatus(CommonConstants.pass);
    			nlpResponseModel.setMessage("Successfully Generated random Number "+randomNumber);
            
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
    			nlpResponseModel.setMessage("Failed to Generate random Number");
          }
        	  
          

          nlpResponseModel.getAttributes().put("Integer3", randomNumber);
          return nlpResponseModel;
      }
  } 
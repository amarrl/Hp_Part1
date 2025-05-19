
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

import org.springframework.stereotype.Component;



@Component("LIC15630_PJT1001_PE_NLPffdde154-0629-4070-a155-679a861be6f1")
public class Is_contains implements Nlp {
    @InputParams({@InputParam(name = "Expected String", type = "java.lang.String"), @InputParam(name = "Actual String", type = "java.lang.String")})
    @ReturnType(name = "Assign step to return value", type = "java.lang.Boolean")

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
          String EXP = (String) attributes.get("Expected String");
          String ACT = (String) attributes.get("Actual String");

          // Your program element business logic goes here ...
          boolean inRetur;
          try {
        	  inRetur = ACT.contains(EXP);
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("String "+ACT+" contains"+EXP);
          }catch (Exception e) {
        	  
        	  inRetur = false;
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("String "+ACT+" contains"+EXP);
          }

//          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("Assign step to return value", inRetur);
          return nlpResponseModel;
      }
  } 
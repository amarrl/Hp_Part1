
package mobileLogics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

public class FetchFirstValue implements Nlp {
    @InputParams({@InputParam(name = "Live Bus Details", type = "Map")})
    @ReturnType(name = "First Bus Stop", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Map liveBusDetails = (Map) programElementsInput.get("Live Bus Details");
          String returnValue=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
        	  
        	// Fetching the first value
               returnValue = (String) liveBusDetails.values().stream().findFirst().orElse(null);

              
              // Your program element business logic starts here ...
              nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);

          }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("First Bus Stop", returnValue);
          return nlpResponseModel;
      }

  } 
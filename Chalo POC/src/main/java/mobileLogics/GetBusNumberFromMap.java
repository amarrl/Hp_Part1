
package mobileLogics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

public class GetBusNumberFromMap implements Nlp {
    @InputParams({@InputParam(name = "Live Bus Details", type = "Map")})
    @ReturnType(name = "Bus Numbers", type = "List")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Map liveBusDetails = (Map) programElementsInput.get("Live Bus Details");
          List<String> returnValue=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              // Your program element business logic starts here ...
               returnValue = new ArrayList<>(liveBusDetails.keySet());


              nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);

          }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("Bus Numbers", returnValue);
          return nlpResponseModel;
      }

  } 
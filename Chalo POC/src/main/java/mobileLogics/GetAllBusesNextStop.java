
package mobileLogics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

public class GetAllBusesNextStop implements Nlp {
    @InputParams({@InputParam(name = "Live Bus Details", type = "Map")})
    @ReturnType(name = "Live Buses Next Stops", type = "List")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Map liveBusDetails = (Map) programElementsInput.get("Live Bus Details");
          List<String> returnValue=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              
        	  
               returnValue = new ArrayList<>(liveBusDetails.values());

              nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);

          }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("Live Buses Next Stops", returnValue);
          return nlpResponseModel;
      }

  } 
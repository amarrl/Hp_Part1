
package default_package;

import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class AddTwoNumbers implements Nlp {
    @InputParams({@InputParam(name = "Number 1", type = "java.lang.Integer"), @InputParam(name = "Number 2", type = "java.lang.Integer")})
    @ReturnType(name = "Sum of Two numbers", type = "java.lang.Integer")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Integer number1 = (Integer) programElementsInput.get("Number 1");
          Integer number2 = (Integer) programElementsInput.get("Number 2");
          Integer returnValue=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              // Your program element business logic starts here ...
              returnValue=number1+number2;

              nlpResponseModel.setStatus(CommonConstants.pass);
              //Uncomment the below line and modify the message, only if you want to override the pass message defined for the program element.
              //nlpResponseModel.setMessage("Added two numbers");
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);

             //Uncomment the below line and modify the message, only if you want to override the fail message defined for the program element.
             //nlpResponseModel.setMessage("Failed to add numbers");
          }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
          return nlpResponseModel;
      }

  } 
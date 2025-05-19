package Business_Logics;




import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;







public class tap_on_Element implements Nlp {
    @InputParams({@InputParam(name = "X co-ordinate", type = "java.lang.Integer"), @InputParam(name = "Y co-ordinate", type = "java.lang.Integer")})

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
          int X = (int) attributes.get("X co-ordinate");
          int Y = (int) attributes.get("Y co-ordinate");
//          int Y = (int) attributes.get("Enter wait time");

          // Your program element business logic goes here ...
          try {
          AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
          TouchAction touchAction=new TouchAction<>(driver);
          PointOption point=new PointOption<>();
          touchAction.tap( point.point(X, Y)).perform();
//          Thread.sleep(Duration.ofSeconds(Y));
          
          nlpResponseModel.setMessage("tapped on "+X+" and "+Y+"-ordinates");
          nlpResponseModel.setStatus(CommonConstants.pass);
          }catch (Exception e) {
			// TODO: handle exception
        	  nlpResponseModel.setMessage("Failed tap on "+X+" and "+Y+"-ordinates");
              nlpResponseModel.setStatus(CommonConstants.fail);
		}
          
          
          
          
//          String string3 = "Return Value";
//          nlpResponseModel.getAttributes().put("string3", string3);
          return nlpResponseModel;
      }
  } 
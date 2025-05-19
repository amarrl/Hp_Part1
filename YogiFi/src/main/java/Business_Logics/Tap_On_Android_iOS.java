package Business_Logics;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Tap_On_Android_iOS implements Nlp {

    @InputParams({
        @InputParam(name = "X co-ordinate", type = "java.lang.Integer"), 
        @InputParam(name = "Y co-ordinate", type = "java.lang.Integer")
    })

    @Override
    public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        params.add("X co-ordinate");
        params.add("Y co-ordinate");
        return params;
    }

    @Override
    public StringBuilder getTestCode() throws NlpException {
        return new StringBuilder();
    }

    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
        int X = (int) attributes.get("X co-ordinate");
        int Y = (int) attributes.get("Y co-ordinate");

        try {
            AndroidDriver androidDriver = null;
            IOSDriver iosDriver = null;

            try {
                androidDriver = nlpRequestModel.getAndroidDriver();
                iosDriver = nlpRequestModel.getIosDriver();
            } catch (Exception e) {
                iosDriver = nlpRequestModel.getIosDriver();
            }

            // Handle tap for Android
            if (androidDriver != null) {
                TouchAction<?> touchAction = new TouchAction<>(androidDriver);
                PointOption<?> point = new PointOption<>();
                touchAction.tap(point.point(X, Y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                        .perform();

                nlpResponseModel.setMessage("Tapped on coordinates (" + X + ", " + Y + ") for Android");
                nlpResponseModel.setStatus(CommonConstants.pass);
            }

            // Handle tap for iOS
            else if (iosDriver != null) {
                TouchAction<?> touchAction = new TouchAction<>(iosDriver);
                PointOption<?> point = new PointOption<>();
                touchAction.tap(point.point(X, Y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                        .perform();

                nlpResponseModel.setMessage("Tapped on coordinates (" + X + ", " + Y + ") for iOS");
                nlpResponseModel.setStatus(CommonConstants.pass);
            } else {
                throw new Exception("No valid driver instance found for Android or iOS.");
            }

        } catch (Exception e) {
            nlpResponseModel.setMessage("Failed to tap on coordinates (" + X + ", " + Y + ") - " + e.getMessage());
            nlpResponseModel.setStatus(CommonConstants.fail);
        }

        return nlpResponseModel;
    }
}

package business_Logics;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
 
import io.restassured.RestAssured;
import io.restassured.response.Response;
 
public class Captcha_Handling implements Nlp {
 
    @InputParams({
        @InputParam(name = "FilePath", type = "java.lang.String"),
        @InputParam(name = "apiKey", type = "java.lang.String")
    })
    @ReturnType(name = "text", type = "java.lang.String")
 
    @Override
    public List<String> getTestParameters() throws NlpException {
        return new ArrayList<>();
    }
 
    @Override
    public StringBuilder getTestCode() throws NlpException {
        return new StringBuilder();
    }
 
    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
 
        String filePath = (String) attributes.get("FilePath");
        String apiKey = (String) attributes.get("apiKey");
 
        String responseText = "";
 
        try {
            byte[] bodyBytes = readFileToBytes(filePath);
 
            Response response = RestAssured.given()
                .header("apikey", apiKey)
                .contentType("application/octet-stream")
                .body(bodyBytes)
                .post("https://api.apilayer.com/image_to_text/upload");
 
            responseText = response.jsonPath().get("all_text");
 
            nlpResponseModel.setMessage("Fetched text from image successfully");
            nlpResponseModel.setStatus(CommonConstants.pass);
        } catch (Exception e) {
            nlpResponseModel.setMessage("Failed to fetch text from image: " + e.getMessage());
            nlpResponseModel.setStatus(CommonConstants.fail);
        }
 
        nlpResponseModel.getAttributes().put("text", responseText);
        return nlpResponseModel;
    }
 
    public static byte[] readFileToBytes(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
 
 
package business_Logics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
public class HandlingPrice implements Nlp {
@InputParams({
@InputParam(name = "String", type = "java.lang.String")
})
@ReturnType(name = "Details", type = "java.util.List")

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
String inputText = (String) attributes.get("BillSummaryText");
List<Double> extractedNumbers = new ArrayList<>();
try {
// Regex to match numbers (with optional ₹, +, - and decimal)
Pattern pattern = Pattern.compile("[-+]?₹?\\d+(\\.\\d+)?");
Matcher matcher = pattern.matcher(inputText);
while (matcher.find()) {
String match = matcher.group().replace("₹", ""); // Remove currency symbol
try {
extractedNumbers.add(Double.parseDouble(match));
} catch (NumberFormatException e) {
// Skip any invalid number
}
}
nlpResponseModel.setMessage("Extracted numbers successfully");
nlpResponseModel.setStatus(CommonConstants.pass);
} catch (Exception e) {
nlpResponseModel.setMessage("Failed to extract numbers: " + e.getMessage());
nlpResponseModel.setStatus(CommonConstants.fail);
}
// Add the extracted numbers to the output attributes
nlpResponseModel.getAttributes().put("numbers", extractedNumbers);
return nlpResponseModel;
}
}
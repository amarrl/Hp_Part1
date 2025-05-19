package Business_Logics;




import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;


public class Compare_Two_Images implements Nlp {
    @InputParams({@InputParam(name = "Actaul Image File Path", type = "java.lang.String"), @InputParam(name = "Expected Image File Path", type = "java.lang.String")})
    @ReturnType(name = "Assign Step to Return Value", type = "java.lang.String")

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
      
      boolean returnValue=false;
      @Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();
          String actualFile = (String) attributes.get("Actaul Image File Path");
          String expectedFile = (String) attributes.get("Expected Image File Path");

          // Your program element business logic goes here ...
          try {
        	  
        	  File file1 = new File(actualFile);
      		File file2 = new File(expectedFile);
          BufferedImage expectedImage = ImageIO.read(file1);
          BufferedImage actualImage = ImageIO.read(file2);
          ImageDiffer imgDiff = new ImageDiffer();
          ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
          returnValue = diff.hasDiff();
          if(returnValue==false)
          {
           nlpResponseModel.setStatus(CommonConstants.pass);
           nlpResponseModel.setMessage("Images are matching");
          }
          else
          {
        	  nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage("Images are not matching"); 
          }
          }catch (Exception e) {
			// TODO: handle exception
        	  e.getMessage();	  
		}

//          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("Assign Step to Return Value", returnValue);
          return nlpResponseModel;
      }
  } 

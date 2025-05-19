package Logics;



import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

public class PDFImageExtractor implements Nlp {
	@InputParams({@InputParam(name = "PDF path", type = "java.lang.String"), @InputParam(name = "Image Download Path", type = "java.lang.String")})
	//    @ReturnType(name = "string3", type = "java.lang.String")

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
		String pdf = (String) attributes.get("PDF path");
		String image = (String) attributes.get("Image Download Path");

		// Your program element business logic goes here ...
		try {
			String outputDir = image;

			PDDocument document = PDDocument.load(new File(pdf));
			PDFRenderer pdfRenderer = new PDFRenderer(document);

			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				// Render the image from the page
				BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

				// Save the image as a PNG file
				String fileName = outputDir + "image-page-" + (page + 1) + ".png";
				ImageIO.write(bim, "png", new File(fileName));
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully performed Click Operation ");	
		}catch (Exception e) {
			// TODO: handle exception
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully performed Click Operation "+e.getMessage());	
		}

		return nlpResponseModel;
	}
} 

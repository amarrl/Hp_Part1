package business_logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class ExtractFileFromZipFolder implements Nlp {
	@InputParams({ @InputParam(name = "FolderName", type = "java.lang.String") })
//	@ReturnType(name = "Assign return step value to", type = "java.lang.String")

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

	public static void extractZipFile(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		// Create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// Buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				// Create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// Close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// Close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		String FolderName = (String) attributes.get("FolderName");

		// Your program element business logic goes here ...
		try {

			//WebDriverManager.chromedriver().setup();
			String one = "/Downloads/";
			String folderPath = FolderName;
			String folder = one.concat(folderPath);

			// Path to the downloaded ZIP file
			String zipFilePath = System.getProperty("user.home") + folder;
			// Path to extract the ZIP file contents
			String destDir = System.getProperty("user.home") + "/Downloads/";

			// Extract the ZIP file
			extractZipFile(zipFilePath, destDir);
			nlpResponseModel.setMessage("Zip Folder Extracted Successfully");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {

			nlpResponseModel.setMessage("Failed to extract zip folder" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		return nlpResponseModel;
	}
}

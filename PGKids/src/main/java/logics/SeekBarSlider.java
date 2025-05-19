package logics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;

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
import io.appium.java_client.touch.offset.PointOption;

public class SeekBarSlider implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "FullScreenshotImagePath", type = "java.lang.String"),
			@InputParam(name = "SubImagePath", type = "java.io.InputStream"),
			@InputParam(name = "percentage", type = "java.lang.Integer") })
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
		WebElement el = (WebElement) attributes.get("element");
		int perc = (int) attributes.get("percentage");
		String FullScreenshotImagePath = (String) attributes.get("FullScreenshotImagePath");
		InputStream SubImagePath = (InputStream) attributes.get("SubImagePath");
		AndroidDriver androidDriver = null;
		IOSDriver iosDriver = null;
		try {
			androidDriver = nlpRequestModel.getAndroidDriver();
			iosDriver = nlpRequestModel.getIosDriver();
		} catch (Exception e) {
			iosDriver = nlpRequestModel.getIosDriver();
		}
		try {

			if (androidDriver != null) {
				int x = el.getLocation().getX() + (el.getRect().getWidth()) * perc / 100;
				int y = el.getLocation().getY();
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence tapSequence = new Sequence(finger, 0)
				        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y)) // Move to the point
				        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))              // Press down
				        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));              // Release

				// Perform the sequence
				androidDriver.perform(Collections.singletonList(tapSequence));
				//TouchAction touchAction = new TouchAction<>(androidDriver);
				//PointOption point = new PointOption<>();
				//touchAction.tap(point.point(x, y)).perform();

				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully performed Action");

			} else {
				BufferedImage SubImagePathbuffer = ImageIO.read(SubImagePath);

				toDrag(iosDriver, FullScreenshotImagePath, SubImagePathbuffer, perc);

				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully performed Action");
			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform swipe Action " + e.getMessage());
		}
		return nlpResponseModel;
	}

	public static void toDrag(IOSDriver driver, String FullBaseImage, BufferedImage subimage, int PercentageToBeSlide)
			throws IOException {

		BufferedImage image2 = ImageIO.read(new File(FullBaseImage));

		List list = findImage(subimage, image2);

		int screenX = ((int) (list.get(0)) * driver.manage().window().getSize().getWidth()) / image2.getWidth();
		int screenY = ((int) (list.get(1)) * driver.manage().window().getSize().getHeight()) / image2.getHeight();

		System.out.println(screenX);
		System.out.println(screenY);

		int width = driver.manage().window().getSize().getWidth();
		double intialx = (width * 18) / 100;
		double silderValue = (width * 66) / 100;

		int xvalue = (int) (((silderValue * PercentageToBeSlide) / 100) + intialx);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence dragAndDrop = new Sequence(finger, 0);

		dragAndDrop.addAction(
				finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), screenX, screenY));
		dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		dragAndDrop.addAction(
				finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), xvalue, screenY));

		dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(dragAndDrop));
	}

	public static List findImage(BufferedImage targetImagePath, BufferedImage searchImage) {
		Pattern targetPattern = new Pattern(targetImagePath).similar(0.9); // Similarity threshold
		Finder finder = new Finder(searchImage);
		finder.find(targetPattern);
		List li = new ArrayList<String>();

		int matchCount = 0;
		while (finder.hasNext()) {
			Match match = finder.next();
			int x = match.getX();
			int y = match.getY();
			int w = match.getW();
			int h = match.getH();
			System.out.println("Match found at: " + x + ", " + y + ", " + (x + w) + ", " + (y + h));

			li.add(0, x + (w));
			li.add(1, y + (h));

			matchCount++;
		}

		if (matchCount != 0) {
			System.out.println("Match Found!");
		} else {
			System.out.println("Match not Found!");
		}
		return li;
	}

}

package Bussiness_Logic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC20134_PJT1001_PE_NLPe68203af-783e-49f6-83c2-591b694e033d")
public class EnterInputUsingRobo implements Nlp {
	@InputParams({ @InputParam(name = "Input String", type = "java.lang.String") })
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
		try {
			System.setProperty("java.awt.headless", "false");
			String string1 = (String) attributes.get("Input String");
			boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (isOn == true) {
				Robot ro = new Robot();
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
			}
			boolean value = KEvents(string1);
			if (value == true) {
				nlpResponseModel.setMessage("Sucessfully Entered " + string1);
				nlpResponseModel.setStatus(CommonConstants.pass);
			} else {
				nlpResponseModel.setMessage("Failed Entered because character is not present");
				nlpResponseModel.setStatus(CommonConstants.fail);
			}
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to Execute" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}

		return nlpResponseModel;
	}

	public static boolean KEvents(String s) throws AWTException, InterruptedException {
		System.setProperty("java.awt.headless", "false");
		Robot ro = new Robot();
		char[] c = s.toCharArray();
		boolean value = true;
		for (int i = 0; i < c.length; i++) {

			switch (c[i]) {
			case '@':
				ro.keyPress(KeyEvent.VK_SHIFT);
				ro.keyPress(KeyEvent.VK_2);
				ro.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case '.':
				ro.keyPress(KeyEvent.VK_PERIOD);
				break;
			case '#':
				ro.keyPress(KeyEvent.VK_SHIFT);
				ro.keyPress(KeyEvent.VK_3);
				ro.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case 'A':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_A);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'B':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_B);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'C':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_C);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'D':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_D);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'E':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_E);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'F':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_F);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'G':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_G);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'H':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_H);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'I':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_I);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'J':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_J);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'K':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_K);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'L':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_L);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'M':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_M);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'N':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_N);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'O':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_O);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'P':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_P);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'Q':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_Q);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'R':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_R);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'S':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_S);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'T':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_T);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'U':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_U);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'V':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_V);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'W':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_W);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'X':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_X);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'Y':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_Y);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case 'Z':
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_Z);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				ro.keyPress(KeyEvent.VK_CAPS_LOCK);
				ro.keyRelease(KeyEvent.VK_CAPS_LOCK);
				break;
			case '0':
				ro.keyPress(KeyEvent.VK_0);
				break;
			case '1':
				ro.keyPress(KeyEvent.VK_1);
				break;
			case '2':
				ro.keyPress(KeyEvent.VK_2);
				break;
			case '3':
				ro.keyPress(KeyEvent.VK_3);
				break;
			case '4':
				ro.keyPress(KeyEvent.VK_4);
				break;
			case '5':
				ro.keyPress(KeyEvent.VK_5);
				break;
			case '6':
				ro.keyPress(KeyEvent.VK_6);
				break;
			case '7':
				ro.keyPress(KeyEvent.VK_7);
				break;
			case '8':
				ro.keyPress(KeyEvent.VK_8);
				break;
			case '9':
				ro.keyPress(KeyEvent.VK_9);
				break;
			case 'a':
				ro.keyPress(KeyEvent.VK_A);
				break;
			case 'b':
				ro.keyPress(KeyEvent.VK_B);
				break;
			case 'c':
				ro.keyPress(KeyEvent.VK_C);
				break;
			case 'd':
				ro.keyPress(KeyEvent.VK_D);
				break;
			case 'e':
				ro.keyPress(KeyEvent.VK_E);
				break;
			case 'f':
				ro.keyPress(KeyEvent.VK_F);
				break;
			case 'g':
				ro.keyPress(KeyEvent.VK_G);
				break;
			case 'h':
				ro.keyPress(KeyEvent.VK_H);
				break;
			case 'i':
				ro.keyPress(KeyEvent.VK_I);
				break;
			case 'j':
				ro.keyPress(KeyEvent.VK_J);
				break;
			case 'k':
				ro.keyPress(KeyEvent.VK_K);
				break;
			case 'l':
				ro.keyPress(KeyEvent.VK_L);
				break;
			case 'm':
				ro.keyPress(KeyEvent.VK_M);
				break;
			case 'n':
				ro.keyPress(KeyEvent.VK_N);
				break;
			case 'o':
				ro.keyPress(KeyEvent.VK_O);
				break;
			case 'p':
				ro.keyPress(KeyEvent.VK_P);
				break;
			case 'q':
				ro.keyPress(KeyEvent.VK_Q);
				break;
			case 'r':
				ro.keyPress(KeyEvent.VK_R);
				break;
			case 's':
				ro.keyPress(KeyEvent.VK_S);
				break;
			case 't':
				ro.keyPress(KeyEvent.VK_T);
				break;
			case 'u':
				ro.keyPress(KeyEvent.VK_U);
				break;
			case 'v':
				ro.keyPress(KeyEvent.VK_V);
				break;
			case 'w':
				ro.keyPress(KeyEvent.VK_W);
				break;
			case 'x':
				ro.keyPress(KeyEvent.VK_X);
				break;
			case 'y':
				ro.keyPress(KeyEvent.VK_Y);
				break;
			case 'z':
				ro.keyPress(KeyEvent.VK_Z);
				break;
			default:
				value = false;
				System.out.println("Not match to the Dial Keypad");
				break;
			}
			Thread.sleep(100);

		}
		return value;

	}
}
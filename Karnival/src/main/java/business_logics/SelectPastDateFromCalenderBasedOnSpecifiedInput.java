package business_logics;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;



public class SelectPastDateFromCalenderBasedOnSpecifiedInput implements Nlp {
	@InputParams({ @InputParam(name = "fromTodate", type = "java.lang.String") })
	@ReturnType(name = "dateRange", type = "java.lang.String")

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

	public static void selectDate(WebDriver driver, String fromToDate) {
		String[] fromToDateArray = fromToDate.split("to");
		String monthYearText2 = driver
				.findElement(By
						.xpath("(//tui-primitive-year-month-pagination[@automation-id='tui-calendar__pagination'])[2]"))
				.getText();
		boolean isFirstPage = true;
		for (int i = fromToDateArray.length - 1; i >= 0; i--) {
			while (true) {
				String monthYearText1 = driver.findElement(By
						.xpath("(//tui-primitive-year-month-pagination[@automation-id='tui-calendar__pagination'])[1]"))
						.getText();
				if (monthYearText2.contains(fromToDateArray[i].trim().split(" ", 2)[1].replace(",", "")) && isFirstPage) {
					driver.findElement(By.xpath(
							"(//tui-primitive-year-month-pagination[@automation-id='tui-calendar__pagination'])[2]/following-sibling::tui-primitive-calendar/descendant::div[normalize-space(text())='"
									+ fromToDateArray[i].split(" ", 2)[0] + "']"))
					.click();
					break;
				} else if (monthYearText1.contains(fromToDateArray[i].split(" ", 2)[1].replace(",",""))) {
					driver.findElement(By.xpath(
							"(//tui-primitive-year-month-pagination[@automation-id='tui-calendar__pagination'])[1]/following-sibling::tui-primitive-calendar/descendant::div[normalize-space(text())='"
									+ fromToDateArray[i].split(" ", 2)[0] + "']"))
					.click();
					break;
				} else {
					driver.findElement(By.xpath("(//button[@title='Previous'])[1]")).click();
					isFirstPage = false;
				}
			}
		}
	}

	public static String getFromToDate(String selectionCriteria) {
		LocalDate startDate = null;
		LocalDate endDate = LocalDate.now();

		switch (selectionCriteria) {
		case "Last 7 Days":
			startDate = endDate.minus(6, ChronoUnit.DAYS);
			break;

		case "Last 28 Days":
			startDate = endDate.minus(27, ChronoUnit.DAYS);
			break;

		case "This Month":
			YearMonth currentMonth = YearMonth.now();
			startDate = currentMonth.atDay(1);
			break;

		case "Previous Month":
			YearMonth previousMonth = YearMonth.now().minusMonths(1);
			startDate = previousMonth.atDay(1);
			endDate = previousMonth.atEndOfMonth();
			break;

		case "Last 3 Months":
			startDate = endDate.minus(3, ChronoUnit.MONTHS);
			break;

		case "Last 6 Months":
			startDate = endDate.minus(6, ChronoUnit.MONTHS);
			break;

		case "Last 12 Months":
			startDate = endDate.minus(12, ChronoUnit.MONTHS);
			break;

		case "Custom Date":
			System.out.println("Invalid Criteria specified, try again with valid one");
			break;
		default:
			break;
		}

		String startDateString = new SimpleDateFormat("d MMMM, yyyy")
				.format(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		String endDateString = new SimpleDateFormat("d MMMM, yyyy")
				.format(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return startDateString + "to" + endDateString;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		WebDriver driver = nlpRequestModel.getWebDriver();
		String fromTodate = (String) attributes.get("fromTodate");

		String fromToDateInString = null;
		// Your program element business logic goes here ...
		try {
			String fromToDateInput = fromTodate;

			if (fromToDateInput.startsWith("Custom")) {
				fromToDateInString = fromToDateInput.split(":")[1].trim();
				selectDate(driver, fromToDateInput.split(":")[1].trim());
				String fromDateReturn = formatDate(fromToDateInString.split("to")[0]);
				String toDateReturn = formatDate(fromToDateInString.split("to")[1]);
				fromToDateInString = fromDateReturn +" to "+toDateReturn;
			} else {
				fromToDateInString = getFromToDate(fromToDateInput);
				selectDate(driver, fromToDateInString);
				String fromDateReturn = formatDate(fromToDateInString.split("to")[0]);
				String toDateReturn = formatDate(fromToDateInString.split("to")[1]);
				fromToDateInString = fromDateReturn +" to "+toDateReturn;
			}
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully fetched the date");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch the date");
		}

		nlpResponseModel.getAttributes().put("dateRange", fromToDateInString);
		return nlpResponseModel;
	}

	private static String formatDate(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM, yyyy", Locale.ENGLISH);

		LocalDate localDate = LocalDate.parse(date, inputFormatter);
		return localDate.format(outputFormatter);
	}

}

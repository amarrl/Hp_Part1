package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//html , xml, json, junit, extent

@CucumberOptions(features="src/test/java/features",glue="stepDefinations",monochrome=true,tags="@PlaceOrder",
plugin= {"html:target/Reports/cucumber.html",
		  "com.avetstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		  "rerun:target/failed_scenarios.txt"}

)  
public class TestNGTestRunner extends AbstractTestNGCucumberTests { 
 
	@Override
	@DataProvider(parallel=true)
	public Object[][]scenarios(){
		return super.scenarios();
	}

}




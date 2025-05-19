package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",glue="stepDefinations"
,monochrome=true,tags="@SmokeTest and @Regression"      //tags="@SmokeTest or @Regression"   //tags="not @Regression"
,plugin= {"pretty","html:Reports/cucumber.html","json:Reports/cucumber.json"})  
 
public class TestNGRunner extends AbstractTestNGCucumberTests { 


}  

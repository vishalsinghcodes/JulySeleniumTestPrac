package cucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "C:\\Users\\Vishal Singh\\eclipse-workspace\\E2ESelProjectJuly2024\\src\\test\\java\\cucumber", 
		glue = "qaClick.stepdefinitions",
		monochrome =true,
		tags="@negative",  // @all and also we have @positive
		plugin= {"html:target/cucmber.html"}
		)
public class CucumberRunner extends AbstractTestNGCucumberTests{

}

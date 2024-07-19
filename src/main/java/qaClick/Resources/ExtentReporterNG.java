package qaClick.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//TestReportsExtent//Testreport.htlm";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Report");
		reporter.config().setReportName("Automation Test Results");
		
		ExtentReports extent	= new ExtentReports();
		
		String testerName = System.getProperty("tester")!=null?System.getProperty("tester"):"Vishal Singh PC default";
		
		extent.setSystemInfo("Tester", testerName);
		extent.attachReporter(reporter);
		return extent;
		
	}
	

}

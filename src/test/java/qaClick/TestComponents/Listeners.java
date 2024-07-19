package qaClick.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qaClick.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	WebDriver driver;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> safeTest = new ThreadLocal<ExtentTest>();
	String ScreenShotPath;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		safeTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// test.pass(result.getMethod().getMethodName());
		safeTest.get().log(Status.PASS, result.getMethod().getMethodName() + "is passed");

	}

	public void onTestFailure(ITestResult result) {
		// test.fail(result.getMethod().getMethodName());
		safeTest.get().log(Status.FAIL, result.getMethod().getMethodName() + "is failed");
		safeTest.get().log(Status.FAIL, result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ScreenShotPath = getScreenShot(driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		safeTest.get().addScreenCaptureFromPath(ScreenShotPath, result.getMethod().getMethodName());

	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

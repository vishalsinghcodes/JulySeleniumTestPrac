package qaClick.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import qaClick.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		String pathToGlobalProperties = System.getProperty("user.dir")
				+ "\\src\\main\\java\\qaClick\\Resources\\Globaldata.properties";
		FileInputStream fis = new FileInputStream(pathToGlobalProperties);
		Properties prop = new Properties();
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		String headOption = System.getProperty("headOption")!=null?System.getProperty("headOption"):prop.getProperty("headOption");
		
		switch (browserName) {
		case "chrome":
			ChromeOptions cop = new ChromeOptions();
			cop.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			cop.addArguments(headOption);
			driver = new ChromeDriver(cop);
			break;
		case "firefox":
			FirefoxOptions fop = new FirefoxOptions();
			fop.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			fop.addArguments(headOption);
			driver = new FirefoxDriver(fop);
			break;
		case "edge":
			EdgeOptions eop = new EdgeOptions();
			eop.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			eop.addArguments(headOption);
			driver = new EdgeDriver(eop);
			break;
		default:
			ChromeOptions op = new ChromeOptions();
			op.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			op.addArguments(headOption);
			driver = new ChromeDriver(op);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if(headOption.equals("headless")) {
			driver.manage().window().setSize(new Dimension(1440, 900));
		}else {
			driver.manage().window().maximize();
		}
		
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToLandingPage();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> jsonDataConvertor(String jsonfilePath) throws IOException {
		String stringOfJson = FileUtils.readFileToString(new File(jsonfilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(stringOfJson, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
	}
	
	public String getScreenShot(WebDriver driver ,String testCaseName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = System.getProperty("user.dir")+ "//reports//"+testCaseName+".png";
		FileUtils.copyFile(src, new File(screenShotPath));
		return screenShotPath;		
	}

}

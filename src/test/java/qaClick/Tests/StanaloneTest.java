package qaClick.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import qaClick.PageObjects.LandingPage;

public class StanaloneTest {
	
	
	@Test(enabled=false)
	public void stanAloneTest() throws InterruptedException {
		ChromeOptions op = new ChromeOptions();
		op.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		WebDriver driver = new ChromeDriver(op);
		String proname = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("Udemyselenium@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password1234");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@class='card']"))));
		List<WebElement> proCards = driver.findElements(By.xpath("//div[@class='card']"));
		proCards.stream().filter(s->s.findElement(By.xpath("div/h5/b")).getText().contains(proname))
		.map(s->s.findElement(By.xpath("div/button[2]"))).limit(1).forEach(s->s.click());
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".infoWrap div h3"))));
		Assert.assertTrue(driver.findElements(By.cssSelector(".infoWrap div h3")).stream().anyMatch(s->s.getText().contains(proname)));
		driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("span.ng-star-inserted"))));
		driver.findElements(By.cssSelector("span.ng-star-inserted")).stream().filter(s->s.getText().contains("Indonesia")).limit(1).forEach(s->s.click());
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		List<WebElement> orderIDList = driver.findElements(By.cssSelector(".em-spacer-1 .ng-star-inserted"));
		orderIDList.stream().forEach(s->System.out.println(s.getText()));
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		//.ng-tns-c31-3.ng-star-inserted
		driver.close();
	}

}

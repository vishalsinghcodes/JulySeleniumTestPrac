package qaClick.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaClick.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//div[@class='card']")
	List<WebElement> productCardListEle;
	
	@FindBy(css="#toast-container")
	WebElement toastEle;
	
	@FindBy(css=".ng-animating")
	WebElement spinnerEle;
	
	
	By productTextOnProCards = By.xpath("div/h5/b");
	By addToCartButtonOnProCards = By.xpath("div/button[2]");
	
	public void addToCart(String proName) throws InterruptedException {
		waitForElementsToAppear(productCardListEle);
		Thread.sleep(2000);
		productCardListEle.stream().filter(s->s.findElement(productTextOnProCards).getText().contains(proName))
		.map(s->s.findElement(By.xpath("div/button[2]"))).limit(1).forEach(s->s.click());	
		waitForElementToAppear(toastEle);
		waitForElementToBeInvisible(spinnerEle);
	}
	

//	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	
	
	
}

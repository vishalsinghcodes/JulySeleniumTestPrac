package qaClick.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaClick.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="userEmail")
	WebElement userEmailEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginButtonEle;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement incorrectPasswordToastEle;
	
	
	public ProductCatalogue loginApplication(String username, String password) {
		userEmailEle.sendKeys(username);
		passwordEle.sendKeys(password);
		loginButtonEle.click();	
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;	
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(incorrectPasswordToastEle);
		return incorrectPasswordToastEle.getText();
	}
	
	public void goToLandingPage() {
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	

}

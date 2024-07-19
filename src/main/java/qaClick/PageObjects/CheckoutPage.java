package qaClick.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import qaClick.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submitFinalOrder;

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryDropDown;
	
	public void selectCountrytext(String textToCountryDropdown) {
		countryDropDown.sendKeys(textToCountryDropdown);
	}

	public void selectCountryFinal(String countryname) {
		waitForElementsToAppear(driver.findElements(By.cssSelector("span.ng-star-inserted")));
		driver.findElements(By.cssSelector("span.ng-star-inserted")).stream().filter(s->s.getText().contains(countryname)).limit(1).forEach(s->s.click());
	}
	
	public OrderConfirmationPage clicksubmitFinalOrder() {
		submitFinalOrder.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}




}

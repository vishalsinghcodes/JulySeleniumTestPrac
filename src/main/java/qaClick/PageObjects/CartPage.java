package qaClick.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import qaClick.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".infoWrap div h3")
	List<WebElement> productInCartNamesEle;
	
	@FindBy(xpath="//button[@class='btn btn-primary' and text()='Checkout']")
	WebElement checkoutButtonEle;


	public boolean checkProductinCartPage(String proname) {
		waitForElementsToAppear(productInCartNamesEle);
		return driver.findElements(By.cssSelector(".infoWrap div h3")).stream().anyMatch(s->s.getText().contains(proname));
		
	}
	
	public CheckoutPage clickCheckoutButton() {
		checkoutButtonEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}



}

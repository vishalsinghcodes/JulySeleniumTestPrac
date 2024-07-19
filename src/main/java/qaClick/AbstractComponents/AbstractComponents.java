package qaClick.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaClick.PageObjects.CartPage;
import qaClick.PageObjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver ;
	
	public AbstractComponents(WebDriver driver){
		this.driver = driver;
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartbuttonEle;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButtonEle;
	
	public CartPage clickCartButton() {
		cartbuttonEle.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage clickOrdersButton() {
		orderButtonEle.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

	
	
	public void waitForElementsToAppear(List<WebElement> eles) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(eles));	
	}
	
	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));	
	}
	
	public void waitForElementToBeInvisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));	
	}
	

}

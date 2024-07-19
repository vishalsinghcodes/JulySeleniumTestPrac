package qaClick.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaClick.AbstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".em-spacer-1 .ng-star-inserted")
	List<WebElement> orderIDList;
	
	@FindBy(css=".hero-primary")
	WebElement headTextEle;
	




	
	public void printOrderIDList() {
		orderIDList.stream().forEach(s->System.out.println(s.getText()));
	}
	
	public boolean checkHeadText(String expectedHeadText) {
		return headTextEle.getText().equalsIgnoreCase(expectedHeadText);
		
	}
	




}

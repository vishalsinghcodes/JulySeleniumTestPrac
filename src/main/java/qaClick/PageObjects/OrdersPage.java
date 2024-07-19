package qaClick.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import qaClick.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	
	WebDriver driver;
	public OrdersPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".ng-star-inserted td:nth-child(3)")
	List<WebElement> orderedItemsList;
	
	
	public boolean checkItemOrdered(String proname) {
		return orderedItemsList.stream().anyMatch(s->s.getText().contains(proname));
	}


	



}

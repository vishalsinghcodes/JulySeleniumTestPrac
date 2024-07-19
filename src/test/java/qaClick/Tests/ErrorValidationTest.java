package qaClick.Tests;

import java.io.IOException;
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

import qaClick.PageObjects.CartPage;
import qaClick.PageObjects.CheckoutPage;
import qaClick.PageObjects.LandingPage;
import qaClick.PageObjects.OrderConfirmationPage;
import qaClick.PageObjects.ProductCatalogue;
import qaClick.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	
	@Test(priority=0, groups= {"errorHandling", "purchaseorder"})
	public void errrValidationOnLogin() throws InterruptedException, IOException {		
		ProductCatalogue productCatalogue =landingpage.loginApplication("Udemyselenium@gmail.com", "wrongPass");
		Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.");	
	}
	
	@Test(priority=1,groups= {"errorHandling", "purchaseorder"})
	public void productErrorValidation() throws InterruptedException, IOException {		
		String proname = "ZARA COAT 3";
		ProductCatalogue productCatalogue =landingpage.loginApplication("Udemyselenium@gmail.com", "Password1234");
		productCatalogue.addToCart(proname);
		CartPage cartPage = productCatalogue.clickCartButton();
		Assert.assertFalse(cartPage.checkProductinCartPage("wrong Product")); // here I have given wrong product deliberately to fail the test and this test will fail. 
		//and now udpated to asserFalse as we will always get false

	}

}

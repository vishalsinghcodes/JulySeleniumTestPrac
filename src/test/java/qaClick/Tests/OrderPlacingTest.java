package qaClick.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import qaClick.PageObjects.CartPage;
import qaClick.PageObjects.CheckoutPage;
import qaClick.PageObjects.LandingPage;
import qaClick.PageObjects.OrderConfirmationPage;
import qaClick.PageObjects.OrdersPage;
import qaClick.PageObjects.ProductCatalogue;
import qaClick.TestComponents.BaseTest;
import qaClick.TestComponents.RetryClass;

public class OrderPlacingTest extends BaseTest {

	@Test(dataProvider = "dataProSimple", groups = "purchaseorder")
	public void placeOrder(String username, String password, String proname) throws InterruptedException, IOException {
		ProductCatalogue productCatalogue = landingpage.loginApplication(username, password);
		productCatalogue.addToCart(proname);
		CartPage cartPage = productCatalogue.clickCartButton();
		Assert.assertTrue(cartPage.checkProductinCartPage(proname));
		CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
		checkoutPage.selectCountrytext("ind");
		checkoutPage.selectCountryFinal("Indonesia");
		OrderConfirmationPage orderConfirmationPage = checkoutPage.clicksubmitFinalOrder();
		// orderConfirmationPage.printOrderIDList();
		Assert.assertTrue(orderConfirmationPage.checkHeadText("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "jsondatapro", groups = "purchaseorder", retryAnalyzer = RetryClass.class)
	public void placeOrdertoRunWithJasondata(HashMap<String, String> data) throws InterruptedException, IOException {
		ProductCatalogue productCatalogue = landingpage.loginApplication(data.get("username"), data.get("password"));
		productCatalogue.addToCart(data.get("proname"));
		CartPage cartPage = productCatalogue.clickCartButton();
		Assert.assertTrue(cartPage.checkProductinCartPage(data.get("proname")));
		CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
		checkoutPage.selectCountrytext("ind");
		checkoutPage.selectCountryFinal("Indonesia");
		OrderConfirmationPage orderConfirmationPage = checkoutPage.clicksubmitFinalOrder();
		// orderConfirmationPage.printOrderIDList();
		Assert.assertTrue(orderConfirmationPage.checkHeadText("THANKYOU FOR THE ORDER .")); // Inserted extra space to fail this test 
	}

	@Test(dependsOnMethods = { "placeOrder" })
	public void checkOrderPlacedTest() throws InterruptedException, IOException {
		String proname = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("Udemyselenium@gmail.com", "Password1234");
		OrdersPage ordersPage = productCatalogue.clickOrdersButton();
		Assert.assertTrue(ordersPage.checkItemOrdered(proname));

	}

	@DataProvider
	public Object[][] dataProSimple() {

		return new Object[][] { { "Udemyselenium@gmail.com", "Password1234", "ZARA COAT 3" },
				{ "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };

	}

	@DataProvider
	public Object[] jsondatapro() throws IOException {
		List<HashMap<String, String>> datafromjson = jsonDataConvertor(
				System.getProperty("user.dir") + "\\src\\test\\java\\qaClick\\TestData\\purchaseOrderData.json");
		int noOfData = datafromjson.size();
		Object[] dataToTest = new Object[noOfData];
		for (int i = 0; i < noOfData; i++) {
			dataToTest[i] = datafromjson.get(i);
		}
		return dataToTest;
	}

	public String getScreenShot(String testCaseName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = System.getProperty("user.dir")+ "//reports//"+testCaseName+".png";
		FileUtils.copyFile(src, new File(screenShotPath));
		return screenShotPath;
		
	}

}

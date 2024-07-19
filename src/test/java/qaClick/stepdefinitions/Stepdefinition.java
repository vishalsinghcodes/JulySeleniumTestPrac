package qaClick.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qaClick.PageObjects.CartPage;
import qaClick.PageObjects.CheckoutPage;
import qaClick.PageObjects.LandingPage;
import qaClick.PageObjects.OrderConfirmationPage;
import qaClick.PageObjects.ProductCatalogue;
import qaClick.TestComponents.BaseTest;

public class Stepdefinition extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public OrderConfirmationPage orderConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	

	@Given("I Logged in with {string} and {string}")
	public void I_Logged_in_with_username_and_password(String username, String password){
		productCatalogue = landingpage.loginApplication(username, password);	
	}
	
	@When("I added product {string} to the cart")
	public void I_added_product_productname_to_the_cart(String proname) throws InterruptedException {
		productCatalogue.addToCart(proname);
	}
	
	 @And("Checkout {string} and submit order")
	 public void Checkout_productname_and_submit_order(String proname) {
		 cartPage = productCatalogue.clickCartButton();
		Assert.assertTrue(cartPage.checkProductinCartPage(proname));
		checkoutPage = cartPage.clickCheckoutButton();
		checkoutPage.selectCountrytext("ind");
		checkoutPage.selectCountryFinal("Indonesia");
		orderConfirmationPage = checkoutPage.clicksubmitFinalOrder();
	 }
	 
	 @Then("Confirmation message {string} is displayed")
	 public void Confirmation_message_is_displayed(String finalMessage) {
		 Assert.assertTrue(orderConfirmationPage.checkHeadText(finalMessage));
		 driver.close();
	 }
	 
	 @Then("{string} toast is displayed")
	    public void incorrect_email_or_password_toast_is_displayed(String incorrecttoastMessage) {
		Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.");	
        driver.close();
	    }
	
	

}

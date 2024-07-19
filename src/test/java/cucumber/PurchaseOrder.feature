#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase product from ECommerce
  This feature File will test if we are able to place orders under different scenarios

  Background: 
    Given I landed on Ecommerce Page

  @positive  @all
  Scenario Outline: Positive test of purchasing the product
    Given I Logged in with "<username>" and "<password>"
    When I added product "<productname>" to the cart
    And Checkout "<productname>" and submit order
    Then Confirmation message "THANKYOU FOR THE ORDER." is displayed

    Examples: 
      | username                | password     | productname     |
      | Udemyselenium@gmail.com | Password1234 | ZARA COAT 3     |
      | shetty@gmail.com        | Iamking@000  | ADIDAS ORIGINAL |

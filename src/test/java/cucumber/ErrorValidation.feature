@tag
Feature: Error validations
  This feature File will test all the negative scenarios to validate the error handlings

  # Here we have give landing on the landing page as the first step of below scenario, Its your choice you can give it in the backgroud also
  @negative @all
  Scenario Outline: Negative Test on loggin in with incorrect password
    Given I landed on Ecommerce Page
    When I Logged in with "<username>" and "<password>"
    Then "Incorrect email or password." toast is displayed

    Examples: 
      | username                | password      |
      | Udemyselenium@gmail.com | wrongpassword |
      | shetty@gmail.com        | wrongpassword |

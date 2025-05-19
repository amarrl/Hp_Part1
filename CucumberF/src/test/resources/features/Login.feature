

Feature: Login

  Scenario: Valid login credentials
    Given User has navigated to Login Page
    When User has entered valid email address "amarraj8050@mailinator.com"
    And User has entered valid password "Password@123"
    Then User should successfully log in
    
 
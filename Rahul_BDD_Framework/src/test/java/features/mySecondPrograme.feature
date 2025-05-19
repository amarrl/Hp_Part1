Feature: Application Login

Background:
Given setup the entries in database
When Luanch the browser using config variables
And Hit the home page url of banking page

#Before->Background->Scenario->After


@MobileTest @NetBanking
Scenario: User Page Login

Given User is on NetBanking landing page
When User login into the application with "user" and password 1234
Then Home Page is displyed
And Cards are displayed

@SmokeTest @Regression @Mortgage
Scenario Outline: User Page Login

Given User is on NetBanking landing page
When User login into the application with "<userName>" and password "<password>" 
Then Home Page is displyed
And Cards are displayed

Examples:
|userName |password|
|debitUser|debit@123|
|creditUse|credit@123|


Feature: Application Login

Background:
Given setup the entries in database
When Luanch the browser using config variables
And Hit the home page url of banking page

Scenario: Admin Page Login

Given User is on NetBanking landing page
When User login into the application with "admin" and password "1234"
Then Home Page is displyed
And Cards are displayed


#Reusable and  below 1st one is regex "^(.+)$" below of this no need to mesntion the data in string i.e " " 

Scenario: User Page Login

Given User is on NetBanking landing page
When User login into the application with "user" and password 1234
Then Home Page is displyed
And Cards are displayed

@SmokeTest
Scenario Outline: User Page Login

Given User is on NetBanking landing page
When User login into the application with "<userName>" and password "<password>" 
Then Home Page is displyed
And Cards are displayed

Examples:
|userName |password|
|debitUser|debit@123|
|creditUse|credit@123|

@SmokeTest @Regression
Scenario: User Page default Sign Up

Given User is on practice landing page
When User Signup Inro application with below details
|Amar|
|r|
|amarraks@gmail.com|
Then Home page is displyedd
And Crdas are displyed
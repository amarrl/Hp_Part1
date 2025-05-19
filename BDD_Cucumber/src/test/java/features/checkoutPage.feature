Feature: Search and Place the Order for Products
@PlaceOrder
Scenario Outline: Search Experiance for product search in both home and Offers page

Given User is on GreenCart Landing page
When User searched with short name <Vegi Name> and extract actual name of product
And added  <Quantity> items of selected product to cart
Then User proceeds to Checkout page and validate  for <Vegi Name>  exists
And Verify User has ability to enter promo code and place the order 

Examples:
|Vegi Name|Quantity|
|Tom|3|
|Beet|4|

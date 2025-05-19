Feature: Search and Place the Order for Products
@OffersPage
Scenario Outline: Search Experiance for product search in both home and Offers page

Given User is on GreenCart Landing page
When User searched with short name <Vegi Name> and extract actual name of product
Then User searches for <Vegi Name> the same short name in offers page to check if product exists
And Validate product name matches with Landing Page in offers page

Examples:
|Vegi Name|
|Tom|
|Beet|

package stepDefinations;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MainStepDefinations {
	

@Given("User is on NetBanking landing page")
public void user_is_on_net_banking_landing_page() {
    // Write code here that turns the phrase above into concrete actions
   System.out.println("User is on NetBanking landing page");
}
/*
@When("User login into the application with {string} and password {string}")
public void user_login_into_the_application_with_and_password(String userName, String passwordd) {
    // Write code here that turns the phrase above into concrete actions
 System.out.println(userName+"and password is "+passwordd);
}
*/
@When("^User login into the application with (.+) and password (.+)$")
public void user_login_into_the_application_with_and_password(String userName, String passwordd) {
    // Write code here that turns the phrase above into concrete actions
 System.out.println(userName+"and password is "+passwordd);
}
@Then("Home Page is displyed")
public void home_page_is_displyed() {
    // Write code here that turns the phrase above into concrete actions
System.out.println("Home Page is displyed");
}
@Then("Cards are displayed")
public void cards_are_displayed() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("Cards are displayed");
}


@Given("User is on practice landing page")
public void user_is_on_practice_landing_page() {
    // Write code here that turns the phrase above into concrete actions
   System.out.println("dat");
}
@When("User Signup Inro application with below details")
public void user_signup_inro_application_with_below_details(List<String>data) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    System.out.println(data.get(0));
    System.out.println(data.get(1));
    System.out.println(data.get(2));
}


@Then("Home page is displyedd")
public void home_page_is_displyedd() {
    // Write code here that turns the phrase above into concrete actions
 System.out.println("Implemented");
}

@Then("Crdas are displyed")
public void crdas_are_displyed() {
    // Write code here that turns the phrase above into concrete actions
  System.out.println("Done");
}

@Given ("setup the entries in database")
public void setup_the_entries_in_database() {
	System.out.println("*******************");
	System.out.println("setting up entries in database");
}

@When("Luanch the browser using config variables")
public void _Luanch_the_browser_using_config_variabsles() {
	System.out.println("Luanch the browser using config variables");
}

@When("Hit the home page url of banking page")
public void And_Hit_the_home_page_url_of_banking_page() {
	System.out.println("And Hit the home page url of banking page");
}



}

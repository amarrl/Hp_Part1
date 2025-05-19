package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("NetBanking")
	public void netBankingSetUp() { 
		System.out.println("**************************");
		System.out.println("setup the entries in Netbanking databases");
	}
	
	@Before("@Mortgage")
	public void mortagageSetup() {
		System.out.println("setup the entries in mortagage databases");
	}
	
	@After
	public void tearDown() {
		System.out.println("clear entries");
	}
}

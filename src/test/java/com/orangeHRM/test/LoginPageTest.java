package com.orangeHRM.test;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	
	private LoginPage loginpage;
	private HomePage homepage;
	
	@BeforeMethod
	public void setupPages() {
		loginpage = new LoginPage(getDriver());
		homepage =new HomePage(getDriver());
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
		loginpage.login("Admin","admin123");
		//Assert.assertTrue(homepage.adminTabVisible(),"Admin  tab should be visible after successful login");
		Assert.assertTrue(homepage.adminTabVisible());
		homepage.verifyLogo();
		homepage.logout();
		staticWait(2);
	}
	@Test
	public void loginInvalidCredentials() {
		loginpage.login("Admin","admin456");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginpage.verifyerrorMessage(expectedErrorMessage),"Test Failed : Invalid error message");
	}

}

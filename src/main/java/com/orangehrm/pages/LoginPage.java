package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {
	
	private ActionDriver actiondriver;
	
	
	
	// define locators using By class
	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
/*	//initialize the ActionDriver object  by passing webDriver Instance
	public LoginPage(WebDriver driver) {
		this.actiondriver=new  ActionDriver(driver); 
		
	}  */
	
	public LoginPage(WebDriver driver) {
		this.actiondriver =BaseClass.getActionDriver();
	}
	
	//method to perform login
	public void login(String username,String password) {
		actiondriver.enterText(usernameField, username);
		actiondriver.enterText(passwordField, password);
		actiondriver.click(loginButton);
	}
	
	//Method to check error message is displayed
	public boolean errorMessageDisplayed() {
		return actiondriver.isDisplayed(errorMessage);
	}
	
	//Method to get text from error message
	public String errormesaage() {
		return actiondriver.getText(errorMessage);
	}
   
	//Verify error message is correct
	public boolean verifyerrorMessage(String expectedError) {
		 return actiondriver.compareText(errorMessage, expectedError);
	}
}

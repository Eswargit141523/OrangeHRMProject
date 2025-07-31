package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {
	
	private ActionDriver actiondriver;
	
	//define locator using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIdButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By logo = By.xpath("//img[@src='/web/images/orangehrm-logo.png?v=1721393199309']");
	
	//Initialize the ActionDriver Object by passing web driver instance
	
/*	public HomePage(WebDriver driver) {
		this.actiondriver = new  ActionDriver(driver);
	}  */
	
	public HomePage(WebDriver driver) {
		this.actiondriver =BaseClass.getActionDriver();
	}
	
	//method for perform actions on Homepage
	//method to check Admin tab is visible
	public boolean adminTabVisible() {
		return actiondriver.isDisplayed(adminTab);
	}
	
	public boolean verifyLogo() {
		return actiondriver.isDisplayed(logo);
	}
	
	//method to perform logout operation
	public void logout() {
		actiondriver.click(userIdButton);
		actiondriver.click(logoutButton);
	}
	

}

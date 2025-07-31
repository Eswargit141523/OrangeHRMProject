package com.orangehrm.actiondriver;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		System.out.println("WebDriver instance is created");
	}

	// method to click an element
	public void click(By by) {
		try {
			waitforelementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to click an element:" + e.getMessage());
		}
	}

	// method to compare two texts
	public boolean compareText(By by, String expectedText) {
		try {
			waitforelementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (actualText.equalsIgnoreCase(expectedText)) {
				System.out.println("Texts are matching :" + actualText + "equals" + expectedText);
				return true;
			} else {
				System.out.println("Texts are  not matching :" + actualText + " not equals" + expectedText);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unavle to compare texts:" + e.getMessage());
		}
		return false;
	}

	// method for element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitforelementToBeVisible(by);
			boolean isdisplayed = driver.findElement(by).isDisplayed();
			if (isdisplayed) {
				System.out.println("element is displayed");
				return isdisplayed;
			} else
				return isdisplayed;
		} catch (Exception e) {
			System.out.println("element is not displayed :" + e.getMessage());
			return false;
		}

	}

	// method to enter text
	public void enterText(By by, String value) {

		try {
			waitforelementToBeVisible(by);
			//updating code to avoid duplication
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(text);
			
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
			} catch (Exception e) {

			System.out.println("unable to enter text:" + e.getMessage());
		}
	}
	
	public String getText(By by) {
		try {
			driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get text:"+e.getMessage());
		}
		return null;
	}
    //Method for Scroll to element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0],scrollIntoView(true)", element);
		} catch (Exception e) {
			System.out.println("Unable to locate element"+e.getMessage());
		}
		
	}
	// wait for element to be clickable
	public void waitforelementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("element is not clickable:" + e.getMessage());
		}
	}

	// wait for element to be visible
	public void waitforelementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible" + e.getMessage());

		}
	}
}

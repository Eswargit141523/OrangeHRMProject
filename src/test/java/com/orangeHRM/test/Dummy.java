package com.orangeHRM.test;

import org.jspecify.annotations.Nullable;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class Dummy  extends BaseClass{
     
	@Test
	public void dummyMethod() {
		 
		String title = driver.getTitle();
		System.out.println(title);
		assert title.equalsIgnoreCase("ORANGEHRM"): "Test failed";
		System.out.println("Test Passed");
	}
}

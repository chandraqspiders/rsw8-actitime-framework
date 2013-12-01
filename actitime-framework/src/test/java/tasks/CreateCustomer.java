package tasks;

import libraries.Assertions;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import testbase.BaseClass;

public class CreateCustomer extends BaseClass{
	
	@Test
	public void testCreateCustomer() {		
		String custName = xllib.getExcelData("CreateCustomer",1,0);
		String custDesc = xllib.getExcelData("CreateCustomer",1,1);
		System.out.println("Start create customer");
		plib.navigateToProjectsAndCustomers();		
		driver.findElement(By.cssSelector("input[value='Create New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(custName);
		driver.findElement(By.name("description")).sendKeys(custDesc);
		driver.findElement(By.name("createCustomerSubmit")).click();
		//Verify if customer is created successfully
		String expectedMsg = "Customer \""+custName+"\" has been successfully created.";
		String actualMsg = driver.findElement(By.className("successmsg")).getText();
		Assertions.assertText(expectedMsg, actualMsg, "Customer created successfully");
		System.out.println("End create customer");
	}
}




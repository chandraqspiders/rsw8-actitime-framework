package tasks;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import testbase.BaseClass;

public class DeleteCustomer extends BaseClass{
	@Test
	public void testDeleteCustomer() {
		String custName = xllib.getExcelData("DeleteCustomer", 1, 0);
		System.out.println("Start delete customer");
		plib.navigateToProjectsAndCustomers();
		driver.findElement(By.linkText(custName)).click();
		driver.findElement(By.cssSelector("input[value='Delete This Customer']")).click();
		driver.findElement(By.id("deleteButton")).click();
		System.out.println("End delete customer");	
	}
}

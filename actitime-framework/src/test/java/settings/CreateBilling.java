package settings;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testbase.BaseClass;

public class CreateBilling extends BaseClass{
	@Test
	public void testCreateBilling() {
		String billingName = xllib.getExcelData("CreateBilling", 1, 0);
		System.out.println("Start create billing");		
		plib.navigateToBilling();
		driver.findElement(By.name("addLeaveType")).click();
		driver.findElement(By.id("name")).sendKeys(billingName);
		driver.findElement(By.xpath("//input[contains(@value,'Create Billing Type')]")).click();
		System.out.println("End create billing");
	}
}

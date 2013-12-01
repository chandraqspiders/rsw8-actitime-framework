package settings;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testbase.BaseClass;

public class DeleteBilling extends BaseClass{
	@Test 
	public void testDeleteBilling() {
		String billingName = xllib.getExcelData("DeleteBilling", 1, 0);
		System.out.println("Start delete billing");
		plib.navigateToBilling();
		driver.findElement(By.xpath("//tr[td[a[text()='"+billingName+"']]]/td[6]/a")).click();
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
		System.out.println("End delete billing");
	}
}

package testbase;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import libraries.Assertions;
import libraries.ExcelLibrary;
import libraries.ProjectSpecificLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	public WebDriver driver;
	public ExcelLibrary xllib;
	public ProjectSpecificLibrary plib;
	@BeforeClass
	public void launchApplication(){		
		xllib = new ExcelLibrary();		
		//Get browser and URL from Config sheet in data.xlsx
		String browser = xllib.getExcelData("Config",1, 0);
		String url = xllib.getExcelData("Config",1, 1);
		System.out.println("Launch "+browser+" and open "+url);
		if(browser.equals("firefox")){
			driver = new FirefoxDriver();
		}
		else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "../browserdrivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser.equals("ie")){
			System.setProperty("webdriver.ie.driver", "../browserdrivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);		
		plib = new ProjectSpecificLibrary();
		plib.driver=this.driver;
		
		/*verify if application is opened*/
		String expectedTitle = "actiTIME - Login";
		String actualTitle = driver.getTitle();
		Assertions.assertTitle(expectedTitle, actualTitle, "Application opened successfully");		
	}	
	@BeforeMethod
	public void login(){
		String un = xllib.getExcelData("Login",1,0);
		String pw = xllib.getExcelData("Login",1,1);
		System.out.println("Login to application");
		driver.findElement(By.name("username")).sendKeys(un);
		driver.findElement(By.name("pwd")).sendKeys(pw);
		driver.findElement(By.id("loginButton")).click();	
		//Verify if login is successful
		String expectedText = "Enter Time-Track";
		String actualText = driver.findElement(By.xpath("//table[@class='noprint']/tbody/tr[1]/td[1]")).getText();
		Assertions.assertText(expectedText, actualText, "Login successful");				
	}
	@AfterMethod
	public void logout(){
		System.out.println("Logout from application");
		driver.findElement(By.id("logoutLink")).click();
	}
	@AfterClass
	public void quitApplication(){		
		System.out.println("Quit application");
		driver.quit();
	}
}

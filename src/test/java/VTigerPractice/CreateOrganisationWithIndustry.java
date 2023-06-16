package VTigerPractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;
import com.generic.utilities.PropertyFileUtility;
import com.generic.utilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import object.repository.LoginPage;

public class CreateOrganisationWithIndustry {

	public static void main(String[] args) throws IOException {
		// Read all required data from Property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String BROWSER = putil.readDatafromPropertyFile("browser");
		String URL = putil.readDatafromPropertyFile("url");
		String USERNAME = putil.readDatafromPropertyFile("username");
		String PASSWORD = putil.readDatafromPropertyFile("password");

		// Read the data from excel sheet
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.readDataFromExcel("Organisation", 4, 2);
		String INDUSTRY = eutil.readDataFromExcel("Organisation", 4, 3);

		// WebDriver Interface
		WebDriver d = null;

		// Java Utility
		JavaUtility jutil = new JavaUtility();
		int randomNumber = jutil.getRandomNumber();

		// WebDriver Utility
		WebDriverUtility wutil = new WebDriverUtility();

		// LAUNCH THE BROWSER
		// Step 1: launch the browser - Run Time Polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
			System.out.println(BROWSER + "-- Browser Launch");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
			System.out.println(BROWSER + "-- Browser Launch");

		} else {
			System.out.println("Invalid Browser Name");
		}

		d.get(URL);
		wutil.maximiseWindow(d);
		wutil.waitForPageLoad(d);

		// Step 2: Login to the application
		
		LoginPage loginPage=new LoginPage(d);		
		loginPage.loginToApp(USERNAME, PASSWORD);
		
		/*d.findElement(By.name("user_name")).sendKeys(USERNAME);
		d.findElement(By.name("user_password")).sendKeys(PASSWORD);
		d.findElement(By.id("submitButton")).click();*/		
		
		/*
		 * loginPage.getUsernameTextField().sendKeys(USERNAME);
		 * loginPage.getUserPasswordTextField().sendKeys(PASSWORD);
		 * loginPage.getLoginButton().click(); System.out.println("Login Successful");
		 */

		// Step 3: Click on Organizations link
		d.findElement(By.linkText("Organizations")).click();

		// Step 4: Click on Create Org Look Up Image
		d.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Step 5: Create Organization with mandatory fields
		d.findElement(By.name("accountname")).sendKeys(ORGNAME + randomNumber);

		// Step 6: Choose 'Chemicals' on Industry drop-down
		WebElement INDUSTRYDROPDWON = d.findElement(By.name("industry"));
		wutil.selectByValue(d, INDUSTRYDROPDWON, INDUSTRY);

		// Step 7: Save
		d.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		// Take screenshot
		WebElement ORGINFO = d.findElement(By.xpath("//table[@class='dvtContentSpace']"));
		wutil.takeScreenShot(d, ORGNAME);

		// Step 8: Validate
		String orgHeader = d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println(orgHeader);
			System.out.println("Test Script Passed");
		} else {
			System.out.println("FAIL");
		}
		
		

		// Step 9: Logout of App
		WebElement adImg = d.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act = new Actions(d); act.moveToElement(adImg).perform();
		
		wutil.mouseHoverAction(d, adImg);		
		d.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout is scuccessful");

		d.quit();
	}
}

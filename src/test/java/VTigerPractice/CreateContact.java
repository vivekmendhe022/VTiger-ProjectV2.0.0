package VTigerPractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;
import com.generic.utilities.PropertyFileUtility;
import com.generic.utilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {
	public static void main(String[] args) throws IOException {

		// Create all Objects from generic utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// Java Utility
		int randomNumber = jutil.getRandomNumber();

		WebDriver driver = null;

		/* Read all the required Data */
		String BROWSER = putil.readDatafromPropertyFile("browser");
		String URL = putil.readDatafromPropertyFile("url");
		String USERNAME = putil.readDatafromPropertyFile("username");
		String PASSWORD = putil.readDatafromPropertyFile("password");

		String ORGNAME = eutil.readDataFromExcel("Contact", 4, 3)+ randomNumber;
		String LASTNAME = eutil.readDataFromExcel("Contact", 4, 2);

		// Step 1: launch the browser - Run Time Polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " --- Launched");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " --- Launched");
		} else {
			System.out.println("Invalid Browser Name");
		}

		wutil.maximiseWindow(driver);
		wutil.waitForPageLoad(driver);
		driver.get(URL);

		// Step 2: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		System.out.println("Login successful");

		// Step 3: Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4: Click on Create Org Look Up Image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Step 5: Create Organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		// Step 6: Save Organization
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 8: Validate for Organization
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println(orgHeader);
			System.out.println("Organization Created");
		} else {
			System.out.println("FAIL");
		}

		// Step 9: Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 10: click on create contact look up Image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 11: Create Contact
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step 12: Click on Organization Look Up Image
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();

		// Step 13: Switch the control to child window
		wutil.switchToWindow(driver, "Accounts");

		// Step 14: search for the required Organization Infosys123
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);

		driver.findElement(By.name("search")).click();

		driver.findElement(By.xpath("//a[.='" + ORGNAME + "']")).click();
		// a[.='Infosys123']

		// Step 15: switch the control back to main window
		wutil.switchToWindow(driver, "Contacts");

		// Step 16: Save the contact - No such Element Exception
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 17: Validate for Organization
		String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (ContactHeader.contains(LASTNAME)) {
			System.out.println(ContactHeader);
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		// Step 18: Logout of App
		WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHoverAction(driver, adImg);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout is scuccessful");
		
		// Step 19: Quit Browser
		driver.quit();
	}
}

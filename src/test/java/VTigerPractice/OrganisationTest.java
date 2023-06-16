package VTigerPractice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;

import object.repository.CreateNewOrganizationPage;
import object.repository.HomePage;
import object.repository.OrganizationInfoPage;
import object.repository.OrganizationsPage;

public class OrganisationTest {
	public static void main(String[] args) throws IOException {
		/*
		 * // Read all required data from Property file PropertyFileUtility putil = new
		 * PropertyFileUtility(); String BROWSER =
		 * putil.readDatafromPropertyFile("browser"); String URL =
		 * putil.readDatafromPropertyFile("url"); String USERNAME =
		 * putil.readDatafromPropertyFile("username"); String PASSWORD =
		 * putil.readDatafromPropertyFile("password");
		 * 
		 * 
		 * 
		 * // WebDriver Interface WebDriver d = null;
		 * 
		 * // Java Utility JavaUtility jutil = new JavaUtility(); int randomNumber =
		 * jutil.getRandomNumber();
		 * 
		 * // WebDriver Utility WebDriverUtility wutil = new WebDriverUtility();
		 * 
		 * // LAUNCH THE BROWSER // Step 1: launch the browser - Run Time Polymorphism
		 * if (BROWSER.equalsIgnoreCase("chrome")) {
		 * WebDriverManager.chromedriver().setup(); d = new ChromeDriver();
		 * System.out.println(BROWSER + "-- Browser Launch"); } else if
		 * (BROWSER.equalsIgnoreCase("firefox")) {
		 * WebDriverManager.firefoxdriver().setup(); d = new FirefoxDriver();
		 * System.out.println(BROWSER + "-- Browser Launch");
		 * 
		 * } else { System.out.println("Invalid Browser Name"); }
		 * 
		 * d.get(URL); wutil.maximiseWindow(d); wutil.waitForPageLoad(d);
		 * 
		 * // Step 2: Login to the application
		 * 
		 * LoginPage loginPage=new LoginPage(d); loginPage.loginToApp(USERNAME,
		 * PASSWORD);
		 */

		// WebDriver Interface
		WebDriver d = null;

		// Read the data from excel sheet
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.readDataFromExcel("Organisation", 4, 2);
		String INDUSTRY = eutil.readDataFromExcel("Organisation", 4, 3);

		JavaUtility jutil = new JavaUtility();
		int randomNumber = jutil.getRandomNumber();

		// Step 3: Click on Organizations link
		HomePage hp = new HomePage(d);
		hp.clickOnOrganizationLink();

		// Step 4: Click on Create Org Look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(d);
		orgPage.clickOnCreateOrgLookUpImg();

		// Step 5: Create Organization with mandatory fields
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(d);
		newOrg.createNewOrganization(ORGNAME + randomNumber, INDUSTRY);

		// Step 8: Validate
		OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(d);
		String orgHeader = orgInfoPage.getHeader();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println(orgHeader);
			System.out.println("Test Script Passed");
		} else {
			System.out.println("FAIL");
		}

		/*
		 * // Step 9: Logout of App hp.logoutOfApp(d);
		 * System.out.println("Logout is scuccessful");
		 * 
		 * // Step 10: Close Browser d.quit();
		 */
	}
}

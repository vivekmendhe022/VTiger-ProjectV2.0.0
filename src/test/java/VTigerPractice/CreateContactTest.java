package VTigerPractice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;
import com.generic.utilities.PropertyFileUtility;
import com.generic.utilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import object.repository.ContactInfoPage;
import object.repository.ContactsPage;
import object.repository.CreateNewContactPage;
import object.repository.CreateNewOrganizationPage;
import object.repository.HomePage;
import object.repository.LoginPage;
import object.repository.OrganizationInfoPage;
import object.repository.OrganizationsPage;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		WebDriver d = null;

		// Step1: Create Objects of all generic utilities
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// Create Random Number
		int random = jutil.getRandomNumber();

		// Step2:Read all the data from Property File
		String BROWSER = putil.readDatafromPropertyFile("browser");
		String URL = putil.readDatafromPropertyFile("url");
		String USERNAME = putil.readDatafromPropertyFile("username");
		String PASSWORD = putil.readDatafromPropertyFile("password");

		// Step 3: Read all The Data From Excel File
		String LASTNAME = eutil.readDataFromExcel("Contact", 4, 2);
		String ORGNAME = eutil.readDataFromExcel("Contact", 4, 3) + random;

		// Step 4: Launch The Browser
		if (BROWSER.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser Name");
		}

		// Step 5: Get Main Application URL, Maximise The Window & Give Implicite
		// TimesOut 20 sec.
		d.get(URL);
		wutil.maximiseWindow(d);
		wutil.waitForPageLoad(d);

		// Step 6: Login To the application
		LoginPage login = new LoginPage(d);
		login.loginToApp(USERNAME, PASSWORD);

		// Step 7: Click On Organisation Link
		HomePage hp = new HomePage(d);
		hp.clickOnOrganizationLink();

		// Step 8: Click on create organisation look up img
		OrganizationsPage org = new OrganizationsPage(d);
		org.clickOnCreateOrgLookUpImg();

		// Step 9: Create Orgnisation
		CreateNewOrganizationPage createOrgPage = new CreateNewOrganizationPage(d);
		createOrgPage.createNewOrganization(ORGNAME);

		// Step 10: validate to create Org Or not
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(d);
		String Orgheader = orgInfo.getHeader();
		if (Orgheader.contains(ORGNAME)) {
			System.out.println(Orgheader);
			System.out.println("Test Script Passed");
		} else {
			System.out.println("Test Script Fail");
		}

		// Step 11: Navigate to Contact Link
		hp.clickOnContactsLink();

		// Step 12: click on create contact look up Image
		ContactsPage cPage = new ContactsPage(d);
		cPage.clickOnCreateContactLookUpIcon();

		// Step 13: Create Contact with Orgnisation name
		CreateNewContactPage newContactPage = new CreateNewContactPage(d);
		newContactPage.createNewContact(d, LASTNAME, ORGNAME);

		// Step 14: Validate for Contact
		ContactInfoPage contactInfoPage = new ContactInfoPage(d);
		String contactHeader = contactInfoPage.getContactHeader();
		if (contactHeader.contains(LASTNAME)) {
			System.out.println(contactHeader);
			System.out.println("test Script Passed");
		} else {
			System.out.println("test Script Failed");
		}

		// Step 15: Logout From Application
		hp.logoutOfApp(d);
		System.out.println("Logout Successful");

		// Step 16: Close Browser
		d.quit();
	}
}

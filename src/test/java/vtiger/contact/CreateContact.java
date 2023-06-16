package vtiger.contact;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.utilities.BaseClass;
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

@Listeners(com.generic.utilities.ListenersImplementationUtility.class)
public class CreateContact extends BaseClass {

	@Test(groups = "RegressionSuit")
	public void createContactTest() throws IOException {

		// Create Random Number
		int random = jutil.getRandomNumber();

		// Step 3: Read all The Data From Excel File
		String LASTNAME = eutil.readDataFromExcel("Contact", 4, 2);
		String ORGNAME = eutil.readDataFromExcel("Contact", 4, 3) + random;

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
		Assert.assertTrue(Orgheader.contains(ORGNAME));

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
		Assert.assertTrue(contactHeader.contains(LASTNAME));

	}

}

package vtiger.organisationtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.utilities.BaseClass;
import com.generic.utilities.JavaUtility;

import object.repository.CreateNewOrganizationPage;
import object.repository.HomePage;
import object.repository.OrganizationInfoPage;
import object.repository.OrganizationsPage;

@Listeners(com.generic.utilities.ListenersImplementationUtility.class)
public class CreateOgrWithIndustryTest extends BaseClass {

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		return eutil.readMultipleData("DataProviderOrg");
	}

	@Test(dataProvider = "getData", groups = "SmokeSuit")
	public void createOrganisationWithIndustryTest(String ORGNAME, String INDUSTRY) throws IOException {

		JavaUtility jutil = new JavaUtility();
		int randomNumber = jutil.getRandomNumber();

		// Step 1: Click on Organizations link
		HomePage hp = new HomePage(d);
		hp.clickOnOrganizationLink();
		Reporter.log("Click on Organizations link", true);

		// Step 2: Click on Create Org Look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(d);
		orgPage.clickOnCreateOrgLookUpImg();
		Reporter.log("Click on Create Org Look Up Image", true);

		// Step 3: Create Organization with mandatory fields
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(d);
		newOrg.createNewOrganization(ORGNAME + randomNumber, INDUSTRY);
		Reporter.log("Create Organization with mandatory fields", true);

		// Step 4: Validate
		OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(d);
		String orgHeader = orgInfoPage.getHeader();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		Reporter.log("Organization Created Successful.",true);

	}
}

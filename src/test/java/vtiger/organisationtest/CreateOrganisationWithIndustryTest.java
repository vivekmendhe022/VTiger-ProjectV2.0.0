package vtiger.organisationtest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.utilities.BaseClass;

import object.repository.CreateNewOrganizationPage;
import object.repository.HomePage;
import object.repository.OrganizationInfoPage;
import object.repository.OrganizationsPage;

@Listeners(com.generic.utilities.ListenersImplementationUtility.class)
public class CreateOrganisationWithIndustryTest extends BaseClass {

	@Test
	public void createOrganisationWithIndustryTest() throws IOException {

		int randomNumber = jutil.getRandomNumber();

		// Step 1: Read the data from excel sheet
		String ORGNAME = eutil.readDataFromExcel("Organisation", 4, 2);
		String INDUSTRY = eutil.readDataFromExcel("Organisation", 4, 3);

		// Step 2: Click on Organizations link
		HomePage hp = new HomePage(d);
		hp.clickOnOrganizationLink();
		Reporter.log("Click on Organizations link", true);

		// Step 3: Click on Create Org Look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(d);
		orgPage.clickOnCreateOrgLookUpImg();
		Reporter.log("Click on Create Org Look Up Image", true);

		// Step 4: Create Organization with mandatory fields
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(d);
		newOrg.createNewOrganization(ORGNAME + randomNumber, INDUSTRY);
		Reporter.log("Create Organization with mandatory fields", true);

		// Step 5: Validate
		OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(d);
		String orgHeader = orgInfoPage.getHeader();
		Assert.assertTrue(orgHeader.contains(ORGNAME));

	}
}

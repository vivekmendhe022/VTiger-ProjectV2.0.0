package vtiger.organisationtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.utilities.BaseClass;
import com.generic.utilities.JavaUtility;

import object.repository.CreateNewOrganizationPage;
import object.repository.DeleteInfoPage;
import object.repository.HomePage;
import object.repository.OrganizationInfoPage;
import object.repository.OrganizationsPage;

@Listeners(com.generic.utilities.ListenersImplementationUtility.class)
public class DeleteOrganisationTest extends BaseClass {

	@Test(priority = 1)
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(d);
		JavaUtility jutil = new JavaUtility();
		int randomNumber = jutil.getRandomNumber();

		// Step 1: Read the data from excel sheet
		String ORGNAME = eutil.readDataFromExcel("Organisation", 4, 2);
		String INDUSTRY = eutil.readDataFromExcel("Organisation", 4, 3);

		// Step 2: Click on Organizations link
		hp.clickOnOrganizationLink();

		// Step 3: Click on Create Org Look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(d);
		orgPage.clickOnCreateOrgLookUpImg();

		// Step 4: Create Organization with mandatory fields
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(d);
		newOrg.createNewOrganization(ORGNAME + randomNumber, INDUSTRY);

		// Step 5: Validate
		OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(d);
		String orgHeader = orgInfoPage.getHeader();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
	}

//	@Test(priority = 2, dependsOnMethods = "createOrganizationTest")
	@Test()
	public void deleteOrganisationTest() throws EncryptedDocumentException, IOException {

		OrganizationsPage org = new OrganizationsPage(d);
		HomePage hp = new HomePage(d);

		// Click on Org Module.
		hp.clickOnOrganizationLink();

		// Click on Select All check box.
		org.clickOnSelectAllCheckBox();

		// Click on delete button.
		org.clickOnDeleteBtn();

		// Handle Pop up
		wutil.acceptAlert(d);

		// Validate
		String h = "No Organization Found !";
		DeleteInfoPage dinfo = new DeleteInfoPage(d);
		String deleteHeader = dinfo.deleteHeader();
		Assert.assertTrue(deleteHeader.equalsIgnoreCase(h));
	}
}

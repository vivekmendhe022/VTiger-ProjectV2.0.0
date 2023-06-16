package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.utilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility{

	@FindBy(name = "accountname")
	private WebElement OrganizationNameTextField;
	
	
	@FindBy(name = "industry")
	private WebElement IndustryDropDownList;
	
	@FindBy(xpath = "//input[@value='  Save  ']")
	private WebElement SaveBtn;

	// Initialization:
	public CreateNewOrganizationPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization:
	public WebElement getOrganizationNameTextField() {
		return OrganizationNameTextField;
	}

	public WebElement getIndustryDropDownList() {
		return IndustryDropDownList;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	
	// Business Logic:-
	/**
	 * This method will create new organization
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME) {
		OrganizationNameTextField.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	/**
	 * This method will create new organization with industry drop down list
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrganization(String ORGNAME, String INDUSTRY) {
		OrganizationNameTextField.sendKeys(ORGNAME);
		handleDropbown(IndustryDropDownList, INDUSTRY);
		SaveBtn.click();
	}
	
	
}

package object.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.utilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {

	@FindBy(name = "lastname")
	private WebElement LastNameTextField;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement CreateOrgLookUpImg;

	@FindBy(name = "search_text")
	private WebElement OrgSearchText;

	@FindBy(name = "search")
	private WebElement OrgSearchBtn;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	/*
	 * @FindAll({@FindBy(name = "button"), @FindBy(className =
	 * "//input[type='submit']")}) private WebElement SaveBtn;
	 */

	// Intialisation
	public CreateNewContactPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization
	public WebElement getLastNameTextField() {
		return LastNameTextField;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}

	// Business Logic:-
	/**
	 * This method will create new contact
	 * 
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME) {
		LastNameTextField.sendKeys(LASTNAME);
		SaveBtn.click();
	}

	/**
	 * This method will create new contact with ORG Name
	 * 
	 * @param d
	 * @param LASTNAME
	 * @param ORGNAME
	 */
	public void createNewContact(WebDriver d, String LASTNAME, String ORGNAME) {
		LastNameTextField.sendKeys(LASTNAME);
		CreateOrgLookUpImg.click();
		switchToWindow(d, "Accounts");
		OrgSearchText.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		d.findElement(By.xpath("//a[.='" + ORGNAME + "']")).click();
		switchToWindow(d, "Contacts");
		SaveBtn.click();
	}
}

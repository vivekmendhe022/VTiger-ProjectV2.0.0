package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateContactLookUpIcon;

	// Intialization
	public ContactsPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization
	public WebElement getCreateContactLookUpIcon() {
		return CreateContactLookUpIcon;
	}

	// Business Logic
	/**
	 * This method will click on CreateContactLookUpIcon
	 */
	public void clickOnCreateContactLookUpIcon() {
		CreateContactLookUpIcon.click();
	}
}

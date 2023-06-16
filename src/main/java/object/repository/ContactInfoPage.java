package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement ContactHeaderText;

	// Initialization
	public ContactInfoPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization
	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}

	// Business Logic:-
	/**
	 * This method will return Contact Last Name
	 * @return
	 */
	public String getContactHeader() {
		return ContactHeaderText.getText();
	}
}

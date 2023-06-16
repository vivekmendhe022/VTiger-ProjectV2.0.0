package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement ORGHeaderText;

	// Initialization:
	public OrganizationInfoPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization
	public WebElement getORGHeaderText() {
		return ORGHeaderText;
	}
	
	// Business Logic
	/**
	 * This method will return Organization Name 
	 * @return
	 */
	public String getHeader() {
		return ORGHeaderText.getText();
	}
	
}

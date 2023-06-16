package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.utilities.WebDriverUtility;

public class CreateNewVendorPage extends WebDriverUtility {

	@FindBy(name = "vendorname")
	private WebElement VendorName;

	@FindBy(name = "glacct")
	private WebElement GLAccount;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	public CreateNewVendorPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public WebElement getVendorName() {
		return VendorName;
	}

	public WebElement getGLAccount() {
		return GLAccount;
	}

	public void createNewVendors(WebDriver d, String VENDORS, String GLName) {
		VendorName.sendKeys(VENDORS);
		selectByValue(d, GLAccount, GLName);
		SaveBtn.click();
	}

}

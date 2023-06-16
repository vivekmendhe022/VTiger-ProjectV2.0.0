package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsInfoPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement vendorHeaderText;

	public VendorsInfoPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public WebElement getVendorHeaderText() {
		return vendorHeaderText;
	}

	public String getvendorHeader() {
		return vendorHeaderText.getText();
	}
}

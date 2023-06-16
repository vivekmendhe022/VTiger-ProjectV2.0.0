package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage {

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateVendorsLookUpImg;

	public VendorsPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public WebElement getCreateVendorsLookUpImg() {
		return CreateVendorsLookUpImg;
	}

	public void clickOnCreateVendorsLookUpImg() {
		CreateVendorsLookUpImg.click();
	}
}

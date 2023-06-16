package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement CreateOrgLookUpImg;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement DeleteBtn;

	@FindBy(xpath = "//input[@name='selectall']")
	private WebElement SelectAllCheckBox;

	// Initialization:
	public OrganizationsPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization:
	public WebElement getCreateOrgLookUpImg() {
		return CreateOrgLookUpImg;
	}

	public WebElement getDeleteBtn() {
		return DeleteBtn;
	}

	public WebElement getSelectAllCheckBox() {
		return SelectAllCheckBox;
	}

	// Bussiness Logic:-
	/**
	 * This method will click on CreateOrgLookUpImg
	 */
	public void clickOnCreateOrgLookUpImg() {
		CreateOrgLookUpImg.click();
	}

	/**
	 * This method will click on DeleteBtn
	 */
	public void clickOnDeleteBtn() {
		DeleteBtn.click();
	}
	
	/**
	 * This method will click on SelectAllCheckBox
	 */
	public void clickOnSelectAllCheckBox() {
		SelectAllCheckBox.click();
	}

}

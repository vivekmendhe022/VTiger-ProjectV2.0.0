package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.utilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsLink;

	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;

	@FindBy(linkText = "Opportunities")
	private WebElement OpportunitiesLink;

	@FindBy(linkText = "Products")
	private WebElement ProductsLink;

	@FindBy(linkText = "Vendors")
	private WebElement VendorsLink;

	@FindBy(xpath = "//td[@class='tabUnSelected']/following-sibling::td[20]/a[.='More']")
	private WebElement MoreDropDownMenu;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;

	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;

	// Initialization
	public HomePage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilization: Getters
	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}

	public WebElement getAdministratorImg() {
		return AdministratorImg;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}

	public WebElement getProductsLink() {
		return ProductsLink;
	}

	// Bussiness Libraries:-
	/**
	 * This method will click on organizations link
	 */
	public void clickOnOrganizationLink() {
		OrganizationsLink.click();
	}

	/**
	 * This method will click on contacts link
	 */
	public void clickOnContactsLink() {
		ContactsLink.click();
	}

	/**
	 * This method will click on OpportunitiesLink
	 */
	public void clickOnOpportunitiesLink() {
		OpportunitiesLink.click();
	}

	/**
	 * This method will click on ProductLink
	 */
	public void clickOnProduct() {
		ProductsLink.click();
	}

	public void clickOnVendor(WebDriver d) {
		mouseHoverAction(d, MoreDropDownMenu);
		VendorsLink.click();
	}

	/**
	 * This method will logout from application
	 * 
	 * @param d
	 */
	public void logoutOfApp(WebDriver d) {
		mouseHoverAction(d, AdministratorImg);
		SignOutLink.click();
	}

}

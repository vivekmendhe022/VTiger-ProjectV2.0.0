package object.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.utilities.WebDriverUtility;

public class CreateNewProductPage extends WebDriverUtility {

	@FindBy(name = "productname")
	private WebElement ProductNameTextField;

	@FindBy(xpath = "//input[@name='vendor_name']/following-sibling::img[@alt='Select']")
	private WebElement VendorNameLookUpImg;

	@FindBy(name = "glacct")
	private WebElement GLAccountDropDownList;

	@FindBy(name = "search_text")
	private WebElement SearchTextField;

	@FindBy(name = "search")
	private WebElement SearchNowBtn;

	@FindBy(xpath  = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	// Initialisation
	public CreateNewProductPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilisation
	public WebElement getProductNameTextField() {
		return ProductNameTextField;
	}

	public WebElement getVendorNameLookUpImg() {
		return VendorNameLookUpImg;
	}

	public WebElement getGLAccountDropDownList() {
		return GLAccountDropDownList;
	}

	public WebElement getSearchTextField() {
		return SearchTextField;
	}

	public WebElement getSearchNowBtn() {
		return SearchNowBtn;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	public void creatingNewProduct(WebDriver d, String PRODUCT) {
		ProductNameTextField.sendKeys(PRODUCT);
		SaveBtn.click();
	}

	// Business Logic
	public void creatingNewProduct(WebDriver d, String PRODUCT, String VENDORS) {
		ProductNameTextField.sendKeys(PRODUCT);
		VendorNameLookUpImg.click();
		switchToWindow(d, "Vendors");
		SearchTextField.sendKeys(VENDORS);
		SearchNowBtn.click();
		d.findElement(By.xpath("//a[.='" + VENDORS + "']")).click();
		switchToWindow(d, "Products");
		selectByValue(d, GLAccountDropDownList, "303-Interest-Income");
		SaveBtn.click();
	}

}

package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateProductLookUpImg;

	// Intialisation
	public ProductPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilisation
	public WebElement getCreateProductLookUpImg() {
		return CreateProductLookUpImg;
	}

	// Business Logic
	public void clickOnCreateProductLookUpImg() {
		CreateProductLookUpImg.click();
	}
}

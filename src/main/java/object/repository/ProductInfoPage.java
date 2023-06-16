package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement productHeader;

	// Initialisation
	public ProductInfoPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Utilisation
	public WebElement getProductHeader() {
		return productHeader;
	}

	// Business Logic
	public void productHeader() {
		productHeader.getText();
	}
}

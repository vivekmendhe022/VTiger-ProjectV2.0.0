package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteInfoPage {

	@FindBy(xpath = "//span[@class='genHeaderSmall']")
	private WebElement genHeaderSmall;

	public DeleteInfoPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public WebElement getGenHeaderSmall() {
		return genHeaderSmall;
	}

	public String deleteHeader() {
		return genHeaderSmall.getText();
	}
}

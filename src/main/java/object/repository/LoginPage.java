package object.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author Vivekanand M
 *
 */
public class LoginPage {
	// Rule 1: Create A Class

	// Rule 2: Identify the element using annotations

	@FindBy(name = "user_name")
	private WebElement usernameTextField;

	@FindBy(name = "user_password")
	private WebElement userPasswordTextField;

	@FindAll({ @FindBy(id = "submitButton"), @FindBy(xpath = "//input[type='submit']") })
	private WebElement loginButton;

	// Rule 3: Create a constructor to initialize
	public LoginPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	// Rule 4: Provide Getters to access elements
	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getUserPasswordTextField() {
		return userPasswordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	// Business Libraries:- Project Specific Generic Methods or Utilities
	/**
	 * This method login to application
	 * 
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD) {
		usernameTextField.sendKeys(USERNAME);
		userPasswordTextField.sendKeys(PASSWORD);
		loginButton.click();
	}

}

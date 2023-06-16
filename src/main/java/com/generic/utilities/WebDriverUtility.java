package com.generic.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * 
	 * @param d
	 */
	public void maximiseWindow(WebDriver d) {
		d.manage().window().maximize();
	}

	/**
	 * This method will minimize the window
	 * 
	 * @param d
	 */
	public void minimiseWindow(WebDriver d) {
		d.manage().window().minimize();
	}

	/**
	 * This method wait for page to load for 20 seconds
	 * 
	 * @param d
	 */
	public void waitForPageLoad(WebDriver d) {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	/**
	 * This method will wait for 20 seconds for a element to be visible
	 * 
	 * @param d
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver d, WebElement element) {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void handleDropbown(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * This method will handle drop down by value
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropbown(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	/**
	 * This method will handle drop down by visible text
	 * 
	 * @param visibleText
	 * @param element
	 */
	public void handleDropbown(String visibleText, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	/**
	 * This method will perform mouse action hover particular element
	 * 
	 * @param d
	 * @param element
	 */
	public void mouseHoverAction(WebDriver d, WebElement element) {
		Actions actions = new Actions(d);
		actions.moveToElement(element).perform();
		;
	}

	/**
	 * This method will perform right click anywhere on web page.
	 * 
	 * @param d
	 */
	public void rightClickAction(WebDriver d) {
		Actions actions = new Actions(d);
		actions.contextClick().perform();
	}

	/**
	 * This method will perform right click particular element.
	 * 
	 * @param d
	 * @param element
	 */
	public void rightClickAction(WebDriver d, WebElement element) {
		Actions actions = new Actions(d);
		actions.contextClick(element).perform();
	}

	/**
	 * This method will perform right click anywhere on web page.
	 * 
	 * @param d
	 * @param element
	 */
	public void doubbleClickAction(WebDriver d) {
		Actions actions = new Actions(d);
		actions.doubleClick().perform();
	}

	/**
	 * This method will perform right click particular element.
	 * 
	 * @param d
	 * @param element
	 */
	public void doubbleClickAction(WebDriver d, WebElement element) {
		Actions actions = new Actions(d);
		actions.doubleClick(element).perform();
	}

	/**
	 * This method will perform dragAndDrop action using source to target.
	 * 
	 * @param d
	 * @param src
	 * @param target
	 */
	public void dragAndDropAction(WebDriver d, WebElement src, WebElement target) {
		Actions actions = new Actions(d);
		actions.dragAndDrop(src, target).perform();
	}

	/**
	 * This method will perform dragAndDrop using source to OffsetX, OffsetY.
	 * 
	 * @param d
	 * @param src
	 * @param x
	 * @param y
	 */
	public void dragAndDropAction(WebDriver d, WebElement src, int x, int y) {
		Actions actions = new Actions(d);
		actions.dragAndDropBy(src, x, y).perform();
	}

	/**
	 * This method will handle frame by using index
	 * 
	 * @param d
	 * @param index
	 */
	public void switchToFrame(WebDriver d, int index) {
		d.switchTo().frame(0);
	}

	/**
	 * This method will handle frame by using nameOrId
	 * 
	 * @param d
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver d, WebElement nameOrId) {
		d.switchTo().frame(nameOrId);
	}

	/**
	 * This method will handle frame by using element
	 * 
	 * @param element
	 * @param d
	 */
	public void switchToFrame(WebElement element, WebDriver d) {
		d.switchTo().frame(element);
	}

	/**
	 * This method will handle frame by using element
	 * 
	 * @param d
	 */
	public void switchToParentFrame(WebDriver d) {
		d.switchTo().parentFrame();
	}

	/**
	 * This method will switch to parent frame using parentFrame.
	 * @param d
	 */
	public void switchToDefaultFrame(WebDriver d) {
		d.switchTo().parentFrame();
	}

	/**
	 * This method will accept the alert pop up
	 * 
	 * @param d
	 */
	public void acceptAlert(WebDriver d) {
		d.switchTo().alert().accept();
	}

	/**
	 * This method will dismiss the alert pop up
	 * 
	 * @param d
	 */
	public void dismissAlert(WebDriver d) {
		d.switchTo().alert().dismiss();
		;
	}

	/**
	 * This method will get the text from pop up
	 * 
	 * @param d
	 * @return
	 */
	public String getAlertText(WebDriver d) {
		return d.switchTo().alert().getText();
	}

	/**
	 * This method will use for scrollBy method to the web page
	 * 
	 * @param d
	 * @param x
	 * @param y
	 */
	public void scrollBy(WebDriver d, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeAsyncScript("scrollBy(x,y)");
	}

	/**
	 * This method will use for scrollTo method to the web page
	 * 
	 * @param d
	 * @param x
	 * @param y
	 */
	public void scrollTo(WebDriver d, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeAsyncScript("scrollTo(x,y)");
	}

	/**
	 * This method will take screen shot & return absolute path
	 * 
	 * @param d
	 * @param scrname
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver d, String scrname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) d;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\screenshot\\" + scrname + ".png");
		Files.copy(src, dst);
		return dst.getAbsolutePath();
	}

	/**
	 * This method will switch the windows based on window title
	 * 
	 * @param driver
	 * @param partialwindowTitle
	 */
	public void switchToWindow(WebDriver d, String partialwindowTitle) {
		// Step 1: Capture all the window IDs
		Set<String> windowIDS = d.getWindowHandles();

		// Step 2: Navigate to each window
		for (String windowID : windowIDS) {

			// Step 3: capture the title of title of each window
			String actualTitle = d.switchTo().window(windowID).getTitle();

			// Step 4: compare the title
			if (actualTitle.contains(partialwindowTitle)) {
				break;
			}
		}
	}

	public void selectByIndex(WebDriver d, WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectByValue(WebDriver d, WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void selectByVisibleText(WebDriver d, String VisibleText, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(VisibleText);
	}

}

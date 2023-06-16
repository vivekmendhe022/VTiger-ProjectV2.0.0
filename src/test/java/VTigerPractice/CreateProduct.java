package VTigerPractice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;
import com.generic.utilities.PropertyFileUtility;
import com.generic.utilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import object.repository.CreateNewProductPage;
import object.repository.CreateNewVendorPage;
import object.repository.HomePage;
import object.repository.LoginPage;
import object.repository.ProductInfoPage;
import object.repository.ProductPage;
import object.repository.VendorsInfoPage;
import object.repository.VendorsPage;

public class CreateProduct {
	public static void main(String[] args) throws IOException {
		WebDriver d = null;

		// Step1: Create Objects of all generic utilities
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// Create Random Number
		int random = jutil.getRandomNumber();

		// Step2:Read all the data from Property File
		String BROWSER = putil.readDatafromPropertyFile("browser");
		String URL = putil.readDatafromPropertyFile("url");
		String USERNAME = putil.readDatafromPropertyFile("username");
		String PASSWORD = putil.readDatafromPropertyFile("password");

		// Step 3: Read all The Data From Excel File
		String VENDORS = eutil.readDataFromExcel("Product", 1, 2);
		String PRODUCT = eutil.readDataFromExcel("Product", 4, 2);
		String GLAccount = eutil.readDataFromExcel("Product", 4, 3);

		// Step 4: Launch The Browser
		if (BROWSER.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser Name");
		}

		// Step 5: Get Main Application URL, Maximise The Window & Give Implicite
		// TimesOut 20 sec.
		d.get(URL);
		wutil.maximiseWindow(d);
		wutil.waitForPageLoad(d);

		// Step 6: Login To the application
		LoginPage login = new LoginPage(d);
		login.loginToApp(USERNAME, PASSWORD);

		// Click on Vendors
		HomePage hp = new HomePage(d);
		hp.clickOnVendor(d);

		// Click on create vendors look up img
		VendorsPage vendorsPage = new VendorsPage(d);
		vendorsPage.clickOnCreateVendorsLookUpImg();

		// Create New Vendors
		CreateNewVendorPage newVendorPage = new CreateNewVendorPage(d);
		newVendorPage.createNewVendors(d, VENDORS, GLAccount);

		// Verify
		VendorsInfoPage vendorsInfoPage = new VendorsInfoPage(d);
		String vendorHeader = vendorsInfoPage.getvendorHeader();
		if (vendorHeader.contains(VENDORS)) {
			System.out.println(vendorHeader);
			System.out.println("Script Pass");
		} else {
			System.out.println("Script Fail");
		}

		// STEP 7: Click on Product Link
		hp.clickOnProduct();

		// Step 8: Click On Create Product Look Up Img
		ProductPage productPage = new ProductPage(d);
		productPage.clickOnCreateProductLookUpImg();

		// Step 9: Insert Data into Product Name Text Field
		CreateNewProductPage newProductPage = new CreateNewProductPage(d);
//		newProductPage.creatingNewProduct(d, PRODUCT);
		newProductPage.creatingNewProduct(d, PRODUCT, VENDORS);

		// Step 10: Validate
		ProductInfoPage productInfoPage = new ProductInfoPage(d);
		String productHeader = productInfoPage.getProductHeader().getText();
		if (productHeader.contains(PRODUCT)) {
			System.out.println(productHeader);
			System.out.println("Test Script Pass");
		} else {
			System.out.println("Test Script Fail");
		}

		// Logout
		hp.logoutOfApp(d);

		// Quit
		d.quit();

	}
}

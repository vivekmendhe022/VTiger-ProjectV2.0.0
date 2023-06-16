package vtiger.product;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generic.utilities.BaseClass;
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

@Listeners(com.generic.utilities.ListenersImplementationUtility.class)
public class CreateProductTest extends BaseClass {

	@Test
	public void createProductTest() throws IOException {

		// Create Random Number
		int r = jutil.getRandomNumber();

		// Step 3: Read all The Data From Excel File
		String VENDORS = eutil.readDataFromExcel("Product", 1, 2)+r;
		String PRODUCT = eutil.readDataFromExcel("Product", 4, 2)+r;
		String GLAccount = eutil.readDataFromExcel("Product", 4, 3);

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
		Assert.assertTrue(vendorHeader.contains(VENDORS));

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
		Assert.assertTrue(productHeader.contains(PRODUCT));

	}
}

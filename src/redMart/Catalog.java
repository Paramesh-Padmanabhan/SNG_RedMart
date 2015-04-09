package redMart;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Catalog extends Home {
	public static int cart_quantity = 0;
	public static String prod_desc = "";
	public static String prod_price = "";
	
	@SuppressWarnings("deprecation")
	@Test (dependsOnMethods = "Log_In")
	public void Catalog_Without_Search() throws IOException, InterruptedException {
		
		//waiting until the text "My Cart" is displayed post logged in
		Thread.sleep(10000);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object.getProperty("CatalogPage.Signed_User_Home_Page"))));
		
		//Asserting the quantity being displayed in the "Cart" link in the header section
		assert wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(Object.getProperty("CatalogPage.Signed_User_Home_Page")), "My Cart"));
		String zero_quantity = "My Cart";
		assert driver.findElement(By.xpath(Object.getProperty("CatalogPage.Signed_User_Home_Page"))).getText().equals(zero_quantity);
		driver.findElement(By.id(Object.getProperty("HomePage.Logo"))).click();
	}
		
		
	//This function will be used whenever there is a need to verify the number of items or quantity of the cart
	@Test (dependsOnMethods = "Add_Item")
	private void Cart_Quantity() throws InterruptedException {
		//Performing Mouse Hover action
		Actions action = new Actions(driver);
		WebElement Cart = driver.findElement(By.xpath(Object.getProperty("CatalogPage.Cart_Post_Item_Addition")));
		action.moveToElement(Cart).build().perform();
		Thread.sleep(5000);
		assert Integer.parseInt(driver.findElement(By.xpath(Object.getProperty("CatalogPage.Quantity"))).getText()) == cart_quantity;
	}
	
	
	//This function will be used whenever there is a need to Add items to the cart
	@Test (dependsOnMethods = "Catalog_Without_Search")
	public void Add_Item() throws InterruptedException {
		driver.findElement(By.xpath(Object.getProperty("CatalogPage.Item_Description"))).click();
		prod_desc = driver.findElement(By.xpath(Object.getProperty("CatalogPage.Product_Description"))).getText();
		prod_price = driver.findElement(By.xpath(Object.getProperty("CatalogPage.Product_Price"))).getText();
		System.out.println(prod_price);
		System.out.println(prod_desc);
		driver.findElement(By.xpath(Object.getProperty("CatalogPage.Add_To_Cart_Button"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object.getProperty("CatalogPage.Cart_Quantity"))));
		cart_quantity++;
		System.out.println(cart_quantity);
		Cart_Quantity();
	}
}
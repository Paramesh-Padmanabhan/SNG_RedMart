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
	@SuppressWarnings("deprecation")
	@Test (dependsOnMethods = { "Log_In" })
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
		
		Add_Item();
		
		//Mouse Hover "Cart" link function
		Cart_Quantity();
	}
		
		
	//This function will be called whenever there is a need to verify the number of items or quantity of the cart
	private void Cart_Quantity() throws InterruptedException {
		//Performing Mouse Hover action
		Actions action = new Actions(driver);
		WebElement Cart = driver.findElement(By.xpath(Object.getProperty("CatalogPage.Cart_Post_Item_Addition")));
		action.moveToElement(Cart).build().perform();
		Thread.sleep(2000);
		assert Integer.parseInt(driver.findElement(By.xpath(Object.getProperty("CatalogPage.Quantity"))).getText()) == cart_quantity;
	}

	//This function will be called whenever there is a need to Add items to the cart
	public void Add_Item() throws InterruptedException {
		driver.findElement(By.xpath(Object.getProperty("CatalogPage.Item_Description"))).click();
		driver.findElement(By.xpath(Object.getProperty("CatalogPage.Add_To_Cart_Button"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object.getProperty("CatalogPage.Cart_Quantity"))));
		cart_quantity++;
		System.out.println(cart_quantity);
		Cart_Quantity();
		Thread.sleep(5000);
	}
}
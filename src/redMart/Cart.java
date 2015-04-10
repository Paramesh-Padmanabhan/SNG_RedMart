package redMart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

@Test (dependsOnMethods = "redMart.Catalog.Cart_Quantity")
public class Cart extends Catalog{
	@Test
	public void View_Cart_Page() {
		driver.findElement(By.id(Object.getProperty("HomePage.Cart_Link"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Object.getProperty("CartPage.Home_Screen"))));
	}


	@Test (dependsOnMethods = "View_Cart_Page")
	public void Cart_Page_Validations() throws InterruptedException {
		Thread.sleep(3000);
		assert driver.findElement(By.xpath(Object.getProperty("CartPage.Product_Description"))).getText().equals(prod_desc);
		assert Integer.parseInt(driver.findElement(By.xpath(Object.getProperty("CartPage.Quantity"))).getText()) == cart_quantity;
		assert driver.findElement(By.xpath(Object.getProperty("CartPage.Price"))).getText().equals(prod_price);
	}
}
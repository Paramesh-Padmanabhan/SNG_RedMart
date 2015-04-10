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
	
	@Test (dependsOnMethods = "Cart_Page_Validations")
	public void Cart_Amount_Validations() throws InterruptedException {
		/*System.out.println("Price in Cart : "+to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Price"))).getText()));
		System.out.println("Price in Total : "+to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Actual_Product_Amount"))).getText()));
		System.out.println("Price in Total inclusive of Shipping : "+to_number(driver.findElement(By.xpath(Object.getProperty
				("CartPage.Total_Amount_With_Shipping"))).getText()));*/
		assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Price"))).getText()) == 
				(to_number(prod_price) * to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Quantity"))).getText()));
		assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Actual_Product_Amount"))).getText()) == to_number(prod_price);
		if (to_number(prod_price) < 75)
			assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Total_Amount_With_Shipping"))).getText()) == (to_number(prod_price)+7.00);
		else
			assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Total_Amount_With_Shipping"))).getText()) == to_number(prod_price);
			
	}


	public float to_number(String text) {
		price_in_amount = Float.parseFloat((text.substring(1)));
		return price_in_amount;
	}
}
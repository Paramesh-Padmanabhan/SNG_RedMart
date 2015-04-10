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
		//assert driver.findElement(By.xpath(Object.getProperty("CartPage.Product_Description").substring(0,30))).getText().equals((prod_desc).substring(0, 30));
		assert Integer.parseInt(driver.findElement(By.xpath(Object.getProperty("CartPage.Quantity"))).getText()) == cart_quantity;
		assert driver.findElement(By.xpath(Object.getProperty("CartPage.Price"))).getText().equals(prod_price);
	}
	
	@Test (dependsOnMethods = "Cart_Page_Validations")
	public void Cart_Amount_Validations() throws InterruptedException {
		/*
		 * I'm trying to validate the cart page values by modifying the string to float.
		 * Also I have used the free shipment logic wherein the shipping charges will be levied off if the cart price = $75.00
		 * Challenges Faced : Earlier I didn't used the condition "text.length() > 1". And hence when quantity was passed as an argument, it threw 
		 * 					empty string error
		 */
		
		Thread.sleep(4000);
		assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Price"))).getText()) == 
				(to_number(prod_price) * to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Quantity"))).getText()));
		assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Actual_Product_Amount"))).getText()) == to_number(prod_price);
		if (to_number(prod_price) < 75)
			assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Total_Amount_With_Shipping"))).getText()) == (to_number(prod_price)+7.00);
		else
			assert to_number(driver.findElement(By.xpath(Object.getProperty("CartPage.Total_Amount_With_Shipping"))).getText()) == to_number(prod_price);
			
	}


	public float to_number(String text) {
		if (text.length() > 1)
		{
			price_in_amount = Float.valueOf((text.substring(1)));
			return price_in_amount;
		}
		else 
		{
			price_in_amount = Float.valueOf((text));
			return price_in_amount;
		}
	}
}
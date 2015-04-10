package redMart;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Home {
	float price_in_amount = 0;
	public static WebDriver driver;
	public static WebDriverWait wait;	
	public static Properties Object = new Properties();
	
	@BeforeTest()
	public void Load_Home_Page_Objects() throws IOException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Paramesh_p\\Desktop\\Paramesh\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://redmart.com");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod()
	public void Object_Read() throws IOException {
		FileInputStream HomeObjRep = new FileInputStream(System.getProperty("user.dir")+"\\src\\redMart\\ObjectRepo.properties");
		//Object = new Properties();
		Object.load(HomeObjRep);
	}
	
	@Test
	public void Log_In() throws IOException, InterruptedException{

			wait = new WebDriverWait(driver, 15);
			try {
				//waiting until the "Huge Product Range" div is getting loaded
				
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Object.getProperty("HomePage.Huge_Product_Range_Image"))));
				}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			//Trying to login by clicking the link "Log in"
			driver.findElement(By.id(Object.getProperty("HomePage.LogIn"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBarInner")));
			
			//clearing off the contents as it is creating problems when keying in the email. Often only partial text is being entered
			driver.findElement(By.xpath(Object.getProperty("HomePage.SignIn_Email"))).clear();
			Thread.sleep(5000);
			
			driver.findElement(By.xpath(Object.getProperty("HomePage.SignIn_Email"))).sendKeys("parameshprof@gmail.com");
			driver.findElement(By.xpath(Object.getProperty("HomePage.SignIn_Password"))).sendKeys("test123");
			driver.findElement(By.xpath(Object.getProperty("HomePage.SignIn_Button"))).click();
		}
	
	/*@AfterTest
	public void Close_Browser() {
		//driver.close();
		driver.quit();
	}*/
}
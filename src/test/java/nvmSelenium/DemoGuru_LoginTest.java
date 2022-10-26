package nvmSelenium;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoGuru_LoginTest {
	
	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		//Login Verification Test using FireFox Browser	
		
		//Variable Declaration
		//String uname = "mngr444901";
		//String pass = "etAmUra";
		//String url = "https://www.demo.guru99.com/V4/";
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		try {
			driver.get(util.BASE_URL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			
			driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(util.USER_NAME);
			
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(util.PASSWD);
			
			driver.findElement(By.xpath("//input[@value='LOGIN']")).click();	
			
			
			String actualtitle = driver.getTitle();
			Assert.assertEquals(util.EXPECT_TITLE, actualtitle);
			
		}
		finally{
			System.out.print("Test Case Successfull");
			driver.quit();
		}
		
	}

}

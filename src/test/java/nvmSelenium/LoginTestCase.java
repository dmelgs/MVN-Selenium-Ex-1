package nvmSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestCase {

	public static String browser = "chrome"; // browser configuration
	public static WebDriver driver;

	public static void main(String[] args) {
		
		String email = "standard_user";
		String pass = "secret_sauce";
		String url = "https://www.saucedemo.com/";
		
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
		}
		try {
			driver.get(url);
			driver.manage().window().maximize();
			
			WebElement inputEmail = driver.findElement(By.xpath("//input[@placeholder='Username']"));
			inputEmail.sendKeys(email);
			
			WebElement inputPass = driver.findElement(By.xpath("//input[@name='password']"));
			inputPass.sendKeys(pass);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
		} finally {
			System.out.print("Success");	
			driver.quit();
		}

	}

}

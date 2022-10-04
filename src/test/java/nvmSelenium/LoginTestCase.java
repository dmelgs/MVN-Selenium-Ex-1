package nvmSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestCase {

	public static String browser = "chrome"; // browser config
	public static WebDriver driver;

	public static void main(String[] args) {
		String email = "melgo.agency@gmail.com";
		String pass = "123456";
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
		}
		try {
			driver.get("https://capstone-fantasea.web.app/?#/login");
			driver.manage().window().maximize();
			
			WebElement inputEmail = driver.findElement(By.xpath("//input[@placeholder='Email']"));
			inputEmail.sendKeys(email);
			
			WebElement inputPass = driver.findElement(By.xpath("//input[@placeholder='Password']"));
			inputPass.sendKeys(pass);
			
			driver.findElement(By.xpath("//button[text()='Login']")).click();
			
		} finally {
			System.out.print("Success");	
			driver.quit();
		}

	}

}

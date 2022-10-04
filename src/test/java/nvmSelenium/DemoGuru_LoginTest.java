package nvmSelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoGuru_LoginTest {
	
	public static WebDriver driver;
	public static void main(String[] args) {
		//Login Verification Test using FireFox Browser
		
		//Variable Declaration
		String uname = "mngr444901";
		String pass = "etAmUra";
		String url = "https://www.demo.guru99.com/V4/";
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		try {
			driver.get(url);
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(uname);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
			driver.findElement(By.xpath("//input[@value='LOGIN']")).click();	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		}
		finally{
			System.out.print("Login Succesfull");
			driver.quit();
		}
		
	}

}

package nvmSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestCase {

	public static String browser = "edge";
	public static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
		}
		try {
			driver.get("https://www.google.com");
			driver.manage().window().maximize();
			WebElement searchInput = driver.findElement(By.name("q"));
			searchInput.sendKeys("selenium", Keys.ENTER);
			// Clears the entered text
			String title = driver.getTitle();
			System.out.print(title);
		} finally {
			driver.quit();
		}

	}

}

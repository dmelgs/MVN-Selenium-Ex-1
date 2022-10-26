package nvmSelenium;

import java.io.*;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizeTestingExcel {
	private XSSFSheet ExcelWSheet;
	private XSSFWorkbook ExcelWBook;

	// create constructor to connect to the excel
	public ParameterizeTestingExcel(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to set the row count of the excel.
	public int excel_get_rows() throws Exception {

		try {
			return ExcelWSheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			throw (e);
		}
	}

	// create read data in excel method
	// This method to get the data and get the value as strings.
	public String getCellDataasstring(int RowNum, int ColNum) throws Exception {

		try {
			String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "Errors in Getting Cell Data";
		}
	}

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws Exception {
		ParameterizeTestingExcel parExc = new ParameterizeTestingExcel(
				"C:\\Users\\acer\\OneDrive - United Electronics Co. (EXTRA)\\Desktop\\UserCredentials.xlsx", "Sheet1");
		System.out.println("The Row count is " + parExc.excel_get_rows());
		WebDriverManager.firefoxdriver().setup();

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		boolean isGood = false;
		int rows = parExc.excel_get_rows();

		for (int i = 1; i < rows; i++) {
			driver.get(util.BASE_URL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();

			WebElement user = driver.findElement(By.xpath("//input[@name='uid']"));
			user.sendKeys(parExc.getCellDataasstring(i, 0));
			WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
			pass.sendKeys(parExc.getCellDataasstring(i, 1));
			driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
			try {
				Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				if (actualBoxtitle.contains(util.EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
					System.out.println("Test case SS[" + i + "]: Passed W/ Incorrect Credentials");
				} else {
					System.out.println("Test case SS[" + i + "]: Failed");
				}

			} catch (NoAlertPresentException Ex) {
				String actualTitle = driver.getTitle();
				// On Successful login compare Actual Page Title with Expected Title
				if (actualTitle.contains(util.EXPECT_TITLE)) {
					System.out.println("Test case SS[" + i + "]: Passed W/ Correct Credentials");
				} else {
					System.out.println("Test case SS[" + i + "]: Failed");
				}
			}
			
		}
		driver.close();

	}

}

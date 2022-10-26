package nvmSelenium;

import java.io.*;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
			System.out.println("The value of CellData " + CellData);
			return CellData;
		} catch (Exception e) {
			return "Errors in Getting Cell Data";
		}
	}

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		ParameterizeTestingExcel parExc = new ParameterizeTestingExcel(
				"C:\\Users\\acer\\OneDrive - United Electronics Co. (EXTRA)\\Desktop\\UserCredentials.xlsx", "Sheet1");
		System.out.println("The Row count is " + parExc.excel_get_rows());
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		int rows = parExc.excel_get_rows();

		for (int i = 1; i < rows; i++) {
			
			try {
				driver.get(util.BASE_URL);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.manage().window().maximize();

				driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(parExc.getCellDataasstring(i, 0));

				driver.findElement(By.xpath("//input[@name='password']")).sendKeys(parExc.getCellDataasstring(i, 1));

				driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
				
				String actualtitle = driver.getTitle();
				Assert.assertEquals(util.EXPECT_TITLE, actualtitle);

			}
			finally{
				System.out.print("Test Case Successfull" + i + "=");				
			}
		}

	}

}

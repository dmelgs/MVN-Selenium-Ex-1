package nvmSelenium;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;

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

	public static void main(String[] args) throws Exception {
		ParameterizeTestingExcel parExc =
				new ParameterizeTestingExcel("C:\\Users\\acer\\OneDrive - United Electronics Co. (EXTRA)\\Desktop\\UserCredentials.xlsx", "Sheet1");	
		 System.out.println("The Row count is " + parExc.excel_get_rows());
		 int rows = parExc.excel_get_rows();
		 for(int i = 1 ; i < rows; i++) {
			 parExc.getCellDataasstring(i, 0);
		 }
		 
		 
	}

}

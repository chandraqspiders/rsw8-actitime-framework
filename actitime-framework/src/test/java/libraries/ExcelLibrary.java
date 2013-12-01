package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExcelLibrary {

	/**
	 * @param args
	 */
	
	public String getExcelData(String sheetName, int rowNum, int cellNum){
		String retVal=null;
		try {
			Workbook wb = WorkbookFactory.create(new File("../data/data.xlsx"));			
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.getCell(cellNum);			
			retVal = c.getStringCellValue();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	public int getRowCount(String sheetName){
		int rowCnt=0;
		try {
			Workbook wb = WorkbookFactory.create(new File("../data/data.xlsx"));			
			Sheet s = wb.getSheet(sheetName);
			rowCnt = s.getLastRowNum(); //zero based index of the last row
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowCnt;
	}
	public void writeDataToExcel(String sheetName, int rowNum, int cellNum, String data){
		try {
			FileInputStream fis = new FileInputStream("../data/data.xlsx");
			Workbook wb = WorkbookFactory.create(fis);			
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);	
			//Cell ce = r.getCell(cellNum);
			//int type = ce.getCellType();
			Cell c = r.createCell(cellNum);			
			c.setCellValue(data);
			FileOutputStream fos = new FileOutputStream("../data/data.xlsx");
			wb.write(fos);			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.crmPRACTICE;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataFromExcelSheetTest {
	@Test
	public void readDataFromExcel() throws Throwable
	{
		//Step 1: Load Excel sheet
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\Data.xlsx");
		
		//Step 2: Create a workbook 
		Workbook wb = WorkbookFactory.create(fis);
		
		//step 3: Get the sheet
		Sheet sheet = wb.getSheet("Sheet1");
		
		//Step 4: Get the row
		Row r = sheet.getRow(3);
		
		//Step 5: Get the cell
		Cell c = r.getCell(3);
		
		//step 6: read the data from the cell
		String value = c.getStringCellValue();
		System.out.println(value);
	}

}

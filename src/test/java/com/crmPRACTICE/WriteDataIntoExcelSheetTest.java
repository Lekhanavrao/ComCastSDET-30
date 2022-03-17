package com.crmPRACTICE;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class WriteDataIntoExcelSheetTest {
	@Test
	public void writeDataIntoExcelSheetTest() throws Throwable
	{
		//Step 1: Load Excel sheet
				FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\Data.xlsx");
				
				//Step 2: Create a workbook
				Workbook wb = WorkbookFactory.create(fis);
				
				//step 3: Get the sheet
				Sheet sheet = wb.getSheet("Sheet1");
				
				//Step 4: Get the row
				Row r = sheet.createRow(3);
				
				//Step 5: Get the cell
				Cell c = r.createCell(3);
				
				//Step 6: Set a cell value
				c.setCellValue("TC_3");
				
				//step 7: Open file in write mode
				FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\Data.xlsx");
				wb.write(fos);
	}

}

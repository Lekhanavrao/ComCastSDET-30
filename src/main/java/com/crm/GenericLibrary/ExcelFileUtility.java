package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author User1
 *
 */
public class ExcelFileUtility {
	
	/**
	 * This method will read data from excel sheet and return the value when sheetname, row no and cell no is specified
	 * @param Sheetname
	 * @param rowNo
	 * @param cellNo
	 * @throws Throwable
	 */
public String readDataFromExcelSheet(String Sheetname, int rowNo, int cellNo) throws Throwable
{
	FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(Sheetname);
	Row ro = sh.getRow(rowNo);
	Cell cel = ro.getCell(cellNo);
	String value = cel.getStringCellValue();
	return value;	
}

/**
 * This method will write data into excel sheet
 * @param Sheetname
 * @param rowNo
 * @param cellNo
 * @param value
 * @throws Throwable
 */
public void writeDataIntoExcelSheet(String Sheetname, int rowNo, int cellNo, String value) throws Throwable
{
	FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(Sheetname);
	Row ro = sh.getRow(rowNo);
	Cell cel = ro.getCell(cellNo);
	cel.setCellValue(value);
	FileOutputStream fos = new FileOutputStream(IPathConstants.ExcelPath);
	wb.write(fos);
}

/**
 * This method will return last row number
 * @param Sheetname
 * @return
 * @throws Throwable
 */
public int getRowCount(String Sheetname) throws Throwable
{
	FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(Sheetname);
	int row = sh.getLastRowNum();
	return row;
}

/**
 * This method will read multiple data from excel sheet with the help of sheetname
 * @param SheetName
 * @param n
 * @return
 * @throws Throwable
 */
public Object[][] readmultipleDataFromExcel(String SheetName,int n) throws Throwable
{
	FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(SheetName);
	int lastRow = sh.getLastRowNum();
	int lastCell = sh.getRow(0).getLastCellNum();
	
	Object[][] data = new Object[lastRow][lastCell-n];
	
	for(int i = 0;i<lastRow;i++)
	{
		for(int j=0;j<lastCell-n;j++)
		{
			data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
		}
	}
	
	return data;
  }
}

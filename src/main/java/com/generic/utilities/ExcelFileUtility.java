package com.generic.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consist of generic method related to Excel file.
 * 
 * @author Vivekanand M
 *
 */
public class ExcelFileUtility {

	/**
	 * This method will read data from excel sheet and return the value to caller
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String sheetName, int rowNo, int cellNo)
			throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IConstatantsUtility.ExcelFilePath);
		Workbook book = WorkbookFactory.create(file);
		return book.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
	}

	/**
	 * This method contains write method to Excel File.
	 * 
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetName, int row, int cell, String value)
			throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IConstatantsUtility.ExcelFilePath);
		Workbook book = WorkbookFactory.create(file);
		book.createSheet(sheetName).createRow(row).createCell(cell).setCellValue(value);
		FileOutputStream fileOS = new FileOutputStream(IConstatantsUtility.ExcelFilePath);
		book.write(fileOS);
		book.close();
	}

	/**
	 * This mehthod will read multiple data from excel file.
	 * 
	 * @param SheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipleData(String SheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IConstatantsUtility.ExcelFilePath);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet(SheetName);

		int row = sheet.getLastRowNum();
		short cell = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[row][cell];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < cell; j++) {
				data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}

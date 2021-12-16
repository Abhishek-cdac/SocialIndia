package com.si.mntcbill.xl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReaderWriter {

	public static void main(String[] args) throws IOException {
		String excelFileName = "E:\\Mahesh\\SOCIETY DOC\\excel-tmp";
		String uniqueVal = "12";
		
		String filepath = excelFileName + "\\" + uniqueVal;
		File file = new File(filepath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		XLSXReaderWriter.createExcelTemplate(filepath + "/" + uniqueVal + ".xlsx");
	}

	public static void createExcelTemplate(String excelFileName)
			throws IOException {

		String sheetName = "biil";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		XSSFRow row = sheet.createRow(0);

		// iterating c number of columns
		String[] cols = { "Monthly / QTLY maintenances ", "Municipal tax",
				"Water charge", "Federation charges", "Repair fund",
				"Sinking fund", "Major repair funds", "Non-Occupancy charges",
				"Play zone fees", "Penalties", "Interest", "Late fees",
				"Insurance cost", "Car parking 1", "Car parking 2",
				"Two wheeler 1", "Two wheeler 2", "Sundry adjustment",
				"Property tax", "Excess Payments", "Club House", "Arrears",
				"Previous Dues", "APP Subscription Fee", "Total Payable",
				"Amount in Words", "Bill No", "Flat No", "Flat Area", "Notes",
				"RSRVD 1", "RSRVD 2", "RSRVD 3", "RSRVD 4", "RSRVD 5",
				"RSRVD 6", "RSRVD 7", "RSRVD 8", "RSRVD 9", "RSRVD 10" };

		System.out.println(cols.length);

		for (int i = 0; i < cols.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(cols[i]);
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static void readXLSXFile(String excelFilename) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(excelFilename);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				if (cell.getCellTypeEnum() == CellType.STRING) {
					System.out.print(cell.getStringCellValue() + " ");
				} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					System.out.print(cell.getNumericCellValue() + " ");
				} else {
					// U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}

	}

}
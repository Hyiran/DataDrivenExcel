package DataDrivenSample.DataDrivenExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class was created based on the A. K. Sahu Blog
 * http://seleniummaster.com/sitecontent/index.php/selenium-web-driver-menu/selenium-test-automation-with-java/167-write-selenium-webdriver-testng-result-to-excel-file
 * 
 * Allows us the use of an Excel spreadsheet to provide input data to a test or
 * set of tests.
 * 
 */
public class WritingExcel {
	private Workbook wb;
	private Sheet ws;
	private Row rw;
	FileOutputStream fileOut;

	/**
	 * Create an excel file with a sheet
	 * 
	 * @param fileName
	 *            name of the file where you want data
	 * @param sheetName
	 *            name of the sheet in the excel file
	 */
	public WritingExcel(String fileName, String sheetName) {
		try {
			File file = new File(fileName);
			if (fileName.indexOf("xlsx") < 0) { // for .xls format
				wb = new HSSFWorkbook();
			    fileOut = new FileOutputStream(file);

			    ws = wb.createSheet(sheetName);
			} else { // for .xlsx format
				wb = new XSSFWorkbook();
			    fileOut = new FileOutputStream(file);

			    ws = wb.createSheet(sheetName);
			}
		} catch (IOException io) {
			System.out.println(io);
			throw new Error("Invalid file '" + fileName
					+ "' or incorrect sheet '" + sheetName
					+ "', enter a valid one");
		}
	}
	
    /**
	 * Close the new excel file with a sheet
     * @return 
	 */
	public void closeExcel() {
	    try {
			wb.write(fileOut);
		    fileOut.close();
		    wb.close();
		} catch (Exception e) {
			throw new Error("Cannot close the new Excel file$");
		}
	}

    /**
	 * Create a new row in the opened sheet
	 * 
	 * @param rowIndex
	 *            starting with 0 index
	 */
	public void createRow(int rowIndex) {
		try {
			ws.createRow(rowIndex);
		} catch (Exception e) {
			throw new Error("The row '" + rowIndex + "' doesn't exist in the sheet");
		}
	}
    /**
	 * Sets a cell value from the opened sheet
	 * 
	 * @param rowIndex
	 *            starting with 0 index
	 * @param columnIndex
	 *            starting with 0 index
	 */
	public void setCell(int rowIndex, int columnIndex, String resultValue) {
		try {
			rw = ws.getRow(rowIndex);
            CellStyle style = wb.createCellStyle();
            if (rowIndex == 0) {
            	Font font = wb.createFont();
                font.setFontHeightInPoints((short)12);
                font.setFontName("Calibri");
                font.setItalic(false);
                font.setBold(true);
                font.setStrikeout(false);

                style.setFont(font);
                style.setAlignment(CellStyle.ALIGN_CENTER);
			}
            
            if (resultValue.equalsIgnoreCase("Fail")) {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			} else if (resultValue.equalsIgnoreCase("Pass")) {
                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			
			Cell cell = rw.createCell(columnIndex);
            cell.setCellValue(resultValue);
            
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(CellStyle.BORDER_THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cell.setCellStyle(style);
		} catch (Exception e) {
			throw new Error("The cell with row '" + rowIndex + "' and column '"
					+ columnIndex + "' doesn't exist in the sheet");
		}
	}
}

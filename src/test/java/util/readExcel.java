package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class readExcel {
	ArrayList<String> rowData;
	ArrayList<String> headerText;
	public static LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
	Sheet sheet;

	public readExcel(String sheetName) {
		try {
			File file = new File("D:/workspace/actitime/src/test/java/testdata/TestData.xlsx");
			FileInputStream fis = new FileInputStream(file);
			Workbook wBook = WorkbookFactory.create(fis);
			sheet = wBook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int rowCount() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	public int colCount() {
		int colCount = sheet.getRow(0).getLastCellNum();
		return colCount;
	}

	public String cellValue(int rowInd, int colInd) {
		String cellValue = String.valueOf(sheet.getRow(rowInd).getCell(colInd));
		return cellValue;
	}

	public ArrayList<String> getHeaderText() {
		headerText = new ArrayList<String>();
		for (int i = 0; i < colCount(); i++) {
			headerText.add(cellValue(0, i));
		}
		return headerText;
	}

	public ArrayList<String> getRowData(int rowInd) {
		rowData = new ArrayList<String>();
		for (int i = 0; i < colCount(); i++) {
			rowData.add(cellValue(rowInd, i));
		}
		for (int key = 0; key < colCount(); key++) {
			lhm.put(headerText.get(key), rowData.get(key));
		}
		return rowData;
	}

}

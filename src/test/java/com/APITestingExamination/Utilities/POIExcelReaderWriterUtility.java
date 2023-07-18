package com.APITestingExamination.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIExcelReaderWriterUtility {
	public ArrayList<ArrayList> readSheetDataFromXLFile(String sheetName ) throws InvalidFormatException, IOException  {
		File file ; 
		//XSSFWorkbook workbook ; 
		
		file = new File (Constants.EXCELFILEPATH) ;
		ArrayList<ArrayList > xlrows = new ArrayList<ArrayList> ()  ;
		XSSFWorkbook workbook = new XSSFWorkbook (file) ; 
		XSSFSheet sheet = workbook.getSheet(sheetName) ;
		Iterator<Row> rows = sheet.rowIterator();
		 
		 while (rows.hasNext()) {
			 XSSFRow row  = (XSSFRow) rows.next() ;
			 Iterator<Cell> cells =  row.cellIterator() ;
			 ArrayList xlcells = new ArrayList() ;
			 while(cells.hasNext()) { 
				 XSSFCell cell = (XSSFCell) cells.next();
				if ( cell.getCellType() == CellType.STRING)  {
					String str = cell.getStringCellValue();
				// System.out.print( str  +"\t")  ; 
				 xlcells.add(str);
				}
				else if  ( cell.getCellType() == CellType.NUMERIC)  {
					int n = (int)cell.getNumericCellValue();
					//System.out.print(n+ "\t");
					xlcells.add(n ) ;
				}
			 }
			// System.out.println();
			 xlrows.add(xlcells) ;
		 } // while ends 
		// System.out.println(xlrows);
		 workbook.close();
		 return xlrows;
		 
	} // function ends 
	
	public  ArrayList getEmpRecord(int rownum)  throws InvalidFormatException, IOException{
		//POIExcelReaderWriterUtility poireader = new POIExcelReaderWriterUtility();
		ArrayList<ArrayList> list = readSheetDataFromXLFile("Data");
		
		return list.get(rownum) ; 
	}
}

package readTestData;
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Storage.StorageVariables;

public class ReadExcel 
{

	public String cellval="";
	
    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create an object of File class to open xlsx file

    File file =    new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook TestWorkbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx"))
    {

    //If it is xlsx file then create object of XSSFWorkbook class

    TestWorkbook = new XSSFWorkbook(inputStream);

    }

    //Check condition if the file is xls file

    else if(fileExtensionName.equals(".xls"))
    {

        //If it is xls file then create object of HSSFWorkbook class

        TestWorkbook = new HSSFWorkbook(inputStream);

    }

    //Read sheet inside the workbook by its name

    Sheet TestSheet = TestWorkbook.getSheet(sheetName);

    //Find number of rows in excel file

    int rowCount = TestSheet.getLastRowNum()-TestSheet.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 1; i < rowCount+1; i++) {

        Row row = TestSheet.getRow(i);
        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Print Excel data in console
        	 String type = row.getCell(j).getCellType().toString();
        	 
        	 if(type.equalsIgnoreCase("NUMERIC"))
        	 {
        		 
        		// DataFormatter formatter = new DataFormatter();
        		 //Cell cell = row.getCell(j);
        		 System.out.print(cellval+"|| ");
        		 double temp = row.getCell(j).getNumericCellValue();
        		 int cellvalue = (int)temp;
        		 cellval=Integer.toString(cellvalue);  
        		 
        	 }
        	 
        	 
        	 else
        	 {
        		 System.out.print(row.getCell(j).getStringCellValue()+"|| ");
        		 cellval=row.getCell(j).getStringCellValue();
        	 }
        	 
        	 switch(j) 
        	 {
        	 case 0: 
                 StorageVariables.actions.add(cellval); 
                 break; 
             case 1: 
            	 StorageVariables.targets.add(cellval);
                 break; 
             case 2: 
            	 StorageVariables.values.add(cellval);
            	 break;
        	 }

        }

        System.out.println();
    } 

 

    }

}

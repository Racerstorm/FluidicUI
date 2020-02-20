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
	String type="";
	int cell_row,cell_column;
	
    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create an object of File class to open xlsx file
    try
    {
  	  	

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

    for (cell_row = 1; cell_row < rowCount+1; cell_row++) {

        Row row = TestSheet.getRow(cell_row);
        //Create a loop to print cell values in a row
        int var=row.getLastCellNum();

        for (int cell_column = 0; cell_column < row.getLastCellNum(); cell_column++) {

            //Print Excel data in console
        	
        	  type = row.getCell(cell_column).getCellType().toString();
        	        	
        	
        	 if(type.equalsIgnoreCase("NUMERIC"))
        	 {
        		 
        		// DataFormatter formatter = new DataFormatter();
        		 //Cell cell = row.getCell(j);
        		 System.out.print(cellval+"|| ");
        		 double temp = row.getCell(cell_column).getNumericCellValue();
        		 int cellvalue = (int)temp;
        		 cellval=Integer.toString(cellvalue);  
        		 
        	 }
        	 
        	 
        	 else
        	 {
        		 System.out.print(row.getCell(cell_column).getStringCellValue()+"|| ");
        		 cellval=row.getCell(cell_column).getStringCellValue();
        	 }
        	 
        	 switch(cell_column) 
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
    
    catch(Exception ex)
	{
	  if(ex.toString().equalsIgnoreCase("java.lang.NullPointerException"))
		{
		  cell_column+=cell_column;
		  switch(cell_column) 
     	 {
     	 case 0: 
              StorageVariables.actions.add("BLANK"); 
              break; 
          case 1: 
         	 StorageVariables.targets.add("BLANK");
              break; 
          case 2: 
         	 StorageVariables.values.add("BLANK");
         	 break;
     	 }

		  
		 }
	  	
	}
 

    }

}

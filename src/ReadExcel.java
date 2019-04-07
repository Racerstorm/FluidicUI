/*
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {

    public static void main(String[] args) throws Exception {
        // An excel file name. You can create a file name with a full
        // path information.
        String filename = "C:\\Users\\Chitrangadans\\Desktop\\data.xls";

        // Create an ArrayList to store the data read from excel sheet.
        List<List<HSSFCell>> sheetData = new ArrayList<>();
        
        

        try (FileInputStream fis = new FileInputStream(filename)) {
            // Create an excel workbook from the file system.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            // Get the first sheet on the workbook.
            HSSFSheet sheet = workbook.getSheetAt(0);

            // When we have a sheet object in hand we can iterator on
            // each sheet's rows and on each row's cells. We store the
            // data read on an ArrayList so that we can printed the
            // content of the excel to the console.
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List<HSSFCell> data = new ArrayList<>();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        showExcelData(sheetData);
    }

    private static void showExcelData(List<List<HSSFCell>> sheetData) {
        // Iterates the data and print it out to the console.
        for (List<HSSFCell> data : sheetData) {
            for (int j = 0; j < data.size(); j++) {
                HSSFCell cell = data.get(j);
                System.out.print(cell);
            }
            System.out.println("");
        }
        
    }
}*/
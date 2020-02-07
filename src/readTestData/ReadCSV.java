package readTestData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import Storage.StorageVariables;

public class ReadCSV {

	public static void readfromCSV() throws FileNotFoundException, IOException
	{
		
	 // StorageVariables.csvLocation="C:\\Automation\\TestData\\Test.csv"; 
	  StorageVariables.delimiter='|';
	  
	  File folder = new File("/C:/Automation/TestData/");
	  File[] listOfFiles = folder.listFiles();

	  for (File file : listOfFiles) {
	      if (file.isFile()) {
	    	    String tempfile=file.getName();
	          //	StorageVariables.file=file.getName();
	          	if(tempfile.equalsIgnoreCase(StorageVariables.inputFile))
	          	{
	          		StorageVariables.file=tempfile;
	          		StorageVariables.csvLocation=folder+"\\"+tempfile;
	          		if (tempfile.indexOf(".") > 0)
	          			tempfile = tempfile.substring(0, tempfile.lastIndexOf("."));
	          		// return fileName;
	          	}
	      }
	  }
	 
	  
	 //csvLocation="D:\\eclipse-workspace\\Test.csv";
	//public OutputFormatter output;
	//List<String[]> actions;
	  
        try
       /* (FileInputStream fis = new FileInputStream(csvLocation);
            InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader(isr)) */
        {
        	//CSVReader reader = new CSVReader(new FileReader(StorageVariables.csvLocation),StorageVariables.delimiter);
        	CSVReader reader = new CSVReader(new FileReader(StorageVariables.csvLocation),StorageVariables.delimiter);
            String[] nextLine;

        while ((nextLine = reader.readNext()) != null)
        {

            for (String e : nextLine) 
            {   
            	if(!e.startsWith("target")&&!e.startsWith("value"))
            	{
            		StorageVariables.actions.add(e);
                //System.out.format("%s ", e);
            	}
            	
            	else if(e.startsWith("target"))
            	{
            		
            		StorageVariables.targets.add(e.substring(7));
            	}
            	
            	else if(e.startsWith("value"))
            	{
            		e.replace("value=", "");
            		StorageVariables.values.add(e.substring(6));
            	}
            }
        }
        reader.close();
    }
        catch(Exception ex)
        {
        	//Block
        }
}
	
	/* public static void readFile() throws FileNotFoundException, IOException
	{
		
		Scanner scanner = new Scanner(new File("D:\\eclipse-workspace\\Test.csv"));
        
        //Set the delimiter used in file
        scanner.useDelimiter("|");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
        	 String nextline  =scanner.nextLine();
        	for (String e : scanner.) 
            {   
            	if(!e.startsWith("target")&&!e.startsWith("value"))
            	{
            	actions.add(e);
                //System.out.format("%s ", e);
            	}
            	
            	else if(e.startsWith("target"))
            	{
            		
            	    target.add(e.substring(7));
            	}
            	
            	else if(e.startsWith("value"))
            	{
            		e.replace("value=", "");
            		value.add(e.substring(6));
            	}
            }
        } */
         
        //Do not forget to close the scanner 
       // scanner.close();
	}




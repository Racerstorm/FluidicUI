package readCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class ReadCSV {

	public static ArrayList<String> actions = new ArrayList<String>();
	public static ArrayList<String> target = new ArrayList<String>();
	public static ArrayList<String> value = new ArrayList<String>();
	public static void readfromCSV() throws FileNotFoundException, IOException
	{
	String csvLocation="D:\\eclipse-workspace\\Test.csv";
	//public OutputFormatter output;
	//List<String[]> actions;
	  
        try
       /* (FileInputStream fis = new FileInputStream(csvLocation);
            InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader(isr)) */
        {
        	CSVReader reader = new CSVReader(new FileReader(csvLocation),'|');
            String[] nextLine;

        while ((nextLine = reader.readNext()) != null)
        {

            for (String e : nextLine) 
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
        }
       for (int i=0;i<=actions.size();i++)
       {
    	   System.out.println(actions.get(i)+" "+target.get(i)+" "+value.get(i)+"\n");
       }
    }
        catch(Exception ex)
        {
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




package testStartup;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//import org.glassfish.jersey.internal.guava.Stopwatch;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DBOperations.StepsfromDB;
import Storage.StorageVariables;
import actions.PageActions;
import logger.Logger;
import readTestData.ReadCSV;
import readTestData.ReadExcel;

 
@Test

public class LaunchTest {
 
	ExtentReports extent;
	
  @BeforeTest(alwaysRun = true)
//  @Parameters("browser")
  
  @Parameters({ "OS", "browser", "mobileautomation","datasource","filename"})

  public void Setup(String OS,String browser,boolean mobileautomation,String datasource,String filename) 
  {
	  Logger.logmessage("Reading steps from the input file");
	  StorageVariables.testdataSource=datasource;
	  StorageVariables.inputFile=filename;
	  //StorageVariables.testcaseSheet=TCsheet;
	  StorageVariables.mobileAutomation=true;
	  StorageVariables.OS=OS;
	  
	//Read file path from the properties file.
		
		 try  {

	            StorageVariables.prop = new Properties();
	            FileInputStream file = null;
	            if(OS.equalsIgnoreCase("Windows"))
	            {			             
	             file = new FileInputStream("C:\\Users\\ust52622\\git\\POMFramework\\src\\main\\java\\com\\testwebapp\\config\\config.properties");
	            }
	            
	            if(OS.equalsIgnoreCase("MAC"))
	            {
	              file = new FileInputStream("/Users/macbook/git/FluidicUI/Java Resources/config.properites");
	            }
	            	
	             StorageVariables.prop.load(file);
	           
	             StorageVariables.testdataPath=StorageVariables.prop.getProperty("testdataPath");
	             StorageVariables.driverPath=StorageVariables.prop.getProperty("webdriverPath");
	             StorageVariables.htmlreportPath=StorageVariables.prop.getProperty("reportPath");
	             StorageVariables.screenshotPath=StorageVariables.prop.getProperty("screenshotsPath");

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	  //Read test case steps from CSV file.  
			try
			{
				if(StorageVariables.testdataSource.equalsIgnoreCase("DB"))
				{
				  StepsfromDB.readtestcasestepsfromDB();
				}
				
				else if(StorageVariables.testdataSource.equalsIgnoreCase("csv"))
				{
				  StorageVariables.inputFile+=".csv";
				  ReadCSV.readfromCSV();
				}
				
				else if(StorageVariables.testdataSource.equalsIgnoreCase("excel"))
				{
					
					readTestData.ReadExcel objExcelFile = new ReadExcel();
					//String filePath = "C:/Automation/TestData";c
					 System.out.println("Entering the excel file");

				    //Call read file method of the class to read data
				    objExcelFile.readExcel(StorageVariables.testdataPath,StorageVariables.inputFile,StorageVariables.testcaseSheet);

				}
		    }
			
			catch (Exception e) 
			    {
	        	  e.printStackTrace();
			    }
			
			
			
		    
		    
			StorageVariables.browser=browser;	
			StorageVariables.mobileAutomation=mobileautomation;
			
				
			Logger.logmessage("Browser : "+StorageVariables.browser);
		 //   StorageVariables.startTime = System.nanoTime();
			LaunchBrowser.LaunchBrowser();	
			
			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); 
			StorageVariables.report = new ExtentReports(StorageVariables.htmlreportPath+StorageVariables.inputFile+" TestReport" +df.format(new Date())+".html",false);
//			StorageVariables.file = StorageVariables.file.substring(0, StorageVariables.file.lastIndexOf('.'));
			if(!StorageVariables.testdataSource.equalsIgnoreCase("excel"))
			{
				StorageVariables.test = StorageVariables.report.startTest(StorageVariables.inputFile);	
			}
			else
			{
			StorageVariables.test = StorageVariables.report.startTest(StorageVariables.testcaseSheet);
			}
			StorageVariables.warningCounter=0;
  }

  @Test(alwaysRun = true) //(invocationCount = 5)
  public  void Start()
  {
	  
	  Logger.logmessage("----------Starting Test(s)_---------"+"<br> Browser : "+StorageVariables.browser+"<br> Testcase : "+StorageVariables.inputFile);
	  
	  for(int counter= 0;counter<StorageVariables.actions.size();counter++)
      {
		  System.out.println("Inside the loop"); 
		StorageVariables.stepNumber=counter+1;
      	StorageVariables.Action = StorageVariables.actions.get(counter).trim();
      	StorageVariables.Target = StorageVariables.targets.get(counter);
      	StorageVariables.Value = StorageVariables.values.get(counter);
      //  Logger.logmessage("Executing Step "+StorageVariables.stepNumber+ ": "+StorageVariables.Action+"");
      	LaunchBrowser.gotoAction();
      	//Logger.logmessage("\nCompleted Step "+StorageVariables.stepNumber+": "+StorageVariables.Action+"");
        StorageVariables.stepLog="";
        PageActions.unhighlightLast();
      }
	  
  }
  
 /* @AfterMethod
  public void getResult(ITestResult result) throws IOException
  {
      if(result.getStatus() == ITestResult.FAILURE)
      {
          String screenShotPath = StorageVariables.screenshotFile;
          StorageVariables.test.log(LogStatus.FAIL, result.getThrowable());
          StorageVariables.test.log(LogStatus.FAIL, "Snapshot below: " + StorageVariables.test.addScreenCapture(screenShotPath));
      }
      extent.endTest(StorageVariables.test);
  }*/

  
  
  @AfterTest
  public void Cleanup() 
  {
	 
	    if(StorageVariables.stepResult==StorageVariables.STEPRESULT.PASSED)
        {
        	Logger.logmessage("Testcase passed");
        	
        }
	    
	    else if(StorageVariables.stepResult==StorageVariables.STEPRESULT.WARNING)
	    {
	    	Logger.logmessage("Testcase failed with "+StorageVariables.warningCounter+" warning(s).");
	    }
        else
        {
        	Logger.logmessage("Testcase failed with error(s)");
        	
        }
	//    long finishTime = System.nanoTime();
	  //  long timeElapsed = finish - startTime;
           try
           {
        	StorageVariables.actions.clear();
        	StorageVariables.targets.clear();
        	StorageVariables.values.clear();
        //	StorageVariables.driver.close();
        	//StorageVariables.driver.quit();
        	if(StorageVariables.browser.equalsIgnoreCase("Chrome"))
        	{
        		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        	}
           }
           catch(Exception e)
           {}
	      //Closing the report
	        StorageVariables.report.endTest(StorageVariables.test);
	        StorageVariables.report.flush();
	        StorageVariables.report.close();

 	}
     
  

}

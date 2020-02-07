package testStartup;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
	
  @BeforeTest
//  @Parameters("browser")
  @Parameters({ "browser", "mobileautomation","datasource","filename","TCsheet"})

  public void Setup(String browser,boolean mobileautomation,String datasource,String filename,String TCsheet) 
  {
	  Logger.logmessage("Reading steps from the input file");
	  StorageVariables.testdataSource=datasource;
	  StorageVariables.inputFile=filename;
	  StorageVariables.testcaseSheet=TCsheet;
	  

	  //Read test case steps from CSV file.
			try
			{
				if(StorageVariables.testdataSource.equalsIgnoreCase("DB"))
				{
				StorageVariables.file="hikvision_qa_create_landingpage";
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
					//String filePath = "C:/Automation/TestData";


				    //Call read file method of the class to read data
				    objExcelFile.readExcel(StorageVariables.testdataPath,StorageVariables.inputFile,StorageVariables.testcaseSheet);

				}
		    }
			
			catch (Exception e) 
			    {
	        	  e.printStackTrace();
			    }
			
		    Properties prop = new Properties();
		    
			StorageVariables.browser=browser;	
			StorageVariables.mobileAutomation=mobileautomation;
			StorageVariables.screenshotPath="C:\\Automation\\Screenshots\\";
			StorageVariables.htmlreportPath="C:\\Automation\\Reports\\";
		    Logger.logmessage("Browser : "+StorageVariables.browser);
			LaunchBrowser.LaunchBrowser();	
			
			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); 
			StorageVariables.report = new ExtentReports(StorageVariables.htmlreportPath+StorageVariables.testcaseSheet+" TestReport" +df.format(new Date())+".html",false);
//			StorageVariables.file = StorageVariables.file.substring(0, StorageVariables.file.lastIndexOf('.'));
			StorageVariables.test = StorageVariables.report.startTest(StorageVariables.testcaseSheet);
  }

  @Test
  public  void Start()
  {
	  
	  Logger.logmessage("----------Starting Test---------"+"<br> Browser : "+StorageVariables.browser+"<br> Testcase : "+StorageVariables.file);
	  for(int counter= 0;counter<StorageVariables.actions.size();counter++)
      {
		StorageVariables.stepNumber=counter+1;
      	StorageVariables.Action = StorageVariables.actions.get(counter);
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
           try
           {
        	StorageVariables.actions.clear();
        	StorageVariables.targets.clear();
        	StorageVariables.values.clear();
        	StorageVariables.driver.close();
        	StorageVariables.driver.quit();
           }
           catch(Exception e)
           {}
	      //Closing the report
	        StorageVariables.report.endTest(StorageVariables.test);
	        StorageVariables.report.flush();
	        StorageVariables.report.close();

 	}
     
  

}

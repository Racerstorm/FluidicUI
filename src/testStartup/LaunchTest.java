package testStartup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DBOperations.StepsfromDB;
import Storage.StorageVariables;
import actions.PageActions;
import logger.Logger;
import readCSV.ReadCSV;

@Test
public class LaunchTest {
  
	ExtentReports extent;
	
  @BeforeTest
 
  public void Setup() 
  {
	  Logger.logmessage("Reading steps from the input file");
	  StorageVariables.testdataSource="csv";
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
				  ReadCSV.readfromCSV();
				}
		    }
			
			catch (Exception e) 
			    {
	        	  e.printStackTrace();
			    }
			
		    Properties prop = new Properties();
		    
			StorageVariables.browser="Firefox";		
			StorageVariables.screenshotPath="C:\\Automation\\Screenshots\\";
			StorageVariables.htmlreportPath="C:\\Automation\\Reports\\File";
		    Logger.logmessage("Browser : "+StorageVariables.browser);
			LaunchBrowser.LaunchBrowser();	
			
			StorageVariables.report = new ExtentReports(StorageVariables.htmlreportPath+"TestReport.html",false);
			StorageVariables.test = StorageVariables.report.startTest("Automation Test");
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
       
	        StorageVariables.driver.close();
	        StorageVariables.driver.quit();
	        
	      //Closing the report
	        StorageVariables.report.endTest(StorageVariables.test);
	        StorageVariables.report.flush();
	        StorageVariables.report.close();

 	}
     
  

}

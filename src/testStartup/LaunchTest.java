package testStartup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Storage.StorageVariables;
import logger.Logger;
import readCSV.ReadCSV;

@Test
public class LaunchTest {
  
  @BeforeTest
  public void Setup() 
  {
	  Logger.logmessage("Reading steps from the input file");
	//Read test case steps from CSV file.
			try
			{
				  ReadCSV.readfromCSV();
		    }
			
			catch (Exception e) 
			    {
	        	  e.printStackTrace();
			    }
			StorageVariables.browser="Chrome";		
			StorageVariables.screenshotPath="C:\\Automation\\Screenshots\\File";
			StorageVariables.htmlreportPath="C:\\Automation\\Reports\\File";
		    Logger.logmessage("Browser : "+StorageVariables.browser);
			LaunchBrowser.LaunchBrowser();	
			
			StorageVariables.report = new ExtentReports(StorageVariables.htmlreportPath+"TestReport.html",false);
			StorageVariables.test = StorageVariables.report.startTest("Automation Test");
  }

  @Test
  public void Start()
  {
	  Logger.logmessage("----------Starting Test---------");
	  for(int counter= 0;counter<StorageVariables.actions.size();counter++)
      {
		StorageVariables.stepNumber=counter+1;
      	StorageVariables.Action = StorageVariables.actions.get(counter);
      	StorageVariables.Target = StorageVariables.targets.get(counter);
      	StorageVariables.Value = StorageVariables.values.get(counter);
        Logger.logmessage("Executing Step "+StorageVariables.stepNumber+ ": "+StorageVariables.Action+"");
      	LaunchBrowser.gotoAction();
      	Logger.logmessage("\nCompleted Step "+StorageVariables.stepNumber+": "+StorageVariables.Action+"");
      }
  }
  
  @AfterTest
  public void Cleanup() 
  {
	    if(StorageVariables.testcaseStatus==true)
        {
        	Logger.logmessage("Testcase passed");
        	
        }
        else
        {
        	Logger.logwarning("Testcase failed with "+StorageVariables.warningCounter+" warning(s).");
        	
        }
       
	        StorageVariables.driver.close();
	        StorageVariables.driver.quit();
	        
	      //Closing the report
	        StorageVariables.report.endTest(StorageVariables.test);
	        StorageVariables.report.flush();
	        StorageVariables.report.close();

 	}
     
  

}

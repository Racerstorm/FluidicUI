package logger;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentReports;

import Storage.StorageVariables;
import actions.CustomActions;
import actions.PageActions;

public class Logger
{
	public static void logsuccess(String log)
	{
		System.out.println("\n"+log);
		StorageVariables.stepsLogs.add("\n"+log);
		
		if(StorageVariables.stepNumber>0)
		{
			StorageVariables.test.log(LogStatus.PASS,"Executing Step : "+StorageVariables.stepNumber+ ": "+StorageVariables.Action+""
		    +"<br>"+"Locator is : "+StorageVariables.Target+"<br> Value is : "+StorageVariables.Value+"<br>"+StorageVariables.stepLog+"<br>"+log+"<br>Step passed : "+StorageVariables.Action);
			
		try {
			StorageVariables.messageType="success";
			CustomActions.stepMessageOnPage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		StorageVariables.stepLog="";
		}
	
	public static void logmessage(String log) 
	{
		System.out.println("\n"+log);
		StorageVariables.stepsLogs.add("\n"+log);
		
		StorageVariables.test.log(LogStatus.INFO, log);	
	}
	
	public static void logwarning(String log)
	{  
		System.out.println("\n"+log);
	    StorageVariables.warningCounter++;
		StorageVariables.stepsLogs.add("\n"+log);
		StorageVariables.stepResult=StorageVariables.STEPRESULT.WARNING;
		StorageVariables.testcaseResult =StorageVariables.TCRESULT.FAILED;
		//StorageVariables.testcaseStatus=false;
		PageActions.TakeSreenshot();
		if(!StorageVariables.Action.isEmpty())
		{   
			StorageVariables.test.log(LogStatus.WARNING,"Executing Step : "+StorageVariables.stepNumber+ ": "+StorageVariables.Action+""+"Locator is : "+StorageVariables.Action
				    +"<br>"+StorageVariables.stepLog+"<br>"+log+"<br>Step failed with warning, but the test case will continue : "+StorageVariables.Action+"<br>"+StorageVariables.test.addScreenCapture(StorageVariables.screenshotFile));
					
	//	StorageVariables.test.log(LogStatus.WARNING, "\nStep failed with warning, but the test case will continue : "+StorageVariables.Action);
		}
		
		else
		{
			StorageVariables.test.log(LogStatus.WARNING, log);
		}
		StorageVariables.testcaseStatus=false;
		StorageVariables.messageType="warning";
		try {
			CustomActions.stepMessageOnPage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
		//	PageActions.TakeSreenshot();
			StorageVariables.test.addScreenCapture(StorageVariables.screenshotFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logerror(String log)
	{
		StorageVariables.stepResult= StorageVariables.STEPRESULT.FAILED;
		
		System.out.println("\n"+log);
		//StorageVariables.warningCounter++;
		StorageVariables.stepsLogs.add("\n"+log);
		StorageVariables.stepResult=StorageVariables.STEPRESULT.FAILED;
		StorageVariables.testcaseResult =StorageVariables.TCRESULT.FAILED;
		StorageVariables.messageType="error";
		try {
			CustomActions.stepMessageOnPage();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			PageActions.TakeSreenshot();
			StorageVariables.test.log(LogStatus.FAIL,"Executing Step : "+StorageVariables.stepNumber+ ": "+StorageVariables.Action+""
				    +"<br>"+StorageVariables.Target+"<br>"+StorageVariables.Value+"<br>"+StorageVariables.stepLog+"<br>"+log+"<br>Step failed and the test case has been stopped"+StorageVariables.test.addScreenCapture(StorageVariables.screenshotFile));
			//StorageVariables.test.log(LogStatus.FAIL, StorageVariables.test.addScreenCapture(StorageVariables.screenshotFile));
		
			//StorageVariables.test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(StorageVariables.screenshotPath+"/"+StorageVariables.screenshotFile).build());

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		Assert.fail();
	}
}
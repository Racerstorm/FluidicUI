package logger;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Storage.StorageVariables;
import actions.CustomActions;
import actions.PageActions;

public class Logger
{
	public static void logsuccess(String log)
	{
		System.out.println("\n"+log);
		StorageVariables.stepsLogs.add("\n"+log);
		StorageVariables.stepResult=StorageVariables.STEPRESULT.PASSED;
		if(StorageVariables.stepNumber>0)
		{
			StorageVariables.test.log(LogStatus.PASS,log+"\nStep passed : "+StorageVariables.Action);
		try {
			StorageVariables.messageType="success";
			CustomActions.stepMessageOnPage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
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
		if(!StorageVariables.Action.isEmpty())
		{
		StorageVariables.test.log(LogStatus.WARNING, "\nStep failed with warning, but the test case will continue : "+StorageVariables.Action);
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
			PageActions.TakeSreenshot();
			StorageVariables.test.addScreenCapture(StorageVariables.screenshotFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logerror(String log)
	{
		System.out.println("\n"+log);
		StorageVariables.warningCounter++;
		StorageVariables.stepsLogs.add("\n"+log);
		StorageVariables.stepResult=StorageVariables.STEPRESULT.FAILED;
		StorageVariables.testcaseResult =StorageVariables.TCRESULT.FAILED;
		StorageVariables.test.log(LogStatus.ERROR, log, "Step failed and the test case has been stopped : "+StorageVariables.Action);
		StorageVariables.messageType="error";
		try {
			CustomActions.stepMessageOnPage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			PageActions.TakeSreenshot();
			StorageVariables.test.addScreenCapture(StorageVariables.screenshotPath+"/"+StorageVariables.screenshotFile);
			//StorageVariables.test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(StorageVariables.screenshotPath+"/"+StorageVariables.screenshotFile).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.fail();
	}
}
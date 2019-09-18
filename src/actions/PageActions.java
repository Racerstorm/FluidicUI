package actions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Storage.StorageVariables;
import logger.Logger;
import testStartup.LaunchBrowser;

public class PageActions 
{

	public static void verifyPageTitle()
	{
	 try
	   {
		
		 StorageVariables.stepLog="Expected page title :" + StorageVariables.Value+"<br>Actual page title :" + StorageVariables.driver.getTitle();
		
		if(StorageVariables.driver.getTitle().contains(StorageVariables.Value))
		{
            
           
            Logger.logsuccess("Expected and actual titles match"); 
		}
		
		else
		{   
			Logger.logwarning("Expected and actual titles do not match");
		}
		
      }
	   
	 catch(Exception e)
	 {
		 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e);
	 }
	}
	
	public static void waitForPageLoad() 
	{
		try
		{
			String pageLoadState = ((JavascriptExecutor)StorageVariables.driver).executeScript("if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}").toString();

			while(true)
			{
				if(pageLoadState.toUpperCase().equals("COMPLETE") || pageLoadState.toUpperCase().equals("LOADED"))
				{
					//ResultLogger.log("Page Load State: "+pageLoadState,ISSTEPACTION.True,RESULT.PASS);
					System.out.println("Page Load State: "+pageLoadState);

					break;
				}
				pageLoadState = ((JavascriptExecutor)StorageVariables.driver).executeScript("if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}").toString();

			}
		}
		catch(Exception e)
		{
          Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Failure at WaitforPageLoad ");
		}
		
	}
	
	public static boolean waitForJStoLoad() {

	    WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	           return ((int)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	            .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	
	public static void waitforElement() 
	{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		
	try
	 {
		WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StorageVariables.by));
		//Logger.logmessage("Element found on the page.");
	    StorageVariables.stepLog="Element found on the page.";
	 }
		catch(Exception e)
		{
			Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" WaitforElement function failed.");
		}
	}

	public static void TakeSreenshot() 
	{  
		waitForPageLoad();
		 
		try
		{
		File scrFile = ((TakesScreenshot)StorageVariables.driver).getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); // add S if you need milliseconds
		StorageVariables.screenshotFile=StorageVariables.screenshotPath+StorageVariables.file+"_Step "+StorageVariables.stepNumber+"_"+StorageVariables.Action + df.format(new Date()) +".png";
		FileUtils.copyFile(scrFile, new File(StorageVariables.screenshotFile));
		}
		
		catch(Exception exc)
		{Logger.logerror("Unable to capture screenshots.");}
		
	}

	public static void highlightElement(){
		
		try
		{
			StorageVariables.highlightedElement=StorageVariables.driver.findElement(StorageVariables.by);
		    StorageVariables.jse = (JavascriptExecutor)StorageVariables.driver;
		    StorageVariables.jse.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", StorageVariables.highlightedElement);
		}
		catch(Exception e)
		{}
		 }

    public static boolean isElementPresent() 
	     {
	    	 boolean elementPresence = false;
	    	 
	    	 try
	    	 {
	    	   StorageVariables.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	   StorageVariables.driver.findElement(StorageVariables.by);
	    	   elementPresence=true;
	    	   Logger.logmessage("Element is present on the page.");
	    	 }
	    
	    	  catch(Exception e)
	    	  {
	    		elementPresence=false;  
	    		Logger.logwarning("Element is not present\n");
	    	  }
	    	  
	    	  StorageVariables.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	  return elementPresence;
	     
	     }

	public static void Sleep()
	{
		long sleepTime = Integer.parseInt(StorageVariables.Value);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}

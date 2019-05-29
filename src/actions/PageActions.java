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
import testStartup.LaunchBrowser;

public class PageActions 
{

	public static void verifyPageTitle() throws InterruptedException
	{
	 try
	   { 
		if(StorageVariables.driver.getTitle().contains(StorageVariables.Value))
		{
			StorageVariables.messageType="success";
			CustomActions.stepMessageOnPage();
            System.out.println("Expected page title :" + StorageVariables.Value+"\n");
            System.out.println("Expected page title :" + StorageVariables.driver.getTitle());
		}
		
		else
		{   StorageVariables.messageType="warning";
			CustomActions.stepMessageOnPage();
			TakeSreenshot();
		}
      }
	   
	 catch(Exception e)
	 {
		    StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
			TakeSreenshot();
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
		catch(Exception ex)
		{

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
	
	public static void waitforElement() throws InterruptedException
	{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		
	try
	 {
		WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StorageVariables.by));
		//element= wait.until(ExpectedConditions.elementToBeClickable(element));
		//StorageVariables.messageType="success";
		//stepMessageOnPage();
		
		
		}
		catch(Exception e)
		{
			StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
		    TakeSreenshot();
		}
	}

	public static void TakeSreenshot()
	{  
		waitForPageLoad();
		 
		try
		{
		File scrFile = ((TakesScreenshot)StorageVariables.driver).getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); // add S if you need milliseconds
		FileUtils.copyFile(scrFile, new File(StorageVariables.screenshotPath+ df.format(new Date()) + ".png"));
		}
		
		catch(Exception exc)
		{}
		
	}

	public static void highlightElement(){
		StorageVariables.highlightedElement=StorageVariables.driver.findElement(StorageVariables.by);
		  StorageVariables.jse = (JavascriptExecutor)StorageVariables.driver;
		  StorageVariables.jse.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", StorageVariables.highlightedElement);
		 }

	   public static boolean isElementPresent()
	     {
	    	 boolean elementPresence = false;
	    	 
	    	 try
	    	 {
	    	   StorageVariables.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	   StorageVariables.driver.findElement(StorageVariables.by);
	    	   elementPresence=true;
	    	   System.out.println("Element is present on the page.");
	    	 }
	    
	    	  catch(Exception e)
	    	  {
	    		elementPresence=false;  
	    		System.out.println("Element is not present\n");
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

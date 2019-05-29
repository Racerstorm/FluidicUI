package actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import Storage.StorageVariables;
import testStartup.LaunchBrowser;
public class CommonActions
{

	public static void Open() throws InterruptedException
	{
		try
		{
			StorageVariables.driver.navigate().to(StorageVariables.Value);
			StorageVariables.messageType="success";
			CustomActions.stepMessageOnPage();
			System.out.println("Open action was performed successfully.");
		}
		
		catch(Exception e)
		{
			StorageVariables.testcaseStatus=false;
			StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
			PageActions.TakeSreenshot();
		  System.out.println("Exception is "+e);
		}
		
	}
	
	public static void Click() throws InterruptedException
	{
     try
     {   
    	 LaunchBrowser.splitTarget(StorageVariables.Target);
    	 PageActions.waitforElement();
    	 PageActions.highlightElement();
    	 StorageVariables.driver.findElement(StorageVariables.by).click();
    	 StorageVariables.messageType="success";
         CustomActions.stepMessageOnPage();
    	 System.out.println("Click action was performed successfully.");
    	 
     }
          
     catch(Exception e)
     {   
    	 try
    	 {
    		 javascriptClick();
    	 }
    	 
    	 catch(Exception Ee) 
    	 {
    		StorageVariables.testcaseStatus=false;
    	    StorageVariables.messageType="error";
    	    CustomActions.stepMessageOnPage();
    	    PageActions.TakeSreenshot();
    	    // (JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", StorageVariables.by);
    	     System.out.println("Click action was not performed.");
    	 }
     }
    	 
     
     }
     
     public static void clickifPresent() throws InterruptedException
     {
    	 try
    	 {
    		 LaunchBrowser.splitTarget(StorageVariables.Target);
    		 if(PageActions.isElementPresent())
    		 {
    			 PageActions.highlightElement();
    			 StorageVariables.driver.findElement(StorageVariables.by).click();
    			 StorageVariables.messageType="success";
 				 CustomActions.stepMessageOnPage();
    			 System.out.println("Element was present on the page and click action was performed.");
    			 
    		 }
    		 
    		 else
    		 { 
    			    StorageVariables.messageType="warning";
    			    CustomActions.stepMessageOnPage();
    			    PageActions.TakeSreenshot();
    		    System.out.println("Element cannot be clicked as the element isn't present on the page. ");
    		 }
    		 
    	 }
    		 
    	 
    	 
    	 catch(Exception e)
    	 {
    		StorageVariables.messageType="error";
    		CustomActions.stepMessageOnPage();
		    PageActions.TakeSreenshot();
    	 }
    	 
    	 
     }     
	
	public static void javascriptClick() throws InterruptedException
	{   
		try
		{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		StorageVariables.element=StorageVariables.driver.findElement(StorageVariables.by);
		//jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		((JavascriptExecutor)StorageVariables.driver).executeScript("arguments[0].click();", StorageVariables.element);
		//jse.executeScript("arguments[0].click();", element);
		StorageVariables.messageType="success";
		CustomActions.stepMessageOnPage();
		}
		catch(Exception ex)
		{
			StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
		    PageActions.TakeSreenshot();
		}
	}
	
	public static void doubleClick()
	{
		try
		{
			LaunchBrowser.splitTarget(StorageVariables.Target);
			PageActions.highlightElement();
			StorageVariables.element=StorageVariables.driver.findElement(StorageVariables.by);
			((JavascriptExecutor) StorageVariables.driver).executeScript("var evt = document.createEvent('MouseEvents');"+ 
				    "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"+ 
				    "arguments[0].dispatchEvent(evt);", StorageVariables.element);
		}
		
		catch(Exception e)
		{
			PageActions.TakeSreenshot();
		}
	}
	
	public static void mouseOverandClick() throws InterruptedException
	{
		try
		{
			
		String hoverElement = StorageVariables.Target.split("\\#")[0]; 
		String elementtobeClicked = StorageVariables.Target.split("\\#")[1]; 
		
		LaunchBrowser.splitTarget(hoverElement);
		WebElement hover=StorageVariables.driver.findElement(StorageVariables.by);
		
		LaunchBrowser.splitTarget(elementtobeClicked);
		WebElement clickElement = StorageVariables.driver.findElement(StorageVariables.by); 
		
		Actions actions = new Actions(StorageVariables.driver);
		
		//WebElement menu = driver.findElement(by); 
		
		actions.moveToElement(hover).build().perform();
		//Thread.sleep(2000);
		actions.moveToElement(clickElement).click().build().perform();
		PageActions.waitForPageLoad();
		StorageVariables.messageType="success";
		CustomActions.stepMessageOnPage();
		System.out.println("Hovered on the element and performed the click action successfully.");
		}
		catch(Exception m)
		{   
			StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
		    PageActions.TakeSreenshot();
			System.out.println("Exception "+m+" was caught in the code.");
		}

	}

	
	public static void Type() throws InterruptedException
	{
		try
		{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		StorageVariables.driver.findElement(StorageVariables.by).clear();
		StorageVariables.driver.findElement(StorageVariables.by).sendKeys(StorageVariables.Value);
		StorageVariables.messageType="success";
		CustomActions.stepMessageOnPage();
		}
		catch(Exception e)
		{
			System.out.println("Unable to type. Exception caught :"+e);
			StorageVariables.messageType="error";
			CustomActions.stepMessageOnPage();
		    PageActions.TakeSreenshot();
		}
	}

	
	
}

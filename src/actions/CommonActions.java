package actions;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import Storage.StorageVariables;
import testStartup.LaunchBrowser;
import logger.Logger;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.support.ui.Select;  

public class CommonActions
{

	public static void Open() 
	{
		try
		{
			StorageVariables.stepLog="Launching the URL : "+StorageVariables.Value;
			StorageVariables.driver.navigate().to(StorageVariables.Value);
			if(StorageVariables.driver.getTitle().contains("503"))
			{
			Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed as the URL could not be opened.");

			}
		}
		
		catch(Exception e)
		{
			StorageVariables.testcaseStatus=false;
			Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e);
		}
		
	}
	
	public static void Click() 
	{
     try
     {   
    	 LaunchBrowser.splitTarget(StorageVariables.Target);
    	 PageActions.waitforElement();
    	 PageActions.highlightElement();
    	 StorageVariables.stepLog+="<br>Clicking the element :";
    	 StorageVariables.driver.findElement(StorageVariables.by).click();
    	 Logger.logsuccess("Click action was performed successfully.");	
    	     	 
     }
          
     catch(Exception e)
     {   
    	 try
    	 {
    		 ((JavascriptExecutor)StorageVariables.driver).executeScript("arguments[0].click();", StorageVariables.element);
    		 
    	 }
    	 
    	 catch(Exception Ee) 
    	 {
    		StorageVariables.testcaseStatus=false;
    		Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+Ee+" Click action was not performed.");

    	 }
     }
    	 
     
     }
     
     public static void clickifPresent() 
     {
    	 try
    	 {
    		 LaunchBrowser.splitTarget(StorageVariables.Target);
    		 if(PageActions.isElementPresent())
    		 {
    			 PageActions.highlightElement();
    			 StorageVariables.driver.findElement(StorageVariables.by).click();
    			 Logger.logsuccess("Element was present on the page and click action was performed.");		 
    		 }
    		 
    		 else
    		 { 
    		    Logger.logmessage("Element cannot be clicked as the element isn't present on the page. ");
    		 }
    		 
    	 }
    		
    	 catch(Exception e)
    	 {
    		 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Click action was not performed.");
    	 }
    	 
     }     
	
	public static void javascriptClick() 
	{   
		try
		{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		StorageVariables.element=StorageVariables.driver.findElement(StorageVariables.by);
		//jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		((JavascriptExecutor)StorageVariables.driver).executeScript("arguments[0].click();", StorageVariables.element);
		//jse.executeScript("arguments[0].click();", element);
		Logger.logsuccess("Element was found on the page and the click action was performed successfully");
		}
		catch(Exception e)
		{
			 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Click action was not performed.");
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
			Logger.logsuccess("Double click action was performed successfully.");
		}
		
		catch(Exception e)
		{
			 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Double click was not performed.");
		}
	}
	
	public static void mouseOverandClick()
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
		Logger.logsuccess("Hovered on the element and performed the click action successfully.");
		}
		catch(Exception m)
		{   
			 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+m+" The action was not performed.");
		}

	}

	public static void Type() 
	{
		try
		{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		StorageVariables.driver.findElement(StorageVariables.by).clear();
		StorageVariables.driver.findElement(StorageVariables.by).sendKeys(StorageVariables.Value);
		Logger.logsuccess("Located the element and performed the type action successfully");
		}
		catch(Exception e)
		{
			 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Type action was not performed.");
		}
	}
	
	public static void Select()
	{
		try
		{
			LaunchBrowser.splitTarget(StorageVariables.Target);
			
			String option = StorageVariables.Value.split("\\#")[0]; 
			String dropdownvalue = StorageVariables.Value.split("\\#")[1]; 
		
					
			
		//	LaunchBrowser.splitTarget(dropdownvalue);
			
			
			Select dropdown = new Select(StorageVariables.driver.findElement(StorageVariables.by));
		
				switch(option.toUpperCase()) 
				{
					case "VISIBLETEXT":
						dropdown.selectByVisibleText(dropdownvalue);
						break;
					
					case "INDEX": 
						int dropdownValue = Integer.parseInt(dropdownvalue);
						dropdown.selectByIndex(dropdownValue);
					break;
					
					case "VALUE":
						dropdown.selectByValue(dropdownvalue);
						break;
						
				}	
		}
				catch(Exception e)
				{
					System.out.println("Invalid action : The exception is : "+e);
				}
		
		  
	}
	
	public static void switchtoTab()
	{
		try
		{
		// Store all currently open tabs in tabs
		ArrayList<String> tabs = new ArrayList<String> (StorageVariables.driver.getWindowHandles());

		// Switch newly open Tab
		StorageVariables.driver.switchTo().window(tabs.get(Integer.parseInt(StorageVariables.Value)));
        Logger.logsuccess("Switched to tab : "+StorageVariables.Value+" successfully. ");
		//Close newly open tab after performing some operations.
		//StorageVariables.driver.close();

		//Switch to old(Parent) tab.
	   //StorageVariables.driver.switchTo().window(tabs.get(0));
		
	}
		catch(Exception e)
		{
			Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" SwitchToTab action was not performed.");
		}
		
	}
	
	public static void fileUpload()
	{
		try
		{
	/*	//File upload button
		String upload = StorageVariables.Target.split("\\#")[0]; 
		LaunchBrowser.splitTarget(upload);
		WebElement uploadButton=StorageVariables.driver.findElement(StorageVariables.by);
		
		//File path
		String file = StorageVariables.Target.split("\\#")[1]; 
		LaunchBrowser.splitTarget(file);
		WebElement filepath=StorageVariables.driver.findElement(StorageVariables.by);
		
		//Click on the upload button
		uploadButton.click();
		filepath.sendKeys(StorageVariables.Value); */
			
			StringSelection stringSelection = new StringSelection(StorageVariables.Value);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			
			Robot WindowsDriver = new Robot();
			WindowsDriver.keyPress(KeyEvent.VK_CONTROL);
			WindowsDriver.keyPress(KeyEvent.VK_V);
			WindowsDriver.keyRelease(KeyEvent.VK_V);
			WindowsDriver.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			WindowsDriver.keyPress(KeyEvent.VK_ENTER);
			WindowsDriver.keyRelease(KeyEvent.VK_ENTER);
			
			Logger.logsuccess("File upload was performed successfully.");
		}
		
		catch(Exception fileExc)
		{
			fileExc.printStackTrace();
			Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+fileExc+" FileUpload failed.");

		}
		//StorageVariables.driver.findElement(StorageVariables.by).sendKeys(StorageVariables.Value);
	}
	
	
	
	}

	


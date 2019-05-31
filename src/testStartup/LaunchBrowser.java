package testStartup;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Storage.StorageVariables;
import actions.CommonActions;
import actions.CustomActions;
import actions.PageActions;
import readCSV.ReadCSV;
 
public class LaunchBrowser {
 
public static void main(String[]  args) throws MalformedURLException, InterruptedException
{		
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
		
		if(StorageVariables.browser=="Chrome")
		{
		StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
	//	DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", StorageVariables.driverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
		//options.addExtensions(new File("C:\\Automation\\Extensions\\extension_1_7.crx"));
		StorageVariables.driver = new ChromeDriver(options);
		}
		
		else if(StorageVariables.browser=="Firefox")
		{
			StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", StorageVariables.driverPath);
			StorageVariables.driver=new FirefoxDriver();
		}
		
		else if(StorageVariables.browser=="IE")
		{
			StorageVariables.driverPath ="C:\\Automation\\WebDrivers\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver",StorageVariables.driverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("requireWindowFocus", true);  
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			capabilities.setCapability("ie.ensureCleanSession", true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			StorageVariables.driver=new InternetExplorerDriver(capabilities);
			
		}
        
        for(int i = 0;i<StorageVariables.actions.size();i++)
        {
        	StorageVariables.Action = StorageVariables.actions.get(i);
        	StorageVariables.Target = StorageVariables.targets.get(i);
        	StorageVariables.Value = StorageVariables.values.get(i);
            System.out.println("Executing Step : "+i);
        	gotoAction();
        	System.out.println("Completed Step : "+i+": "+StorageVariables.Action+"");
        }
        
        if(StorageVariables.testcaseStatus==true)
        {
        	System.out.println("Testcase passed");
        }
        else
        {
        	System.out.println("Testcase failed");
        }
       
            StorageVariables.driver.quit();
 	}	
	
public static void gotoAction() 
{
	
	try 
	{	
		switch(StorageVariables.Action.toUpperCase()) 
		{
			case "OPEN": CommonActions.Open();
				break;
			
			case "VERIFYPAGETITLE": PageActions.verifyPageTitle();
			break;
			
			case "CLICK": CommonActions.Click();
				break;
				
			case "JAVASCRIPTCLICK" : CommonActions.javascriptClick();
				break;
				
			case "DOUBLECLICK" : CommonActions.doubleClick();
			break;
				
			case "CLICKIFPRESENT" : CommonActions.clickifPresent();
				break;
				
			case "TYPE" : CommonActions.Type();
				break;
				
			case "WAITFORPAGELOAD" : PageActions.waitForPageLoad();
				break;
				
			case "WAITFORJSLOAD" : PageActions.waitForJStoLoad();
				break;
				
			case "WAITFORELEMENT" : PageActions.waitforElement();
				break;
				
			case "MOUSEOVERANDCLICK" : CommonActions.mouseOverandClick();
				break;
				
			case "CHECKALERT" : CustomActions.checkAlert();
				break;
				
			case "SWITCHTOIFRAME" : CustomActions.switchToIframe();
			    break;
			    
			case "SLEEP" : PageActions.Sleep();
		    break;
			    
			case "TAKESCREENSHOT" : PageActions.TakeSreenshot();
			break;
			
			case "GETELEMENTSTYLE": CustomActions.getElementStyle();
			break;
				
		}
	}
	catch(Exception e)
	{
		System.out.println("Invalid action : The exception is : "+e);
	}
}

public static void splitTarget(String Target)
	{	
		/* To Split and Store the Property type and Property Values from Target */
		 
          if (Target.toUpperCase().startsWith("ID"))
            {
        	  StorageVariables.TargetValue = Target.split("=")[1];
 
        	  StorageVariables.TargetType = "ID";
 
        	  StorageVariables.by = By.id(StorageVariables.TargetValue);
                
                
 
            }
            else if (Target.toUpperCase().startsWith("NAME"))
            {
            	StorageVariables.TargetValue = Target.split("=")[1];
 
            	StorageVariables.TargetType = "NAME";
 
            	StorageVariables.by = By.name(StorageVariables.TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("CSS"))
            {
            	StorageVariables.TargetValue = Target.split("=")[1];
 
            	StorageVariables.TargetType = "CSS";
 
            	StorageVariables.by = By.cssSelector(StorageVariables.TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("CLASS"))
            {
            	StorageVariables.TargetValue = Target.split("=")[1];
 
            	StorageVariables.TargetType = "CLASS";
 
            	StorageVariables.by = By.className(StorageVariables.TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("LINK"))
            {
            	StorageVariables.TargetValue = Target.split("=")[1];
 
            	StorageVariables.TargetType = "LINK";
 
            	StorageVariables.by = By.linkText(StorageVariables.TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("XPATH"))
            {
            	StorageVariables.TargetValue = Target.split("=")[1];
 
            	StorageVariables.TargetType = "XPATH";
 
            	StorageVariables.by = By.xpath(StorageVariables.TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("//"))
            {
            	StorageVariables.TargetValue = Target;
 
            	StorageVariables.TargetType = "XPATH";
 
            	StorageVariables.by = By.xpath(StorageVariables.TargetValue);
 
            }
            else if(Target.toUpperCase().startsWith(".//"))
            {
            	StorageVariables.TargetValue = Target;
 
            	StorageVariables.TargetType = "XPATH";
 
            	StorageVariables.by = By.xpath(StorageVariables.TargetValue);
            }
            else if(Target.toUpperCase().startsWith("(//"))
            {
            	StorageVariables.TargetValue = Target;
 
            	StorageVariables.TargetType = "XPATH";
 
            	StorageVariables.by = By.xpath(StorageVariables.TargetValue);
            }
            else
            {
                
            }
	}

}	
	    


	 
	    



	
	 


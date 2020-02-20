package testStartup;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Storage.StorageVariables;
import actions.CommonActions;
import actions.CustomActions;
import actions.PageActions;
 

public class LaunchBrowser {
 
@Parameters({"browser"})
@BeforeTest
public static void LaunchBrowser()
{
		
	try
	{
		
	
	   if(StorageVariables.browser.equalsIgnoreCase("Chrome"))
		{
		StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
	//	DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", StorageVariables.driverPath);
		ChromeOptions options = new ChromeOptions();
		
		//Launch Canary
		//options.setBinary("C:\\Users\\ust52622\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
		options.setExperimentalOption("useAutomationExtension", false); 
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		//options.addExtensions(new File("C:\\Automation\\Extensions\\extension_1_7.crx"));
		//Mobile Automation
		if(StorageVariables.mobileAutomation)
		{
			Map<String, Object> deviceMetrics = new HashMap<>();
			deviceMetrics.put("width", 1078);
			deviceMetrics.put("height", 924);
			deviceMetrics.put("pixelRatio", 3.0);
			Map<String, Object> mobileEmulation = new HashMap<>();
			mobileEmulation.put("deviceMetrics", deviceMetrics);
			mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0.0;" +"Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML,like Gecko) " +"Chrome/67.0.3396.99 Mobile Safari/537.36");
			options.setExperimentalOption("mobileEmulation", mobileEmulation);

		}
		StorageVariables.driver = new ChromeDriver(options);
		
		
		
		}
		
		else if(StorageVariables.browser.equalsIgnoreCase("Firefox"))
		{
			StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", StorageVariables.driverPath);
			StorageVariables.driver=new FirefoxDriver();
			StorageVariables.driver.manage().window().maximize();
		}
		/*
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
			
		}*/
		
		else if(StorageVariables.browser.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Automation\\WebDrivers\\MicrosoftWebDriver.exe");
			StorageVariables.driver= new EdgeDriver();
			StorageVariables.driver.manage().window().maximize();
		}
	}
	catch(Exception webdriver)
	{}
		
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
				
			case "GENERATERANDOMSTRING": CustomActions.generateRandomString();
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
			    
			case "SWITCHTODEFAULTCONTENT" : CustomActions.switchtoDefaultContent();
		    break;
			    
			case "SLEEP" : PageActions.Sleep();
		    break;
			    
			case "TAKESCREENSHOT" : PageActions.TakeSreenshot();
			break;
			
			case "GETELEMENTSTYLE": CustomActions.getElementStyle();
			break;
			
			case "OPENNEWTAB": CommonActions.OpenNewTab();
			break;
			
			case "SWITCHTOTAB": CommonActions.switchtoTab();
			break;
			
			case "FILEUPLOAD": CommonActions.fileUpload();
			break;
			
			case "VERIFYELEMENTANDSKIPSTEPS": CustomActions.verifyElementandSkipSteps();
			break;
			
			case "STORETEXT": PageActions.StoreText();
			break;
			
			case "VERIFYTEXT": PageActions.verifyText();
			break;
			
			case "VERIFYELEMENTPRESENT": PageActions.verifyElementPresent();
			break;
			
			case "VERIFYELEMENTNOTPRESENT": PageActions.verifyElementnotPresent();
			break;
			
			case "REFRESHPAGE": PageActions.RefreshPage();
			break;
			
			case "SELECT": CommonActions.Select();
			break;
			
			case "TYPECHARACTERS": CommonActions.TypeCharacters();
			break;
			
			case "TYPEACTION": CommonActions.typeAction();
			break;
			
			case "GOTOPREVIOUSPAGE": PageActions.GotoPreviousPage();
			break;		
			
			case "MODIFYXPATH": CustomActions.modifyxpath();
			break;	
			
			case "CLICKMULTIPLELINKS": CustomActions.ClickMultipleLinks();	
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
	    


	 
	    



	
	 


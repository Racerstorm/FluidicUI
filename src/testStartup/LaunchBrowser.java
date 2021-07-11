package testStartup;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Storage.StorageVariables;
import actions.CommonActions;
import actions.CustomActions;
import actions.PageActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
 

public class LaunchBrowser {
 
@Parameters({"browser"})
@BeforeTest
public static void LaunchBrowser()
{
		
	try
	{
		
	   if(!StorageVariables.mobileAutomation)
	   {
	   if(StorageVariables.browser.equalsIgnoreCase("Chrome"))
		{
			//StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
		//	DesiredCapabilities capability=DesiredCapabilities.chrome();
			//System.setProperty("webdriver.chrome.driver", StorageVariables.driverPath+"chromedriver");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			
			//Launch Canary
			//options.setBinary("C:\\Users\\ust52622\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.setExperimentalOption("useAutomationExtension", false); 
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			//options.addExtensions(new File("C:\\Automation\\Extensions\\extension_1_7.crx"));
			//Mobile Automation
			/*if(StorageVariables.mobileAutomation)
			{
				Map<String, Object> deviceMetrics = new HashMap<>();
				deviceMetrics.put("width", 1078);
				deviceMetrics.put("height", 924);
				deviceMetrics.put("pixelRatio", 3.0);
				Map<String, Object> mobileEmulation = new HashMap<>();
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0.0;" +"Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML,like Gecko) " +"Chrome/67.0.3396.99 Mobile Safari/537.36");
				options.setExperimentalOption("mobileEmulation", mobileEmulation);
	
			}*/
			StorageVariables.driver = new ChromeDriver(options);	
		}
		
		else if(StorageVariables.browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\geckodriver.exe";
			//System.setProperty("webdriver.gecko.driver", StorageVariables.driverPath);
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
			WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver","C:\\Automation\\WebDrivers\\MicrosoftWebDriver.exe");
			StorageVariables.driver= new EdgeDriver();
			StorageVariables.driver.manage().window().maximize();
		}
	   
		else if(StorageVariables.browser.equalsIgnoreCase("Safari"))
		{
			StorageVariables.driver = new SafariDriver();
			StorageVariables.driver.manage().window().maximize();
		}
	}
	   if(StorageVariables.mobileAutomation)
	   {
		// Create an object for Desired Capabilities
/*			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Name of mobile web browser to automate. �Safari� for iOS and �Chrome�
			// or �Browser� for Android
			capabilities.setCapability("browserName", StorageVariables.browser);

			// The kind of mobile device or emulator to use - iPad Simulator, iPhone
			// Retina 4-inch, Android Emulator, Galaxy S4 etc
			//capabilities.setCapability("deviceName", "realme X2 Pro");
			capabilities.setCapability("udid", "emulator-5554");
		//	capabilities.setCapability("deviceId", "192.168.1.7:4444");
		//	capabilities.setCapability("deviceId", "192.168.1.4:5555");

			// Which mobile OS platform to use - iOS, Android, or FirefoxOS
			capabilities.setCapability("platformName", "Android");

			// Java package of the Android app you want to run- Ex:
			// com.example.android.myApp
			//capabilities.setCapability("appPackage", "com.android.chrome");

			// Activity name for the Android activity you want to launch from your
			// package
			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			capabilities.setCapability(MobileCapabilityType.NO_RESET,"true");


			// Initialize the driver object with the URL to Appium Server and
			// passing the capabilities
			StorageVariables.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
		//	wait = new WebDriverWait(StorageVariables.driver, 5);
		   */
		   
	   }
	} 
		
	catch(Exception webdriver)
	{
		System.out.print("Could not initialize the driver");
		System.exit(0); 
	}
		
}       
        

	
public static void gotoAction() 
{
	
	try 
	{	
		switch(StorageVariables.Action.trim().toUpperCase()) 
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
			
			case "TYPEANDENTER": CommonActions.typeandEnter();
			break;
			
			case "SWIPEVERTICAL" : PageActions.swipeVertical();
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
	    


	 
	    



	
	 


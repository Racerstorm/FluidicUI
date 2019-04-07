import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.ExecuteScript;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import readCSV.ReadCSV;
import Storage.StorageVariables;
 
public class LaunchBrowser {
 
	public static void main(String[]  args) throws MalformedURLException, InterruptedException{
 
 		/*String URL = "http://www.google.com";
 		String Node = "http://localhost:4444/wd/hub";*/
		try
		{
			  ReadCSV.readfromCSV();
	    }
		
		catch (FileNotFoundException e) 
		    {
		 e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		StorageVariables.driverPath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
		StorageVariables.screenshotPath="C:\\Users\\Chitrangadans\\Pictures\\Screenshots\\File";
	//	DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", StorageVariables.driverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
;		StorageVariables.driver = new ChromeDriver(options);
 		
        
        for(int i = 0;i<StorageVariables.actions.size();i++)
        {
        	StorageVariables.Action = StorageVariables.actions.get(i);
        	StorageVariables.Target = StorageVariables.targets.get(i);
        	StorageVariables.Value = StorageVariables.values.get(i);
            System.out.println("Executing Step : "+i);
        	gotoAction();
        	System.out.println("Completed Step : "+i+": "+StorageVariables.Action+"");
        
     
        }
        		
        TakeSreenshot();
        Thread.sleep(3000);
        StorageVariables.driver.quit();
 	}	
	
public static void gotoAction() 
{
	
	try {
		
		
		switch(StorageVariables.Action.toUpperCase()) 
		{
			case "OPEN": Open();
				break;
			
			case "CLICK": Click();
				break;
				
			case "JAVASCRIPTCLICK" : javascriptClick();
				break;
				
			case "CLICKIFPRESENT" : clickifPresent();
				break;
				
			case "TYPE" : Type();
				break;
				
			case "WAITFORPAGELOAD" : waitForPageLoad();
				break;
				
			case "WAITFORJSLOAD" : waitForJStoLoad();
				break;
				
			case "WAITFORELEMENT" : waitforElement();
				break;
				
			case "MOUSEOVERANDCLICK" : mouseOverandClick();
				break;
				
			case "CHECKALERT" : checkAlert();
				break;
				
			case "TAKESCREENSHOT" : TakeSreenshot();
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

	public static void Open()
	{
		try
		{
			StorageVariables.driver.navigate().to(StorageVariables.Value);
			System.out.println("Open action was performed successfully.");
		}
		
		catch(Exception e)
		{
		  System.out.println("Exception is "+e);
		}
		
	}
	
	public static void Click()
	{
     try
     {   
    	 splitTarget(StorageVariables.Target);
    	 waitforElement();
    	 StorageVariables.driver.findElement(StorageVariables.by).click();
    	 System.out.println("Click action was performed successfully.");
     }
          
     catch(Exception e)
     {
    	// (JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", StorageVariables.by);
    	 System.out.println("Click action was not performed.");
     }
    	 
     
     }
     
     public static void clickifPresent()
     {
    	 try
    	 {
    		 splitTarget(StorageVariables.Target);
    		 if(isElementPresent())
    		 {
    			 StorageVariables.driver.findElement(StorageVariables.by).click();
    			 System.out.println("Element was present on the page and click action was performed.");
    		 }
    		 
    		 else
    		 {
    		    System.out.println("Element cannot be clicked as the element isn't present on the page. ");
    		 }
    		 
    	 }
    		 
    	 
    	 
    	 catch(Exception e)
    	 {
    	 }
    	 
    	 
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
     
	
	public static void javascriptClick()
	{   
		try
		{
		splitTarget(StorageVariables.Target);
		StorageVariables.element=StorageVariables.driver.findElement(StorageVariables.by);
		//jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		((JavascriptExecutor)StorageVariables.driver).executeScript("arguments[0].click();", StorageVariables.element);
		//jse.executeScript("arguments[0].click();", element);
		}
		catch(Exception ex)
		{}
	}
	
	public static void Type()
	{
		try
		{
		splitTarget(StorageVariables.Target);
		StorageVariables.driver.findElement(StorageVariables.by).clear();
		StorageVariables.driver.findElement(StorageVariables.by).sendKeys(StorageVariables.Value);
		}
		catch(Exception e)
		{
			System.out.println("Unable to type. Exception caught :"+e);
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

	
	public static void waitforElement()
	{
		splitTarget(StorageVariables.Target);
		
	try
	 {
		WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StorageVariables.by));
		//element= wait.until(ExpectedConditions.elementToBeClickable(element));
		
		}
		catch(Exception w)
		{}
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
	
	public static void mouseOverandClick()
	{
		try
		{
			
		String hoverElement = StorageVariables.Target.split("\\#")[0]; 
		String elementtobeClicked = StorageVariables.Target.split("\\#")[1]; 
		
		splitTarget(hoverElement);
		WebElement hover=StorageVariables.driver.findElement(StorageVariables.by);
		
		splitTarget(elementtobeClicked);
		WebElement clickElement = StorageVariables.driver.findElement(StorageVariables.by); 
		
		Actions actions = new Actions(StorageVariables.driver);
		
		//WebElement menu = driver.findElement(by); 
		
		actions.moveToElement(hover).build().perform();
		//Thread.sleep(2000);
		actions.moveToElement(clickElement).click().build().perform();
		System.out.println("Hovered on the element and performed the click action successfully.");
		
		}
		catch(Exception m)
		{ 
			System.out.println("Exception "+m+" was caught in the code.");
		}

	}

	public static void checkAlert() {
	    try {;
	        WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 2);
	        wait.until(ExpectedConditions.elementToBeClickable(StorageVariables.by));
	        Alert alert = StorageVariables.driver.switchTo().alert();
	        alert.dismiss();
	    } catch (Exception e) {
	        //exception handling
	    }
	}
}
	
	 


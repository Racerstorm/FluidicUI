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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
 
public class LaunchBrowser {
	public static WebDriver driver;
	public static String Target="";
	public static String locatorValue="";
	public static String locatorType="";
	public static String inputValue = "";
	public static String locator = "";
	public static WebElement element = null;
	public static By by = null;
	public static JavascriptExecutor jse = (JavascriptExecutor)driver;
	
 
	public static void main(String[]  args) throws MalformedURLException, InterruptedException{
 
 		/*String URL = "http://www.google.com";
 		String Node = "http://localhost:4444/wd/hub";*/
		String url = "https://www.google.com/";
		String chromedriverpath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
		DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
 		
        //driver.navigate().to("https://development-webstore-unilever.demandware.net/s/UNI-T2-NA/Home");
        driver.navigate().to("https://development-webstore-unilever.demandware.net/s/UNI-T2-NA/en/us/account");
        waitForJStoLoad();
        //Thread.sleep(10000);
        locator = "//a[contains(text(),'Full Price')]";
        waitforElement();
        javascriptClick();
        //Click();

        //Thread.sleep(4000);
      //  locator = "//a[contains(text(),'Full Price')]";
       // checkAlert();
        //waitforElement();
       // Click();
        //Thread.sleep(10000);
       /* waitForPageLoad();
        locator = "//img[contains(@id,\"popup-subcription\")]";
        waitforElement();
        Thread.sleep(20000);
        javascriptClick();
        locator = "//a[@class='user-login']";
        Click();*/
        locator="id=dwfrm_login_username";
        inputValue="prasad.mani@unilever.com";
        Type();
        locator="id=dwfrm_login_password";
        inputValue="Prasadman1";
        Type();
        locator="//button[@class='button btn'][@value='Login']";
        Click();
        checkAlert();
        locator = "//a[@class='level-1 subcat'][contains(text(),'Teawares')]|//ul[@class='column col-1']/li//a[contains(text(),'Tea For One')]";
        mouseOver();
        locator = "//a[contains(text(),'Full Price')]";
        Click();
        TakeSreenshot();
        Thread.sleep(3000);
 		driver.quit();
 	}	
	
	public static void splitTarget(String Target)
	{	
		/* To Split and Store the Property type and Property Values from Target */
		 
          if (Target.toUpperCase().startsWith("ID"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "ID";
 
                by = By.id(locatorValue);
                
                
 
            }
            else if (Target.toUpperCase().startsWith("NAME"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "NAME";
 
                by = By.name(locatorValue);
 
            }
            else if (Target.toUpperCase().startsWith("CSS"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "CSS";
 
                by = By.cssSelector(locatorValue);
 
            }
            else if (Target.toUpperCase().startsWith("CLASS"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "CLASS";
 
                by = By.className(locatorValue);
 
            }
            else if (Target.toUpperCase().startsWith("LINK"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "LINK";
 
                by = By.linkText(locatorValue);
 
            }
            else if (Target.toUpperCase().startsWith("XPATH"))
            {
                locatorValue = Target.split("=")[1];
 
                locatorType = "XPATH";
 
                by = By.xpath(locatorValue);
 
            }
            else if (Target.toUpperCase().startsWith("//"))
            {
                locatorValue = Target;
 
                locatorType = "XPATH";
 
                by = By.xpath(locatorValue);
 
            }
            else if(Target.toUpperCase().startsWith(".//"))
            {
                locatorValue = Target;
 
                locatorType = "XPATH";
 
                by = By.xpath(locatorValue);
            }
            else if(Target.toUpperCase().startsWith("(//"))
            {
                locatorValue = Target;
 
                locatorType = "XPATH";
 
                by = By.xpath(locatorValue);
            }
            else
            {
                
            }
	}

	public static void Click()
	{
     try
     {   
    	 splitTarget(locator);
    	 waitforElement();
       driver.findElement(by).click();
      
     
     }
     
     catch(Exception e)
     {
       
    	
    	// (JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", Storage.webElement);
     }
     }
	
	public static void javascriptClick()
	{   
		try
		{
		splitTarget(locator);
		element=driver.findElement(by);
		//jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		//jse.executeScript("arguments[0].click();", element);
		}
		catch(Exception ex)
		{}
	}
	
	public static void Type()
	{
		splitTarget(locator);
		driver.findElement(by).sendKeys(inputValue);
	}
	
	public static void waitForPageLoad()
	{
		try
		{
			String pageLoadState = ((JavascriptExecutor)driver).executeScript("if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}").toString();

			while(true)
			{
				if(pageLoadState.toUpperCase().equals("COMPLETE") || pageLoadState.toUpperCase().equals("LOADED"))
				{
					//ResultLogger.log("Page Load State: "+pageLoadState,ISSTEPACTION.True,RESULT.PASS);
					System.out.println("Page Load State: "+pageLoadState);

					break;
				}
				pageLoadState = ((JavascriptExecutor)driver).executeScript("if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}").toString();

			}
		}
		catch(Exception ex)
		{

		}
		
	}
	
	public static boolean waitForJStoLoad() {

	    WebDriverWait wait = new WebDriverWait(driver, 30);

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
		splitTarget(locator);
		element = driver.findElement(by);
	try
	 {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
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
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); // add S if you need milliseconds
		FileUtils.copyFile(scrFile, new File("C:\\Users\\Chitrangadans\\Pictures\\Screenshots\\File"+ df.format(new Date()) + ".png"));
		}
		
		catch(Exception exc)
		{}
		
	}
	
	public static void mouseOver()
	{
		try
		{
			
		String hoverElement = locator.split("\\|")[0]; 
		String elementtobeClicked = locator.split("\\|")[1]; 
		
		splitTarget(hoverElement);
		WebElement hover=driver.findElement(by);
		
		splitTarget(elementtobeClicked);
		WebElement clickElement = driver.findElement(by); 
		
		Actions actions = new Actions(driver);
		
		//WebElement menu = driver.findElement(by); 
		
		actions.moveToElement(hover).build().perform();
		//Thread.sleep(2000);
		actions.moveToElement(clickElement).click().build().perform();
		System.out.println("Done.");
		
		}
		catch(Exception m)
		{ 
			System.out.println("Exception "+m+" was caught in the code.");
		}

	}

	public static void checkAlert() {
	    try {;
	        WebDriverWait wait = new WebDriverWait(driver, 2);
	        wait.until(ExpectedConditions.elementToBeClickable(by));
	        Alert alert = driver.switchTo().alert();
	        alert.dismiss();
	    } catch (Exception e) {
	        //exception handling
	    }
	}
}
	
	 


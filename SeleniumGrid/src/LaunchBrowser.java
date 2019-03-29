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
 
public class LaunchBrowser {
	public static WebDriver driver;
	public static String Action="";
	public static String Target="";
	public static String TargetValue="";
	public static String TargetType="";
	public static String Value = "";
	public static WebElement element = null;
	public static By by = null;
	public static JavascriptExecutor jse = (JavascriptExecutor)driver;
	
 
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
	
		String chromedriverpath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
		DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
 		
         /*
        driver.navigate().to("https://www.t2tea.com/en/us/Home");
        Target = "//a[@class='user-login']";
        Click();
        Target="id=dwfrm_login_username";
        Value="prasad.mani@unilever.com";
        Type();
        Target="id=dwfrm_login_password";
        Value="Prasadman1";
        Type();
        Target="//button[@class='button btn'][@value='Login']";
        Click();
        */
        for(int i = 0;i<ReadCSV.actions.size();i++)
        {
            Action = ReadCSV.actions.get(i);
        	Target = ReadCSV.target.get(i);
        	Value = ReadCSV.value.get(i);
        
        	gotoAction();
        
        	
       // driver.navigate().to("https://www.sheamoisture.com/");
       // Target = "//i[contains(text(),'Maybe Later')]";
     //   Thread.sleep(10000);
     //   driver.switchTo().frame("ju_iframe_242603");
     ////   Thread.sleep(2000);
      //  Target = "//span/i";
     //   waitforElement();
        //javascriptClick();
     //   Click();
       /* Target = "//a/span[contains(text(),'Bath & Body')]";
        Click();
        Target = "//span[@class='action primary quickview-button']";
        Click();
        driver.navigate().to("https://www.nubianheritage.com/");
        waitForJStoLoad();
        Target = "//a[@class='nubian-menu']";
        Click();
        Target = "//span[contains(text(),'Deodorants')]";
        Click();
        Target = "//a[@class='view-product']";
        Click();*/
        
        
        //Thread.sleep(4000);
      //  Target = "//a[contains(text(),'Full Price')]";
       // checkAlert();
        //waitforElement();
       // Click();
        //Thread.sleep(10000);
       /* waitForPageLoad();
        Target = "//img[contains(@id,\"popup-subcription\")]";
        waitforElement();
        Thread.sleep(20000);
        javascriptClick();
        Target = "//a[@class='user-login']";
        Click();*/
     /*   Target="id=dwfrm_login_username";
        Value="prasad.mani@unilever.com";
        Type();
        Target="id=dwfrm_login_password";
        Value="Prasadman1";
        Type();
        Target="//button[@class='button btn'][@value='Login']";
        Click();
        checkAlert();
        Target = "//a[@class='level-1 subcat'][contains(text(),'Teawares')]|//ul[@class='column col-1']/li//a[contains(text(),'Tea For One')]";
        mouseOver();
        Target = "//a[contains(text(),'Full Price')]";
        Click(); */
        }
        		
        TakeSreenshot();
        Thread.sleep(3000);
 		driver.quit();
 	}	
	
public static void gotoAction() 
{
	
	try {
		//Implement Action method invoke using reflection- TBD_P2
		
		switch(Action.toUpperCase()) 
		{
			case "OPEN": Open();
				break;
			
			case "CLICK": Click();
				break;
				
			case "JAVASCRIPTCLICK" : javascriptClick();
				break;
				
			case "TYPE" : Type();
				break;
				
			case "WAITFORPAGELOAD" : waitForPageLoad();
				break;
				
			case "WAITFORJSLOAD" : waitForJStoLoad();
				break;
				
			case "WAITFORELEMENT" : waitforElement();
				break;
				
			case "MOUSEOVER" : mouseOver();
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
                TargetValue = Target.split("=")[1];
 
                TargetType = "ID";
 
                by = By.id(TargetValue);
                
                
 
            }
            else if (Target.toUpperCase().startsWith("NAME"))
            {
                TargetValue = Target.split("=")[1];
 
                TargetType = "NAME";
 
                by = By.name(TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("CSS"))
            {
                TargetValue = Target.split("=")[1];
 
                TargetType = "CSS";
 
                by = By.cssSelector(TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("CLASS"))
            {
                TargetValue = Target.split("=")[1];
 
                TargetType = "CLASS";
 
                by = By.className(TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("LINK"))
            {
                TargetValue = Target.split("=")[1];
 
                TargetType = "LINK";
 
                by = By.linkText(TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("XPATH"))
            {
                TargetValue = Target.split("=")[1];
 
                TargetType = "XPATH";
 
                by = By.xpath(TargetValue);
 
            }
            else if (Target.toUpperCase().startsWith("//"))
            {
                TargetValue = Target;
 
                TargetType = "XPATH";
 
                by = By.xpath(TargetValue);
 
            }
            else if(Target.toUpperCase().startsWith(".//"))
            {
                TargetValue = Target;
 
                TargetType = "XPATH";
 
                by = By.xpath(TargetValue);
            }
            else if(Target.toUpperCase().startsWith("(//"))
            {
                TargetValue = Target;
 
                TargetType = "XPATH";
 
                by = By.xpath(TargetValue);
            }
            else
            {
                
            }
	}

	public static void Open()
	{
		try
		{
			driver.navigate().to(Value);
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
    	 splitTarget(Target);
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
		splitTarget(Target);
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
		splitTarget(Target);
		driver.findElement(by).sendKeys(Value);
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
		splitTarget(Target);
		
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
			
		String hoverElement = Target.split("\\|")[0]; 
		String elementtobeClicked = Target.split("\\|")[1]; 
		
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
	
	 


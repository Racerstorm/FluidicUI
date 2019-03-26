import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class Browser extends Thread {
	
	public WebDriver driver;
	
	public String browsername ="";
	
	//public String URL = "http://WWW.DemoQA.com";
	
	public String Node = "http://10.4.65.128:4444/wd/hub";
	
	Browser (String browserName)
	{
		this.browsername=browserName;
	}
	
	public void run()
	{
		try 
		{
			launchBrowser();
		
			Open();
			Type();
			Click();
			
			Thread.sleep(5000);
			
			CloseBrowser();
		}
		
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void launchBrowser() throws MalformedURLException
	{
		DesiredCapabilities capability;
		if(this.browsername.equalsIgnoreCase("Firefox"))
		{
			capability=DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", "C:\\Automation\\WebDrivers\\geckodriver.exe");
			
			//driver=new FirefoxDriver();
			driver = new RemoteWebDriver(new URL(Node),capability);
		}
		
		else if(this.browsername.equalsIgnoreCase("IE"))
		{
			capability=DesiredCapabilities.internetExplorer();
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//InternetExplorerOptions capabilities = new InternetExplorerOptions(capabilities);
			
			System.setProperty("webdriver.ie.driver", "c:\\Automation\\WebDrivers\\IEDriverServer.exe");
			
			//driver=new InternetExplorerDriver(capabilities1);
			
			driver = new RemoteWebDriver(new URL(Node),capability);
		}
		
		else if(this.browsername.equalsIgnoreCase("CHROME"))
		{
			capability=DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", "C:\\Automation\\WebDrivers\\chromedriver.exe");
			
			driver = new RemoteWebDriver(new URL(Node),capability);
			//driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.MINUTES);
	}
	
	public void Open()
	{
		driver.navigate().to("https://login.salesforce.com/");
	}
	
	public void Type()
	{
		driver.findElement(By.id("username")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("password")).sendKeys("1234");
		TakeScreenshot();
	}
	
	public void Click()
	{
		driver.findElement(By.id("Login")).click();
		TakeScreenshot();
	}
	
	public  void TakeScreenshot()
	{
		try
		{
			String saveLocation ="Warning_"+this.browsername;
			Calendar calobj = Calendar.getInstance();

			DateFormat df = new SimpleDateFormat("dd-MM-yy");

			Path pathString = Paths.get("D:\\Screenshots");

			File file= new File(pathString.toString());

			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			Path filePath = Paths.get(pathString.toString(), saveLocation + "_" + (calobj.getTimeInMillis())+ ".png" );

			FileUtils.copyFile(screenshot, new File(filePath.toString()));
			
			String scrLocation=filePath.toString();
			//scrLocation=scrLocation.replace(Storage.Custom_ScreenCapture_Path, "");
			//scrLocation=scrLocation.replace("\\", "/");
			//Storage.stepLog.stepscrLocation.add(scrLocation);
			//Storage.stepLog.stepscrLocation.add(Storage.stepNumber, scrLocation);
		}
		catch(Exception ex)
		{
			//ResultLogger.log("Exception", ISSTEPACTION.False, RESULT.EXCEPTION);
		}

	}
	
	public static void CreateDirectory(String path)
	{
		

		
	}

	public void CloseBrowser()
	{
		driver.close();	
		driver.quit();
		
	}
}

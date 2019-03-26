import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ThreadClass 
{
	
	public static void main(String[] args) throws InterruptedException, IOException
    {
		String chromedriverpath = "C:\\Automation\\WebDrivers\\chromedriver.exe";
		String geckodriverpath = "C:\\Automation\\WebDrivers\\geckodriver.exe";
		String Hub_Path = "D:\\eclipse-workspace\\SeleniumHub.bat";
		//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		DesiredCapabilities capabilities;
		capabilities=DesiredCapabilities.firefox();
		System.setProperty("webdriver.gecko.driver", geckodriverpath);
		
		DesiredCapabilities capability=DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		Runtime.getRuntime().exec("cmd.exe /c start D:\\eclipse-workspace\\SeleniumHub.bat");
	    //Runtime.getRuntime().exec("cmd.exe /c start D:\\eclipse-workspace\\SeleniumHub.bat");
		//Process p1=Runtime.getRuntime().exec(new String[]{"D:\\eclipse-workspace\\SeleniumHub.bat"});
		//Process p = Runtime.getRuntime().exec("D:\\eclipse-workspace\\SeleniumHub.bat");
		
		Process p2=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "D:\\eclipse-workspace\\SeleniumNode_Remote.bat"});
		Thread.sleep(20000);
		Process p3=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "D:\\eclipse-workspace\\FirefoxRemoteNode.bat"});
		Thread.sleep(20000);
		//options.addArguments("--start-fullscreen");
		//WebDriver driver = new ChromeDriver(options);
		
		WebDriver driver = new RemoteWebDriver(new URL("http://10.4.65.149:4444/wd/hub"),capability);
		driver = new RemoteWebDriver(new URL("http://10.4.65.149:4444/wd/hub"),capabilities);
		//((IJavaScriptExecutor)driver).ExecuteScript("window.resizeTo(1024, 768);");
		
		Thread.sleep(1000);
		
		driver.get("https://stackoverflow.com/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-label='ask new question']/a")).click();
		Thread.sleep(1000);
	//	File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 		//driver.quit();
 		Thread.sleep(10000);
 		Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "D:\\eclipse-workspace\\Selenium_KillRemoteNode.bat"});
 		Thread.sleep(10000);
 		Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "D:\\eclipse-workspace\\Selenium_KillNode.bat"});
		
//		List<String> myList = new ArrayList<String>();
//		
//		
//		myList.add("CHROME");
//		myList.add("IE");
//		myList.add("Firefox");
//		
//		for (int i=0; i<myList.size(); i++)
//        {
//			Browser b=new Browser(myList.get(i).toString());
//			
//			b.start();
//        }
    }
}

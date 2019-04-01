package Storage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StorageVariables {

	public static WebDriver driver;
	public static String driverPath= "";
	public static JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	public static String Action="";
	public static String Target="";
	public static String TargetValue="";
	public static String TargetType="";
	public static String Value = "";
	
	public static ArrayList<String> actions = new ArrayList<String>();
	public static ArrayList<String> targets = new ArrayList<String>();
	public static ArrayList<String> values = new ArrayList<String>();
	public static String csvLocation = "";
	public static char delimiter;
	
	public static WebElement element = null;
	public static By by = null;
	public static String screenshotPath="";
	

}

package Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class StorageVariables {

	public static WebDriver driver;
	public static String driverPath= "";
	public static String browser= "";
	public static JavascriptExecutor jse;
	public static String Action="";
	public static String Target="";
	public static String TargetValue="";
	public static String TargetType="";
	public static String Value = "";
	public static int stepNumber;
	public static ArrayList<String> actions = new ArrayList<String>();
	public static ArrayList<String> targets = new ArrayList<String>();
	public static ArrayList<String> values = new ArrayList<String>();
	public static String file = "";
	public static String csvLocation = "";
	public static char delimiter;
	public static WebElement highlightedElement=null;
	
	
	public static WebElement element = null;
	public static By by = null;
	public static String tempStorage="";
	public static String screenshotPath="";
	public static String htmlreportPath="";
	public static String screenshotFile="";
	
	public static String stepLog="";
	public static List<String> stepsLogs= new ArrayList<String>();
    public static int warningCounter=0;
	
	//Variables for Runtime Step information using Growl.js
	public static String messageType="";
	public static String stepMessage ="";
	public enum STEPRESULT {PASSED , FAILED , WARNING};
	public static STEPRESULT stepResult; 
	public enum TCRESULT {PASSED , FAILED};
	public static TCRESULT testcaseResult;
	
	public static boolean testcaseStatus=true;
	
	public static ExtentTest logger=null;
	public static ExtentReports report=new ExtentReports(htmlreportPath, true);
	 public static ExtentTest test=new ExtentTest("", "");
	 

}

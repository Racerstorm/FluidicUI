package Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public static HashMap<String,String> StoredVariables=new HashMap<String,String>();    
	public static String file = "";
	public static String inputFile="";
	public static String testdataSource="";
	public static String csvLocation = "";
	public static char delimiter;
	public static String localVar="";
	public static WebElement highlightedElement=null;
	public static String testdataPath;
	public static String testcaseSheet="";
	public static String OS="";	
	
	public static WebElement element = null;
	public static By by = null;
	public static String tempStorage="";
	public static String screenshotPath="";
	public static String htmlreportPath="";
	public static String screenshotFile="";
	public static boolean ParallelExecution;
	public static boolean mobileAutomation=false;
	
	public static long startTime;
	
	public static String stepLog="";
	public static List<String> stepsLogs= new ArrayList<String>();
    public static int warningCounter=0;
	
	//Variables for Runtime Step information using Growl.js
	public static String messageType="";
	public static String stepMessage ="";
	public enum STEPRESULT {PASSED , FAILED , WARNING};
	public static STEPRESULT stepResult=STEPRESULT.PASSED; 
	public enum TCRESULT {PASSED , FAILED};
	public static TCRESULT testcaseResult;
	
	public static boolean testcaseStatus=true;
	
	public static ExtentTest logger=null;
	public static ExtentReports report=new ExtentReports(htmlreportPath, true);
	 public static ExtentTest test=new ExtentTest("", "");
	 public static Properties prop;
	 
	 
	 
	 

}

package actions;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Storage.StorageVariables;
import logger.Logger;
import testStartup.LaunchBrowser;
import actions.PageActions;

public class CustomActions 
{

	public static void checkAlert() 
	{
	   try {
		   
	        WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 2);
	        wait.until(ExpectedConditions.elementToBeClickable(StorageVariables.by));
	        Alert alert = StorageVariables.driver.switchTo().alert();
	        alert.dismiss();
	        Logger.logsuccess("Alert handled on the page.");
	        
	       }
	   catch (Exception e)
	       {
		   Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e);
	       }
	}
	
	public static void getElementStyle()
	{  
		LaunchBrowser.splitTarget(StorageVariables.Target);
				 
		//Get element background-color
	    WebElement el = StorageVariables.driver.findElement(StorageVariables.by);
	    
	    //String styles=StorageVariables.driver.findElement(StorageVariables.by).getAttribute("style");
	    
	    JavascriptExecutor executor = (JavascriptExecutor)StorageVariables.driver;
	    String script = "var s = '';" +
	                    "var o = getComputedStyle(arguments[0]);" +
	                    "for(var i = 0; i < o.length; i++){" +
	                    "s+=o[i] + ':' + o.getPropertyValue(o[i])+'\\n';}" + 
	                    "return s;";
	    String styleVal = "let compStyles = window.getComputedStyle(arguments[0]);\r\n" + 
	    		"var allowedPatterns = [ \r\n" + 
	    		"  \"font-family\", \r\n" + 
	    		"  \"font-size\",\r\n" + 
	    		"  \"line-height\",\r\n" + 
	    		"  \"text-decoration\",\r\n" + 
	    		"  \"word-spacing\",\r\n" + 
	    		"  \"background-color\",\r\n" + 
	    		"  \"background-image\",\r\n" + 
	    		"  \"background-position\",\r\n" + 
	    		"  \"background-repeat\",\r\n" + 
	    		"  \"color\",\r\n" + 
	    		"  \"height\",\r\n" + 
	    		"  \"width\",\r\n" + 
	    		"  \"padding\",\r\n" + 
	    		"  \"display\",\r\n" + 
	    		"  \"transition\",\r\n" + 
	    		"  \"transform\",\r\n" + 
	    		"  \"outline\"\r\n" + 
	    		"];\r\n" + 
	    		"\r\n" + 
	    		"var allowedRegexStr = '^(?:' + \r\n" + 
	    		"  allowedPatterns.\r\n" + 
	    		"    join('|').\r\n" + 
	    		"    replace(/\\./g, '\\\\.').\r\n" + 
	    		"    replace(/X/g, '\\d+') + \r\n" + 
	    		"  ')$';\r\n" + 
	    		"\r\n" + 
	    		" var allowedRegexp = new RegExp(allowedRegexStr);\r\n" + 
	    		"var filtered = [];\r\n" + 
	    		"for (let k = 0; k < Object.values(compStyles).length; ++k) {\r\n" + 
	    		"    let item = Object.values(compStyles)[k];\r\n" + 
	    		"    if (item.match(allowedRegexp))\r\n" + 
	    		"filtered.push(item); \r\n" + 
	    		"}\r\n" + 
	    		"var val = [];\r\n" + 
	    		"var len = filtered.length;\r\n" + 
	    		"for (var i=0; i< len; ++i) {\r\n" + 
	    		"let k = compStyles.getPropertyValue(filtered[i]);\r\n" + 
	    		"val.push(k);\r\n" + 
	    		"}\r\n" + 
	    		"var resultArray = [];\r\n" + 
	    		"for(var i=0; i<len; i++){\r\n" + 
	    		"  var obj = {};\r\n" + 
	    		"     obj[filtered[i]] = val[i];\r\n" + 
	    		"\r\n" + 
	    		"   resultArray.push(obj);\r\n" + 
	    		"}\r\n" + 
	    		"return resultArray; ";
	    System.out.println(executor.executeScript(styleVal, el));

	   // System.out.println(executor.executeScript(script, el));
	    
	    String contents = (String) ((JavascriptExecutor) StorageVariables.driver)
	            .executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');", el);
	    System.out.println("Element background color in RGB : "+contents);
	    
	    //Get element color
	    String color = (String) ((JavascriptExecutor) StorageVariables.driver)
	            .executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('color');", el);
	    int red = Integer.parseInt(color.split(",")[0].replaceAll("[^\\d.]", "")); 
	    int green = Integer.parseInt(color.split(",")[1].replaceAll("[^\\d.]", "")); 
	    int blue = Integer.parseInt(color.split(",")[2].replaceAll("[^\\d.]", "")); 
	    String hex = String.format("#%02x%02x%02x", red, green, blue);	    	       
	    System.out.println("Element color in Hex : " +hex);
	    
	    //Get element font family
	    String fontFamily = (String) ((JavascriptExecutor) StorageVariables.driver)
	            .executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('font-family');", el);
	    System.out.println("Element font family : "+fontFamily);
	    
	    //Get element font size
	    String fontSize = (String) ((JavascriptExecutor) StorageVariables.driver)
	            .executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('font-size');", el);
	    System.out.println("Element font size : "+fontSize);
	    
	}
	
	public static void getCSSvalues()
	{
		LaunchBrowser.splitTarget(StorageVariables.Target);
		WebElement element = StorageVariables.driver.findElement(StorageVariables.by);
		String fontSize = element.getCssValue("font-size");
		String fontColor = element.getCssValue("color");
	
		
		//Hover Color
		Actions action = new Actions(StorageVariables.driver);
	    action.moveToElement(element).perform();
	    String hovercolor =  element.getCssValue("color");
		
	}
	
	public static void generateRandomString()
     {
        try
        {
        LaunchBrowser.splitTarget(StorageVariables.Target);
        int count = 5;
        String variableName = "RandomVar";
        if(StorageVariables.Value.contains("|"))
        {
        count = Integer.parseInt(StorageVariables.Value.split("\\|")[0]); 
		variableName = StorageVariables.Value.split("\\|")[1];
        }
        
		String alphanumericstring = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomstring = new StringBuilder();
        Random rnd = new Random();
        
        while (randomstring.length() < count)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphanumericstring.length());
            randomstring.append(alphanumericstring.charAt(index));
        }
        
        String randomString = randomstring.toString();
        
        StorageVariables.stepLog+="<br>Random string is : "+randomString;
        
        //StorageVariables.Value= randomString;
        StorageVariables.driver.findElement(StorageVariables.by).sendKeys(randomString);
        
        Logger.logsuccess("Random string was successfully generated.");
        
        StorageVariables.StoredVariables.put(variableName,randomString); //put(StorageVariables.Value, text);
        }
        
        catch(Exception ex)
        {
        	 Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+ex+" Random String could not be generated.");
        }

    }
	
	 public static void modifyxpath()
	 {
	  
		 try
		 {
		  StorageVariables.Target.replace("param", StorageVariables.StoredVariables.get(StorageVariables.Value));
		  LaunchBrowser.splitTarget(StorageVariables.Target);
		  StorageVariables.driver.findElement(StorageVariables.by).click();
		  
		 }
		 
		 catch(Exception e)
		 {
			 
		 }
		 
		 
		 
     }
	
	
public static void stepMessageOnPage() throws InterruptedException
{
	/*try {
		StorageVariables.jse=(JavascriptExecutor)StorageVariables.driver;
	// Check for jQuery on the page, add it if need be
			StorageVariables.jse.executeScript("if (!window.jQuery) {"
					+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
					+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
					+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
			PageActions.waitForJStoLoad();
			Thread.sleep(1000);

			// Use jQuery to add jquery-growl to the page
			try
			{
				StorageVariables.jse.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");
				
				StorageVariables.jse.executeScript("$('head').append('<link rel=\"stylesheet\" "
						+ "href=\"https://the-internet.herokuapp.com/js/vendor/jquery.growl.js\"/>');");
			    StorageVariables.jse.executeScript("$('head').append('<link rel=\"stylesheet\" "
					+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
			    PageActions.waitForJStoLoad();
			}
			catch(Exception ec)
			{
			StorageVariables.jse.executeScript("var script = document.createElement(\"script\");  // create a script DOM node\n" + 
					"script.src = 'https://the-internet.herokuapp.com/js/vendor/jquery.growl.js';  \n" + 
					"document.head.appendChild(script); \n" + 
					"var style   = document.createElement( 'link' );\n" + 
					"style.rel   = 'stylesheet';\n" + 
					"style.type  = 'text/css';\n" + 
					"style.href  = \" https://the-internet.herokuapp.com/css/jquery.growl.css\";\n" + 
					"document.getElementsByTagName( 'head' )[0].appendChild( style );");
			}	

			//js.executeScript("$.getScript('/Users/NaveenKhunteta/Documents/workspace/Test/src/testcases/jquery.growl.js')");

			// Use jQuery to add jquery-growl styles to the page
			
			Thread.sleep(2000);

			// jquery-growl w/ no frills
			StorageVariables.jse.executeScript("$.growl({ title: 'Step Info', message: 'Current Step : "+StorageVariables.Action+"' });");
                 
			// jquery-growl w/ colorized output
			if(StorageVariables.messageType.equals("error"))
			{
			 StorageVariables.jse.executeScript("$.growl.error({ title: 'ERROR', message: 'The "+StorageVariables.Action+" action was unsucessful and has failed' });");
			}
			
			else if(StorageVariables.messageType.equals("success"))
			{
			 StorageVariables.jse.executeScript("$.growl.notice({ title: 'Passed', message: 'The "+StorageVariables.Action+" action was sucessful' });");
			}
			
			else if(StorageVariables.messageType.equals("warning"))
			{
			 StorageVariables.jse.executeScript("$.growl.warning({ title: 'Warning!', message: 'The "+StorageVariables.Action+" action was sucessful with warnings' });");
			}
	}
	catch(Exception e)
	{
		System.out.println("Exception is "+e);
	}*/
	
}


public static void switchToIframe()
{	
	try 
	{
		
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		WebElement frame = StorageVariables.driver.findElement(StorageVariables.by);
		StorageVariables.driver.switchTo().frame(frame);
	    Logger.logsuccess("Switched to iframe successfully.");		
	  
	}
	catch(Exception e) {
		Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Cannot switch to iframe");
	}
}

public static void switchtoDefaultContent()
{
	try
	{
	StorageVariables.driver.switchTo().parentFrame();
	StorageVariables.driver.switchTo().defaultContent();
	 Logger.logsuccess("Switched to default content successfully.");
	}
	
	catch(Exception e)
	{
		Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+e+" Cannot switch back to default content");
	}
}



public static void ClickMultipleLinks()
{
	try
	{
				
		LaunchBrowser.splitTarget(StorageVariables.Target);
		if(PageActions.isElementPresent())
		{
		List<WebElement> xpathofLinks = StorageVariables.driver.findElements(StorageVariables.by);
		
		for(WebElement xpath : xpathofLinks)
		{
			 LaunchBrowser.splitTarget(StorageVariables.Value);			
			 String label  = StorageVariables.driver.findElement(StorageVariables.by).getText();
			
			 //StorageVariables.highlightedElement=StorageVariables.driver.findElement(StorageVariables.by);
			 StorageVariables.jse = (JavascriptExecutor)StorageVariables.driver;
		  StorageVariables.jse.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", xpath);
			    
			 xpath.click();
			 Thread.sleep(1000);
			
			StorageVariables.stepLog+="<br>Clicked the link  "+label;
			StorageVariables.jse.executeScript("arguments[0].style.border='0px'", xpath);
			
			
		}
		 Logger.logsuccess("Click action performed successfully on the links.");
		}
		
		else
		{
			Logger.logwarning("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" Exiting as there are no links present");
		}
	}
	
	
	catch(Exception ex)
	{
		Logger.logerror("Step "+StorageVariables.stepNumber+ " : "+StorageVariables.Action+" failed with the exception "+ex+" Cannot click multiple links");
	}
}

public static void verifyElementandSkipSteps()
{
	int currentstep=StorageVariables.stepNumber;
	String stepMessage="";
	String currentAction="";
	try
	{
	
	LaunchBrowser.splitTarget(StorageVariables.Target);
	if(!PageActions.isElementPresent())
	{
	  currentstep=StorageVariables.stepNumber;
	  stepMessage="Element not found. <br>";
	  //currentAction=StorageVariables.actions.get(currentstep);
	  do
	  {			
		  stepMessage+="Skipping step : "+StorageVariables.actions.get(currentstep)+" "+StorageVariables.targets.get(currentstep)+" "+StorageVariables.values.get(currentstep)+"<br>";
		  StorageVariables.actions.remove(currentstep);
		  StorageVariables.targets.remove(currentstep);
		  StorageVariables.values.remove(currentstep);
		  currentAction=StorageVariables.actions.get(currentstep);
		//  StorageVariables.stepNumber++;
		  		 
	  }
	  
	  while(!currentAction.toUpperCase().contains("END"));
	  Logger.logwarning(stepMessage);
	}
	else
	{
		Logger.logsuccess("Element found on the page");
	
	}
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


}


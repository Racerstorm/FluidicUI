package actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Storage.StorageVariables;
import testStartup.LaunchBrowser;

public class CustomActions 
{

	public static void checkAlert() throws InterruptedException 
	{
	   try {
		   
	        WebDriverWait wait = new WebDriverWait(StorageVariables.driver, 2);
	        wait.until(ExpectedConditions.elementToBeClickable(StorageVariables.by));
	        Alert alert = StorageVariables.driver.switchTo().alert();
	        alert.dismiss();
	        
	       }
	   catch (Exception e)
	       {
		     StorageVariables.messageType="error";
			 stepMessageOnPage();
			 PageActions.TakeSreenshot();
	       }
	}
	
	public static void getElementStyle()
	{ 
		LaunchBrowser.splitTarget(StorageVariables.Target);
		
		
		//Get element background-color
	    WebElement el = StorageVariables.driver.findElement(StorageVariables.by);
	    
	    String styles=StorageVariables.driver.findElement(StorageVariables.by).getAttribute("style");
	    
	    JavascriptExecutor executor = (JavascriptExecutor)StorageVariables.driver;
	    String script = "var s = '';" +
	                    "var o = getComputedStyle(arguments[0]);" +
	                    "for(var i = 0; i < o.length; i++){" +
	                    "s+=o[i] + ':' + o.getPropertyValue(o[i])+'\\n';}" + 
	                    "return s;";

	    System.out.println(executor.executeScript(script, el));
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
	
	
	
public static void stepMessageOnPage() throws InterruptedException
{
	try {
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
			
			Thread.sleep(3000);

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
	}
	
}


public static void switchToIframe() {
	
	try {
		
		LaunchBrowser.splitTarget(StorageVariables.Target);
		PageActions.highlightElement();
		WebElement frame = StorageVariables.driver.findElement(StorageVariables.by);
		
	StorageVariables.driver.switchTo().frame(frame);
	System.out.println("Switched to iframe");
		
		
	}
	catch(Exception ex) {
		System.out.println("Cannot switch to iframe");
	}
}

}

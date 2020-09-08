# FluidicUI
Automation framework in Java



After cloning the project to local, create a batch file to set up default folders. Use the script below.
SetupFolders.bat
cd C:\Automation
md AutomationOne
md AutomationOne\Webdrivers
md AutomationOne\Libs
md AutomationOne\Screenshots
md AutomationOne\TestData
md AutomationOne\Reports
exit

cd %ProjectFolderPath%
java -cp C:\Automation\Libs\*;%ProjectFolderPath%\src -Dbrowsername=Chrome -Dtestcasename=Globe_Stage_ComposeMessage org.testng.TestNG testng.xml

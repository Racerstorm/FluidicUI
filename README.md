# FluidicUI
Hybrid test automation framework in Java



After cloning the project to local, create a batch file to set up default folders. Use the script below.
SetupFolders.bat
cd C:\
md Automation
cd C:\Automation 
md Webdrivers
md Libs
md Screenshots 
md TestData 
md Reports 
exit

cd %ProjectFolderPath%
java -cp C:\Automation\Libs\*;%ProjectFolderPath%\src -Dbrowsername=Chrome -Dtestcasename=Globe_Stage_ComposeMessage org.testng.TestNG testng.xml

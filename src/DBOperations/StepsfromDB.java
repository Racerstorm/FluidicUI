package DBOperations;
import  java.sql.Connection;
import  java.sql.DriverManager;
import  java.sql.SQLException;

public class StepsfromDB {
	
public void openDBConnection() throws ClassNotFoundException, SQLException
{
	//DB URL
	String url = "jdbc:mysql://localhost:3306/testcase";
	//Properties DBproperties = new Properties();
	String username = "root";
	String password = "root";
	
	//Query for DB
 	String query = "select StepNo,StepAction,StepTarget,StepValue from testcase";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,username,password);
    

} 
    
}

package DBOperations;

import java.util.Properties;
import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;

public class StepsfromDB {
	
public void openDBConnection() throws ClassNotFoundException, SQLException
{
	//DB URL
	String url = "jdbc:mysql://localhost:3036/tcsteps";
	//Properties DBproperties = new Properties();
	String username = "root";
	String password = "password";
	
	//Query for DB
 	String query = "select Actions,Targets,StoredValues from tcsteps";
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,username,password);
    

} 
    
}

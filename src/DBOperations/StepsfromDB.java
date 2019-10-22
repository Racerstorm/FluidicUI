package DBOperations;
import  java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.ResultSet;
import  java.sql.SQLException;
import java.sql.Statement;

import Storage.StorageVariables;

public class StepsfromDB {
	
public static void readtestcasestepsfromDB() throws ClassNotFoundException, SQLException
{
	//DB URL
	String url = "jdbc:mysql://localhost:3306/testcase";
	//Properties DBproperties = new Properties();
	String username = "root";
	String password = "root";
	
	//Query for DB
 	String query = "select StepNo,StepAction,StepTarget,StepValue from "+StorageVariables.file;
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,username,password);
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while(rs.next())
    {
    	StorageVariables.actions.add(rs.getString("StepAction"));
    	StorageVariables.targets.add(rs.getString("StepTarget"));
    	StorageVariables.values.add(rs.getString("StepValue"));
    	
    	
    }
    

} 
    
}

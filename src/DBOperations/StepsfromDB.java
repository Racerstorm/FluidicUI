package DBOperations;
import  java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.ResultSet;
import  java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import Storage.StorageVariables;

public class StepsfromDB {
	
public static void readtestcasestepsfromDB() throws ClassNotFoundException, SQLException
{
	//DB URL
	String url = "jdbc:mysql://localhost:3306/testcases";
	//Properties DBproperties = new Properties();
	String username = "root";
	String password = "root";
	
	//Query for DB
 	String query = "select Actions,Targets,FieldValues from "+StorageVariables.inputFile.toLowerCase(Locale.ENGLISH);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,username,password);
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while(rs.next())
    {
    	StorageVariables.actions.add(rs.getString("Actions"));
    	StorageVariables.targets.add(rs.getString("Targets"));
    	StorageVariables.values.add(rs.getString("FieldValues"));
    	
    	
    }
    

} 
    
}

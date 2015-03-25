import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnectionClass {

	public static Connection getConnection()
	{
	Connection con=null;
	
	/*String DriverName="oracle.jdbc.driver.OracleDriver";
	String userName="system";
	String password="sai";
	String driverManager="jdbc:oracle:thin:@localhost:3030:xe";
	*/
	String DriverName="com.mysql.jdbc.Driver";
	String userName="root";
	String password="garage@makers";
	String driverManager="jdbc:mysql://localhost:3306/groovy_mg";
	
	try {
	Class.forName(DriverName);
	con=DriverManager.getConnection(driverManager, userName, password);
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	System.out.println("Driver Get's Loaded");
	return con;
	}
	
}

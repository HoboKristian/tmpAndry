package treningsdagbok;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class showWorkout extends DBConn{
		 
			
	public void showWorkOuts(int n){
		 	
		Statement sporring = null;
		ResultSet result = null;
		 	
 		try {
 			sporring = conn.createStatement();
 	        //Finner f�rst IDen til de som er lagt til sist
 	        result = sporring.executeQuery("select * from treningsøkt ORDER BY treningsøkt.treningsid DESC LIMIT "
 	                + " " + n  + ";");
 	        while(result.next()){
 	            System.out.println("ID: " + result.getString(1) + " Varighet: " + result.getString(2) + " Form: " + result.getString(3) + " Prestasjon: " + result.getString(4) + " Tidspunkt: " + result.getString(5) + " Dato: " + result.getString(6) + " Notat: " + result.getString(7));
 	        }		
 		}
 		catch(SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
	}
}
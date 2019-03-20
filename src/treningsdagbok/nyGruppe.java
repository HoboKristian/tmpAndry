package treningsdagbok;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class nyGruppe extends DBConn {
	
	Statement stmt = null;
	ResultSet rs = null;
	
	public static String rightPadding(String str, int num) {
		return String.format("%1$-" + num + "s", str);
	}
	
	public void OvelseIgruppe(int id){
		
	try {
		stmt = conn.createStatement();
		
		String query = "SELECT gruppeid,navn FROM øvelse Inner Join øvelseigruppe ON (øvelse.øvelsesid = øvelseigruppe.øvelsesid) WHERE gruppeid="+id+";";
		if (stmt.execute(query)) {
			rs = stmt.getResultSet();
		}
		
		while (rs.next()) {
			String string = rs.getString(1);
			String string2 = rs.getString(2);
			System.out.println(string+ " "+string2);
		}
	}
	catch(SQLException ex) {
		System.out.println("SQLException " + ex.getMessage());
	}
	
}      
	            
	
	public void insettOvelseGruppe(int gruppeID, String muskelgruppe) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into øvelsesGruppe values ("+gruppeID+",\'"+muskelgruppe+"\')");
			System.out.println("gruppe ble lagret");
		}
		catch(SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
	}
	
	public void insettOvelserIGruppen(int OvelsesID, int gruppeID) {
		try {
			stmt = conn.createStatement();
			String query = "insert into øvelseigruppe values(" + gruppeID + ", "+ OvelsesID +");";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch(SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
	}
	
	public void getOvelser() {
		try {
			stmt = conn.createStatement();
			String query = "select * from øvelse;";
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
			
			String rad = rightPadding("�velsesID", 15) + rightPadding("Navn p� �velse", 15);
			while (rs.next()) {
				String kolonne = null;
				rad += "\n";
				for (int i = 1; i < 3; i++) {
					kolonne = rs.getString(i);
					rad += rightPadding(kolonne, 15);
				}	
			}
			System.out.println(rad);
		}
		catch(SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
		
	}
} 
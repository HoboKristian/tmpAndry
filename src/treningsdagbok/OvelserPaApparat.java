package treningsdagbok;

import java.sql.*;

public class OvelserPaApparat extends DBConn{

	
	public void OvelserApparat(String navn) {
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			
			String query = "SELECT øvelse.navn FROM øvelsemedapparat Inner Join apparat ON øvelsemedapparat.apparatid = apparat.apparatid Inner Join øvelse ON øvelse.øvelsesid = øvelsemedapparat.øvelsesid WHERE apparat.navn ="+"'"+navn+"'";
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
			
			while (rs.next()) {
				String string = rs.getString(1);
				System.out.println(string);
			}
		}
		catch(SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
		
	}
	
}
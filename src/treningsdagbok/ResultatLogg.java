package treningsdagbok;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultatLogg extends DBConn{
	
	
	
	public static String rightPadding(String str, int num) {
		return String.format("%1$-" + num + "s", str);
	}
	
	public void getResultatLogg(String Ovelse, String start, String slutt, int tall) {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			//�nsker du � sortere p� dato (1) eller starttidspunkter (2)?
			String insett = null;
			if (tall == 1) {
				insett = "dato";
			}
			else { insett = "tidspunkt"; }
			String query = "select treningsid, navn, dato, tidspunkt, varighet, form, prestasjon, notat " + 
					"from (øvelse natural join øvelsepåøkt) natural join treningsøkt " +
					"where navn = '" + Ovelse + "' and " + insett +" between '" + start + "' and '"
					+ slutt +"';";
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
			String rad = rightPadding("Treningsid", 15) + rightPadding("øvelse", 15) + rightPadding("dato", 15) +
					rightPadding("tidspunkt", 15) + rightPadding("varighet", 15) + 
					rightPadding("form", 15) + rightPadding("prestasjon", 15) + rightPadding("notat", 15);
			
			while (rs.next()) {
				String kolonne = null;
				rad += "\n";
				for (int i = 1; i < 9; i++) {
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

package treningsdagbok;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hei, og velkommen til din treningsdagbok!");
    		
		while (true) {
		
	    	System.out.println( "Velg din funksjonalitet (skriv inn tallet): \n"
	    			+ "1) \t Sett registrere nye apparater, �velser og trenings�kter \n"
	    			+ "2) \t Her finner du informasjon om de n siste trenings�ktene \n"
	    			+ "3) \t Her kan du for hver enkelt �velse se en resultatlogg i et gitt tidsintervall \n"
	    			+ "4) \t Her kan du lage nye �velsesgrupper og finne ut hvilke �velser som er i hvilken gruppe \n"
	    			+ "5) \t Her kan du finne ut hvilke �velser du kan gj�re p� et apparat \n"
	    			+ "0) \t Skriv 0 om du �nsker � avslutte programmet");
	    			
	    	int funksj = scanner.nextInt();
	    	
	    	if (funksj == 0) {
	    		break;
	    	}
	    	
	    	if (funksj == 1) {
	    		treningsdagbok.Registrer r = new treningsdagbok.Registrer();
	    		r.connect();
	    		
	    		System.out.println("�nsker du � registrere en ny trenings�kt (1), apparat (2) eller �velse(3)");
	    		int funk = scanner.nextInt();
	    		if (funk == 1) {
	    			System.out.println("Skriv inn dato (format: YYYY-MM-DD)");
	    			String dato = scanner.next();
	    			System.out.println("Skriv inn tidspunkt (format: HHMMSS)");
	    			String tidspunkt = scanner.next();
	    			System.out.println("Skriv inn varighet");
	    			int varighet = scanner.nextInt();
	    			System.out.println("Skriv inn form (tall fra 1-10");
	    			int form = scanner.nextInt();
	    			System.out.println("Skriv inn prestasjon (tall fra 1-10)");
	    			int prestasjon = scanner.nextInt();
	    			System.out.println("Skriv inn notat");
	    			String notat = scanner.next();
	    			notat += scanner.nextLine();
	    			r.registrer_treningsokt(dato, tidspunkt, varighet, form, prestasjon, notat);
	    		}
	    		if (funk == 2) {
	    			System.out.println("Skriv inn apparatid");
	    			int id = scanner.nextInt();
	    			System.out.println("Skriv inn navn");
	    			String navn = scanner.next();
	    			System.out.println("Skriv inn beskrivelse");
	    			String besk = scanner.next();
	    			r.registrer_aparat(id, navn, besk);
	    		}
	    		if (funk == 3) {
	    			System.out.println("Skriv inn �velsesid");
	    			int id = scanner.nextInt();
	    			System.out.println("Skriv inn navn");
	    			String navn = scanner.next();
	    			r.registrer_ovelse(id, navn);
	    			
	    		}
		    		
		    }
		    	
	    	if (funksj == 2) {
	    		showWorkout show = new showWorkout();
	    	    show.connect();
	    		System.out.println("Skriv inn hvor mange �kter du har lyst til � se:");
	    		int n = scanner.nextInt();
		 		show.showWorkOuts(n);
	    	}
	    	
	    	if (funksj == 3) {
	    		System.out.println("Hvilken �velse �nsker du � se resultatlogg for? ");
	    		String ovelse = scanner.next();
	    		System.out.println("�nsker du � finne tidsintervall basert p� dato (velg 1) eller (start)tidspunk i l�pet av en dag (velg 2)?");
	    		int intervall = scanner.nextInt();
	    		String start = null;
	    		String slutt = null;
	    		if (intervall == 1) {
	    			System.out.println("Velg startdato p� format YYYY-MM-DD");
	    			start = scanner.next();
	    			System.out.println("Velg sluttdato p� format YYYY-MM-DD");
	    			slutt = scanner.next();
	    		}
	    		if (intervall == 2) {
	    			System.out.println("Velg startid p� format HHMMSS");
	    			start = scanner.next();
	    			System.out.println("Velg sluttdato p� format HHMMSS");
	    			slutt = scanner.next();
	    		}
	    		ResultatLogg result = new ResultatLogg();
	    		result.connect();
	    		result.getResultatLogg(ovelse, start, slutt, intervall);
	    	}
		    	
	    	Statement stmt = null;
			ResultSet rs = null;
		    	
	    	if (funksj == 4){
	    		nyGruppe p = new nyGruppe();
	    		p.connect();
	    		System.out.println("�nsker du � opprette en ny muskelgruppe, svar y dersom ja og n dersom nei");
	    		String svar= scanner.next();
	    		if(svar.equals("y")){
	    			System.out.println("Hvilke musklgruppe �nsker du � lage en gruppe for? "); 
		    		String gruppeNavn= scanner.next();
		            System.out.println("Hvilke id skal muskelgruppen ha?");
		    		int id= scanner.nextInt();
		    		scanner.nextLine();
		    		p.insettOvelseGruppe(id, gruppeNavn);
		    		System.out.println("Hvilke �velser vil du at skal ligge i gruppen, skriv inn id(er)");
		    		p.getOvelser();
		    		System.out.println("Velg en �velse, og trykk enter. N�r du er ferdig kan skriv 0");
		    		while (true) {
		    			int nyOvelse = scanner.nextInt();
		    			if (nyOvelse == 0) {
		    				break;
		    			}
		    			else {
		    				p.insettOvelserIGruppen(nyOvelse, id);
		    			}
		    		}
	    		}
	    		System.out.println("Hvilke Gruppeid �nsker du � se �velser fra?");
	    		int gruppeId2= scanner.nextInt();
	    		p.OvelseIgruppe(gruppeId2);
	    	}
	    	
	    	if (funksj == 5) { 
			    System.out.println("Skriv navnet p� apparatet: ");
			    String navn = scanner.next();
			    
			    OvelserPaApparat hei = new OvelserPaApparat ();
	    	    hei.connect();
	    	    hei.OvelserApparat(navn);
	    	}	    
		}
		
	    System.out.println("Hadebra!");
		scanner.close();
    }

	
}

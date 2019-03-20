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
	    			+ "1) \t Sett registrere nye apparater, øvelser og treningsøkter \n"
	    			+ "2) \t Her finner du informasjon om de n siste treningsøktene \n"
	    			+ "3) \t Her kan du for hver enkelt øvelse se en resultatlogg i et gitt tidsintervall \n"
	    			+ "4) \t Her kan du lage nye øvelsesgrupper og finne ut hvilke øvelser som er i hvilken gruppe \n"
	    			+ "5) \t Her kan du finne ut hvilke øvelser du kan gjøre på et apparat \n"
	    			+ "0) \t Skriv 0 om du ønsker å avslutte programmet");
	    			
	    	int funksj = scanner.nextInt();
	    	
	    	if (funksj == 0) {
	    		break;
	    	}
	    	
	    	if (funksj == 1) {
	    		treningsdagbok.Registrer r = new treningsdagbok.Registrer();
	    		r.connect();
	    		
	    		System.out.println("ønsker du å registrere en ny treningsøkt (1), apparat (2) eller øvelse(3)");
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
	    			System.out.println("Skriv inn øvelsesid");
	    			int id = scanner.nextInt();
	    			System.out.println("Skriv inn navn");
	    			String navn = scanner.next();
	    			r.registrer_ovelse(id, navn);
	    			
	    		}
		    		
		    }
		    	
	    	if (funksj == 2) {
	    		showWorkout show = new showWorkout();
	    	    show.connect();
	    		System.out.println("Skriv inn hvor mange økter du har lyst til å se:");
	    		int n = scanner.nextInt();
		 		show.showWorkOuts(n);
	    	}
	    	
	    	if (funksj == 3) {
	    		System.out.println("Hvilken øvelse ønsker du å se resultatlogg for? ");
	    		String ovelse = scanner.next();
	    		System.out.println("ønsker du å finne tidsintervall basert på dato (velg 1) eller (start)tidspunk i løpet av en dag (velg 2)?");
	    		int intervall = scanner.nextInt();
	    		String start = null;
	    		String slutt = null;
	    		if (intervall == 1) {
	    			System.out.println("Velg startdato på format YYYY-MM-DD");
	    			start = scanner.next();
	    			System.out.println("Velg sluttdato på format YYYY-MM-DD");
	    			slutt = scanner.next();
	    		}
	    		if (intervall == 2) {
	    			System.out.println("Velg startid på format HHMMSS");
	    			start = scanner.next();
	    			System.out.println("Velg sluttdato på format HHMMSS");
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
	    		System.out.println("ønsker du å opprette en ny muskelgruppe, svar y dersom ja og n dersom nei");
	    		String svar= scanner.next();
	    		if(svar.equals("y")){
	    			System.out.println("Hvilke musklgruppe ønsker duålage en gruppe for? "); 
		    		String gruppeNavn= scanner.next();
		            System.out.println("Hvilke id skal muskelgruppen ha?");
		    		int id= scanner.nextInt();
		    		scanner.nextLine();
		    		p.insettOvelseGruppe(id, gruppeNavn);
		    		System.out.println("Hvilke øvelser vil du at skal ligge i gruppen, skriv inn id(er)");
		    		p.getOvelser();
		    		System.out.println("Velg en øvelse, og trykk enter. Når du er ferdig kan skriv 0");
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
	    		System.out.println("Hvilke Gruppeid ønsker du å se øvelser fra?");
	    		int gruppeId2= scanner.nextInt();
	    		p.OvelseIgruppe(gruppeId2);
	    	}
	    	
	    	if (funksj == 5) { 
			    System.out.println("Skriv navnet på apparatet: ");
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

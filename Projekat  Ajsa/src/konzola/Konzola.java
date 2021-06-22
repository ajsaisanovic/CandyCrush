package konzola;

import java.util.Scanner;

import logika.Logika;

public class Konzola {
	Logika logika;
	int [][] tabelaStanja;
	
	public Konzola() {
		tabelaStanja=new int[9][9];    		
	}
	
	public void poveziKonzoluiSLogikom(Logika log) {
		try {
			logika=log;
			tabelaStanja=logika.vratiTabeluStanja();
		}
		catch(Exception e){}
	}
	/**
	 * Funkcija prima koordinate polja koje treba zamijeniti, potom salje signal logici gdje se vrsi njihova zamjena, nakon cega
	 * osvjezavamo stanje tabele, te provjeravamo da li je kraj igre ukoliko jeste prekidamo igricu, dok u suprotnom pozivamo funkciju
	 * odigrajPotez, te korisnik ponovo unosi polja koja zeli zamijeniti.
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void posaljiSignalKaLogici(int x, int y,int x1, int y1) {
		logika.primiSignal(x,y,x1,y1);
		
		if(logika.krajIgre()) {
			System.out.println("Kraj igrice");
			return;
		}	
		
		System.out.println("Broj preostalih poteza je: " + logika.brojPreostalihPoteza());
		tabelaStanja=logika.vratiTabeluStanja();
		odigrajPotez();
	}
	/**
	 * Unosimo koordinate polja koje zelimo zamijeniti.
	 */
	public void odigrajPotez() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++)
				System.out.print(tabelaStanja[i][j]+" ");
			System.out.println();
		}
		Scanner ulaz=new Scanner(System.in);
		int x=ulaz.nextInt();
		int y=ulaz.nextInt();
		int x1=ulaz.nextInt();
		int y1=ulaz.nextInt();
		posaljiSignalKaLogici(x,y,x1,y1);
	}

}



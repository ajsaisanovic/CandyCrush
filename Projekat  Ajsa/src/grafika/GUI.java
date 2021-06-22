package grafika;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logika.Logika;

public class GUI {
	Logika logika;
	Okvir glavni_prozor;
	int x,y,x1,y1;
	JButton[][] tabelaDugmadi;
	int [][] tabelaStanja;
	boolean dugmePritisnuto;
	
	public GUI() {
		tabelaStanja=new int[9][9];
		tabelaDugmadi=new JButton[9][9];
		dugmePritisnuto=false;	
	}
	
	public void poveziGuiSLogikom(Logika log) {
		logika=log;
		try {
			tabelaStanja=logika.vratiTabeluStanja();
			glavni_prozor=new Okvir(this);
		}
		catch(Exception e) {}
	}
	/**
	 * Funkcija prima koordinate pritisnutog dugmeta, potom se vrsi provjera da li je pritisnuto samo jedno dugme ili dva, ako su 
	 * pritisnuta dva dugmeta, saljemo njihove koordinate logici gdje ce se dalje vrsiti njihova zamjena.
	 * @param koord1
	 * @param koord2
	 */
	public void posaljiSignalKaLogici(int koord1, int koord2) {

		if(dugmePritisnuto) {
			x1=koord1;
			y1=koord2;
			logika.primiSignal(x, y,x1,y1);
			tabelaStanja=logika.vratiTabeluStanja();
			if(logika.krajIgre()) {
				glavni_prozor.postaviSadrzaj("Kraj igrice");
				return;
			}
			glavni_prozor.osvjeziTabeluStanja(tabelaStanja);
			glavni_prozor.postaviSadrzaj("Broj preostalih poteza je: " + String.valueOf(logika.brojPreostalihPoteza()));
			dugmePritisnuto=false;
		}
		else{
			x=koord1;
			y=koord2;
			dugmePritisnuto=true;
		}
	}
	
	public void prikaziGUI() {
		glavni_prozor.prikaziOkvir();
	}
	
	public int[][] vratiTabelu() {
		return tabelaStanja;
	}
		
}


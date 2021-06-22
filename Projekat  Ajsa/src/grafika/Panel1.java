package grafika;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Panel u kojem se prikazuje ploca sa dugmicima.
 *
 */
public class Panel1 extends JPanel {
	JButton[][] tabelaDugmadi;
	int [][] tabelaStanja;
	JButton dugme;
	GUI gui;
	JPanel prikazTabele;
	
	private void build(int n) {
		setLayout(new GridLayout(1,1));
		prikazTabele = new JPanel();
		prikazTabele.setLayout(new GridLayout(n,n));
		tabelaDugmadi=new JButton[n][n];
		tabelaStanja=new int[n][n];
		tabelaStanja=gui.vratiTabelu();
		dodajDugme(n);
		add(prikazTabele);
	}
	
	public Panel1(GUI gui1) {
		gui=gui1;
		build(9);
	}
	
	/**
	 *  Boje dugmica definisemo u zavisnosti vrijednosti koje se nalaze u tabeliStanja, gdje nam pomocna funkcija vratiBoju 
	 *  vraca odredenu boju u zavisnoti od proslijedenog broja, te za svaki dugmic definisemo listener koji ce vracati koordinate 
	 *  pritisnutog dugmeta.
	 */
	private void dodajDugme(int dim) {
		for(int i=0;i<dim;i++) {
			for(int j=0;j<dim;j++) {
				
				int x=i;
				int y=j;
				dugme=new JButton();
				dugme.setBackground(vratiBoju(tabelaStanja[i][j]));
				
				dugme.setPreferredSize(new Dimension(50,50));
				tabelaDugmadi[i][j]=dugme;
				
				tabelaDugmadi[i][j].addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						 
						 gui.posaljiSignalKaLogici(x,y);		 
						 
					}
				});
				prikazTabele.add(tabelaDugmadi[i][j]);
						
			}
		}	
		
	}
	/**
	 * Pomocna funkcija koja nam vraca boju na osnovu proslijedenog broja.
	 */
	private Color vratiBoju(int n) {
		if(n==0)
			return Color.RED;
		if(n==1)
			return Color.BLUE;
		if(n==2)
			return Color.YELLOW;
		if(n==3)
			return Color.GREEN;
		return Color.BLACK;
	}
	
	
	public int[][] vratiTabelu(){
		return tabelaStanja;
	}
	/**
	 * Funkcija koja prima izmijenje tabelu stanja.
	 * @param tabela
	 */
	public void postaviTabeluStanja(int[][] tabela) {
		tabelaStanja=tabela;
		osvjeziTabeluDugmadi();
	}
	/**
	 * Nakon svakog odigranog poteza potrebno je osvjeziti tabelu koja se prikazuje korisniku.
	 */
	public void osvjeziTabeluDugmadi() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tabelaDugmadi[i][j].setBackground(vratiBoju(tabelaStanja[i][j]));
			}
		}
	}

}

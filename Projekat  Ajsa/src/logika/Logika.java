package logika;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import grafika.GUI;
import konzola.Konzola;

public class Logika {
	
	private int[][] tabelaStanja;
	
	private int x,y;
	private int x1,y1;
	private int brojPoteza;

	boolean zamjena;
	private boolean kraj;
	
	public Logika() {
		tabelaStanja=new int[9][9];
		napraviMatricu();
		kraj=false;
		x=0; y=0; x1=0; y1=0;
		brojPoteza=20;
		zamjena=false;
	}
	
	/**
	 * Funkcija napraviMatricu nam pravi pocetno stanje tabela kada se igrica pokrene. Generisemo matricu dimenzija 9x9 u kojoj polja
	 * popunjavamo slucajno generisanim brojevima iz intervala [0,3], s tim da moramo paziti da nam ge ne generise broj na nacin da 
	 * imamo isti broj tri puta uzastopno u koloni ili redu.
	 */
	public void napraviMatricu() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				tabelaStanja[i][j]=slucajanBroj(4);
				if(i>1) {
					while(tabelaStanja[i][j] ==tabelaStanja[i-1][j] &&
							tabelaStanja[i][j] ==tabelaStanja[i-2][j]) {

						tabelaStanja[i][j]=slucajanBroj(4);
					}		
				}
				if(j>1) {
					while(tabelaStanja[i][j] ==tabelaStanja[i][j-1] &&
							tabelaStanja[i][j] ==tabelaStanja[i][j-2]) {
						
						tabelaStanja[i][j]=slucajanBroj(4);
					}		
				}
			}
		}
	}
	/**
	 * Funkcija za vracanje tabele stanja.
	 * @return
	 */
	public int[][] vratiTabeluStanja() {
		return tabelaStanja;
	}
	/**
	 * Funkcija koja nam vraca preostali broj poteza koji igrac ima.
	 * @return
	 */
	public int brojPreostalihPoteza() {
		return brojPoteza;
	}
	/**
	 * Ova funkcija prima koordinate dva polja koja trebamo zamijeniti, te vrsi njihovu zamjenu.
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public void primiSignal(int x,int y,int x1, int y1) {
			
		if((x1==x && (y1==y-1 || y1==y+1)) || (y1==y && (x1==x-1 || x1==x+1))){
		
			if((x1==x && y1==y) ) {
				return;
			}	
			int pomocna;
			pomocna=tabelaStanja[x][y];
			tabelaStanja[x][y]=tabelaStanja[x1][y1];
			tabelaStanja[x1][y1]=pomocna;
			brojPoteza--;
			
			ponistavanjeRedovaKolona();
			if(zamjena==false) {
				pomocna=tabelaStanja[x][y];
				tabelaStanja[x][y]=tabelaStanja[x1][y1];
				tabelaStanja[x1][y1]=pomocna;
				brojPoteza++;
			}
			else
				zamjena=false;
			
			if(brojPoteza==0) {
				kraj=true;						
			}	
		}
    }
	
	public boolean krajIgre() {
		return kraj;
	}
	/** 
	 * U ovoj funkciji vrsimo provjeru da li se odredeni red ili kolona trebaju ponisitit, to se desava u slucaju kada tri ili vise
	 * uzastopna dugmica imaju istu boju, ponistavanje vrsimo sve dok imamo takvu situaciju. Prvo provjeravamo da li u redu postoje
	 * polja koja trebamo ponisiti, u slucaju da postoje vrsimo njihovo ponistavanje, te spustamo sve polja koja su se nalazila iznad 
	 * i generisemo nove dugmice za pocetni red. Kada zavrsimo za provjervom s redovima vrsimo identicnu provjeru za kolone. 
	 */
	public void ponistavanjeRedovaKolona() {
		int brojac=0;
		int pozicijaX=0;
		int pozicijaY=0;
		
		boolean promjenaTri=true;
		boolean promjenaCetiri=false;
		boolean promjenaPet=false;
		boolean promjenaHorizontalno=false;
		boolean promjenaVertikalno=false;
		
		while(promjenaTri) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					if(j+2<9) {
						if(tabelaStanja[i][j]==tabelaStanja[i][j+1] && tabelaStanja[i][j]==tabelaStanja[i][j+2]) {
							promjenaTri=true;
							zamjena=true;
							pozicijaX=i;
							pozicijaY=j;
							promjenaHorizontalno=true;
							if(j+3<9) {
								if(tabelaStanja[i][j]==tabelaStanja[i][j+3])
									promjenaCetiri=true;
								
							}
							if(promjenaCetiri && j+4<9) {
								if(tabelaStanja[i][j]==tabelaStanja[i][j+4])
									promjenaPet=true;
							}
							break;
						}
					}
					if(promjenaHorizontalno)
						break;
					if(i+2<9) {
						if(tabelaStanja[i][j]==tabelaStanja[i+1][j] && tabelaStanja[i][j]==tabelaStanja[i+2][j]) {
							promjenaTri=true;
					        zamjena=true;
							pozicijaX=i;
							pozicijaY=j;
							promjenaVertikalno=true;
							
							if(i+3<9) {
							  if(tabelaStanja[i][j]==tabelaStanja[i+3][j])
								  promjenaCetiri=true;
							  
							}
							if(promjenaCetiri && i+4<9) {
								  if(tabelaStanja[i][j]==tabelaStanja[i+4][j])
									  promjenaPet=true;
								  
								}
							
						}
					}
					if(promjenaVertikalno)
						break;	
				
				}
				if(promjenaHorizontalno || promjenaVertikalno)
					break;
					
				if( promjenaHorizontalno==false && promjenaVertikalno==false) {
					promjenaTri=false;
				}
			
			}
			if(!promjenaTri)
				break;
			
			if(promjenaTri && promjenaHorizontalno) {
				int n=3;
				if(promjenaCetiri) {
					n=4;
				}
				if(promjenaPet) {
					n=5;
				}
				for(int i=pozicijaX;i>0;i--) {	
					for(int j=pozicijaY;j<pozicijaY+n;j++) {
						tabelaStanja[i][j]=tabelaStanja[i-1][j];	
					}
				}
				
				for(int j=pozicijaY;j<pozicijaY+n;j++) {
					int m=slucajanBroj(4);
					tabelaStanja[0][j]=m;
					
				}
				promjenaHorizontalno=false;
				promjenaCetiri=false;
				promjenaPet=false;
			
			}
			
			if(promjenaTri && promjenaVertikalno) {
				int n=3;
				if(promjenaCetiri) {
					n=4;
				}
				if(promjenaPet) {
					n=5;
				}
				for(int i=pozicijaX+n-1;i>pozicijaX-1;i--) {
					int j=pozicijaY;
					if(i-n<0)
						break;
					tabelaStanja[i][j]=tabelaStanja[i-n][j];		
					
				}
				
				for(int i=0;i<n;i++) {
					int m=slucajanBroj(4);
					int j=pozicijaY;
					tabelaStanja[i][j]=m;				
				}
			}
			promjenaVertikalno=false;
			promjenaCetiri=false;
			promjenaPet=false;
			 
		}
		
	}
	/**
	 * Pomocna funkcija koja vraca slucajni broj u intervalu od [0,n) gdje je n parametar koji prosljedujemo funkciji.
	 */
	private static int slucajanBroj(int n){ 
		return (int) (Math.random()*n); 
	}
	
}

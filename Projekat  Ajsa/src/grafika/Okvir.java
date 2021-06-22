package grafika;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Definisemo panele, njihovu velicinu, te poziciju gdje se prikazuju.
 * @author User
 *
 */
public class Okvir extends JFrame {
	GUI gui;
	Panel1 panel1;
	Panel2 panel2;
	
	private void build() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Candy Crush");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		panel1=new Panel1(gui);
		c.gridx=0;
		c.gridy=0;
		c.weightx=1;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		add(panel1,c);
		
		panel2=new Panel2();
		c.gridx=0;
		c.gridy=1;
		c.weightx=1;
		c.weighty=0.4;
		c.fill=GridBagConstraints.BOTH;
		add(panel2,c);
		pack();
		setLocationRelativeTo(null);
		
	}
	public Okvir(GUI gui1) {
		gui=gui1;
		build();
	}
	public void prikaziOkvir() {
		setVisible(true);
	}
	public void postaviSadrzaj(String s) {
		panel2.postaviSadrzaj(String.valueOf(s));
	}
	
	public int[][] vratiTabelu(){
		return panel1.vratiTabelu();
	}
	public void osvjeziTabeluStanja(int[][] tabela) {
		panel1.postaviTabeluStanja(tabela);
	}
}

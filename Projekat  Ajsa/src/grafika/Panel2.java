package grafika;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Ovaj panel nam sluzi za prikaz informacije korisniku o broju preostalih poteza koje ima na raspolaganju.
 */
public class Panel2 extends JPanel {
	JTextField polje;
	private void build() {
		setLayout(new GridLayout(1,1));
		polje=new JTextField("Broj poteza: 20");
		add(polje);
	}
	public Panel2() {
		build();
	}
	/**
	 * Mijenjamo tekst koji se prikazuje igracu.
	 * @param s
	 */
	public void postaviSadrzaj(String s) {
		polje.setText(s);
	}

}

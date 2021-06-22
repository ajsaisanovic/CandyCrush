package grafika;

import logika.Logika;
/**
 * Pokrecemo igricu preko GUI-a.
 *
 */
public class IgrajCandyCrush {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logika logika=new Logika();
		GUI gui=new GUI();
		gui.poveziGuiSLogikom(logika);
		gui.prikaziGUI();

	}

}

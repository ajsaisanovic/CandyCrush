package konzola;

import logika.Logika;
/**
 * Pokrecemo igranje igrice pomocu konzole.
 *
 */
public class IgrajIgricu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Konzola konzola=new Konzola();
		Logika logika=new Logika();
		konzola.poveziKonzoluiSLogikom(logika);
		konzola.odigrajPotez();

	}

}

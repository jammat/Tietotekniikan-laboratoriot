import java.util.regex.*;
import javax.swing.*;

public class Main {
	static boolean kaynnissa = true;
	static int viive;
	static int leveys = 64;
	static int korkeus = 64;
	public static void main(String[] args) throws InterruptedException{
		Langton murkku = new Langton(leveys,korkeus);//Annetaan logiikalle taulukkokoko
		Paneeli paneeli = new Paneeli(murkku);//Annetaan ruudulle logiikka
		/*
		 * Jos syötetty muu kuin numeroita, lopeta peli. Jos numeroita, käynnistä. Jos tyhjä kenttä, lopeta peli.
		 */
		String luku = JOptionPane.showInputDialog("Aseta viive millisekunneissa ", 0);
		if (luku == null) {
			kaynnissa = false;
			System.exit(-1);
		}
		/*
		 * Otetaan ainoastaan numerot
		 */
		Pattern p = Pattern.compile("[0-9]");
		/*
		 * Annetaan syöte ja haluttu sisältö vertailtavaksi
		 */
		Matcher m = p.matcher(luku);
		/*
		 * Jos ei löydetä ainoastaan numeroita, tai numero on negatiivinen
		 */
		if (!m.find() || Integer.parseInt(luku) < 0){
		       JOptionPane.showMessageDialog(null, "Syötä ainoastaan positiivinen luku");
		       kaynnissa = false;
		       System.exit(-1);
		}
		else {
			viive = Integer.parseInt(luku);
		}
		/*
		 * Jos syötetty numero, piirrä paneeli
		 */
		paneeli.setVisible(true);
		/*
		 * Piirtää ruudukon ja antaa sille syötetyn viiveen
		 */
		while (kaynnissa) {
			paneeli.piirra(viive);
		}
	}
}
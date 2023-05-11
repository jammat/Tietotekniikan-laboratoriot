
public class Main {
	static boolean kaynnissa = true; //peli käynnissä
	public static void main(String[] args) {
		Peli mato = new Peli(Ruutu.LEVEYS,Ruutu.KORKEUS); //luodaan madon kenttä ruudulta saatavilla parametreillä
		Ruutu ruutu = new Ruutu(mato); //luodaan ruutu 
		ruutu.setVisible(true); //näytetään ruutu
		/*
		 * Aina kun ohjelma käynnissä, käytetään piirrä -funktiota.
		 */
		while(kaynnissa) {
			ruutu.piirra();
		}
	}
}

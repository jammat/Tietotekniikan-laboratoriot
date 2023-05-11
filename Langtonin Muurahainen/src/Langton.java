import java.util.*;

public class Langton {
	int[][] kentta;
	int leveys;
	int korkeus;
	public final int MUSTA = 0;
	public final int VALKOINEN = 1;
	public final int MUURAHAINEN = 2;
	/*
	 * Ensimmäinen ruutu tulkitaan valkoisena, suunta alaspäin
	 */
	String vari ="VALKOINEN";
	char suunta='A';
	Random satunnainen = new Random();
	/*
	 * Alustetaan kenttä mainilta saatavalla leveydellä ja korkeudella ja 
	 * annetaan muurahaiselle (melko)satunnainen sijainti maailmassa 
	 * (x:n ja y:n arvot taulukon keskeltä 5 askelta suuntaansa, xMax=yMax=64).
	 */
	public Langton(int leveys, int korkeus) {
		this.leveys = leveys;
		this.korkeus = korkeus;
		kentta = new int[leveys][korkeus];
		kentta[satunnainen.nextInt((leveys/2+5) - (leveys/2-5) + 1) + (leveys/2-5)][satunnainen.nextInt((korkeus/2+5) - (korkeus/2-5) + 1) + (korkeus/2-5)] = MUURAHAINEN;
	}
	/*
	 * Ohjelma käynnissä
	 */
	public void kaynnissa() {
		int pituus = kentta.length;
		int x = 0;
		int y = 0;
		int xSuunta = 0;
		int ySuunta = 0;
		/*
		 * Asetetaan muurahaisen sijainti maailmassa.
		 */
		for (int i=0; i<pituus; i++) {
			for(int j=0;j<pituus;j++) {
				if(kentta[i][j]==MUURAHAINEN) {
					x=i;
					y=j;
				}
			}
		}
		/*
		 * Jos väri = musta, käännetään aina 90 astetta vastapäivään.
		 */
		if(vari.equals("MUSTA")) {	
			kentta[x][y] = MUSTA;
			if(suunta == 'Y') {
				suunta='V';
				xSuunta=-1;
				ySuunta=0;
			}
			else if(suunta == 'A') {
				suunta='O';
				xSuunta=1;
				ySuunta=0;	
			}
			else if(suunta == 'O') {
				suunta='Y';
				xSuunta = 0;
				ySuunta = -1;
			}
			else if(suunta == 'V') {
				suunta='A';
				xSuunta=0;
				ySuunta=1;
			}
		}
		/*
		 * Jos väri on valkoinen, käännetään aina 90 astetta myötäpäivään.
		 */
		else if(vari.equals("VALKOINEN")) {
			kentta[x][y] = VALKOINEN;
			if(suunta == 'Y') {
				suunta='O';
				xSuunta=1;
				ySuunta=0;
			}
			else if(suunta == 'A') {
				suunta='V';
				xSuunta=-1;
				ySuunta=0;
			}
			else if(suunta == 'O') {
				suunta='A';
				xSuunta=0;
				ySuunta=1;
			}
			else if(suunta == 'V') {
				suunta='Y';
				xSuunta = 0;
				ySuunta = -1;
			}
		}
		/*
		 * Tarkistetaan, että muurahainen on rajojen sisällä
		 */
		if ((x + xSuunta <= leveys-2 && y + ySuunta <= korkeus-2) && (x + xSuunta >= 0 && y + ySuunta >= 0)) {
			/*
			 * Jos muurahainen menossa seuraavaksi mustaan ruutuun, vaihdetaan sen väri valkoiseksi
			 */
			if(kentta[x+xSuunta][y+ySuunta] == MUSTA) {
				vari="VALKOINEN";
			}
			/*
			 * Jos menossa seuraavaksi valkoiseen, vaihdetaan sen väri mustaksi
			 */
			else if(kentta[x+xSuunta][y+ySuunta] == VALKOINEN) {
				vari="MUSTA";
			}
			/*
			 * Jos pysytään rajojen sisällä, seuraavaksi astuttava ruutu on muurahaisen uusi sijainti
			 */
			kentta[x+xSuunta][y+ySuunta]=MUURAHAINEN;
		}
		/*
		 * Muurahaisen seuraava askel on paneelin ulkopuolella, pysäytetään ohjelma
		 */
		else {
			Main.kaynnissa=false;
			Paneeli.loppu();
		}
	}
}
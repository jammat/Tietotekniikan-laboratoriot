import java.util.*;

public class Peli {
	private int leveys; //mainilta/ruudusta saatava leveys
	private int korkeus; //mainilta/ruudusta saatava leveys
	int xPiste; //x-koordinaatti uudelle pisteelle
	int yPiste; //y-koordinaatti uudelle pisteelle
	int pisteet; //kerätyt pisteet
	int pituus = 4; //pituus aluksi
	char suunta = 'V'; //suunta aluksi
	Random satunnainen = new Random();
	final int x[] = new int[Ruutu.LEVEYS]; //Alustetaan kenttä
	final int y[] = new int[Ruutu.KORKEUS]; //Alustetaan kenttä
	/*
	 * Alustetaan madon sijainti alussa.
	 */
	Peli(int leveys, int korkeus){
		this.leveys=leveys;
		this.korkeus=korkeus;
		satunnainen = new Random();
		alusta();
	}
	/*
	 * Madon sijainti suurin piirtein keskellä aluksi.
	 */
	public void alusta() {
		for (int i=0;i<pituus;i++) {
			x[i] = Ruutu.PIKSELIKOKO*8;
			y[i] = Ruutu.PIKSELIKOKO*8;
		}
	}
	/*
	 * Liikutaan nuolinäppäimistöltä saatavaan suuntaan taulukossa. Madon ruumis seuraa päätä.
	 */
	public void liiku() {
		for(int i=pituus;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if(suunta == 'Y') {
			y[0] = y[0] - Ruutu.PIKSELIKOKO;
		}
		if(suunta == 'A') {
			y[0] = y[0] + Ruutu.PIKSELIKOKO;
		}
		if(suunta == 'V') {
			x[0] = x[0] - Ruutu.PIKSELIKOKO;
		}
		if(suunta == 'O') {
			x[0] = x[0] + Ruutu.PIKSELIKOKO;
		}
	}
	/*
	 * Asetetaan piste pelialueelle (yläosasta jätetään 2*PIKSELIKOKOa huomiotta, sillä alue sisältää pisteet). Jos piste asetettaisiin madon sisään, haetaan uusi piste.
	 */
	public void uusiPiste() {
		xPiste = satunnainen.nextInt(leveys/Ruutu.PIKSELIKOKO)*Ruutu.PIKSELIKOKO;
		yPiste = satunnainen.nextInt(((korkeus)-(2*Ruutu.PIKSELIKOKO))/Ruutu.PIKSELIKOKO)*Ruutu.PIKSELIKOKO+2*Ruutu.PIKSELIKOKO;
		for(int i=pituus;i>0;i--) {
			if (xPiste == x[i] && yPiste == y[i]) {
				uusiPiste();
			}
		}
	}
	/*
	 * Jos madon pää on samassa kohdassa kuin piste, lisätään pisteitä ja pituutta ja luodaan uusi piste.
	 */
	public void pisteTarkastus() {
		if((x[0]==xPiste) && y[0] == yPiste) {
			pituus++;
			pisteet++;
			uusiPiste();
		}
	}
	/*
	 * Jos madon pää osuu vartaloon, tai jos mato on menossa pelialueen ulkopuolelle, pysäytetään peli.
	 */
	public void osumaTarkastus() {
		for(int i=pituus;i>0;i--) {
			if((x[0]==x[i])&&y[0]==y[i]) {
				Ruutu.kaynnissa = false;
			}
		}
		if (x[0] < 0 || x[0] > leveys || y[0] < Ruutu.PIKSELIKOKO*2 || y[0] > korkeus-Ruutu.PIKSELIKOKO) {
			Ruutu.kaynnissa = false;
		}
	}
}

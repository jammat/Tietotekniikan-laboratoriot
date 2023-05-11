import java.awt.*;
import javax.swing.*;

public class Paneeli extends JFrame{
	private static final long serialVersionUID = 1L;
	Langton murkku; //logiikka
	JPanel paneeli; //paneeli
	private final int PIKSELIKOKO = 8;//pikselikoon kerroin
	boolean kaynnissa; //ohjelma kaynnissa
	/*
	 * Alustetaan ruutu ja paneeli.
	 */
	public Paneeli(Langton murkku) {
		this.murkku = murkku;
		setSize(520,540);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Langton");
		paneeli = new JPanel();
		setResizable(false);
		setLocationRelativeTo(null);
		add(paneeli);
		kaynnissa = true;
		}
	/*
	 * Jos viive on positiivinen numero, käynnistä ohjelma. Jos ei, lopeta ohjelma
	 */
	public void piirra(int viive) throws InterruptedException {
		if(kaynnissa) {
			murkku.kaynnissa();
			Thread.sleep(viive);
		}
		else {
			System.exit(-1);
		}
		Graphics g = paneeli.getGraphics();
		for (int i=0;i<murkku.leveys;i++) {
			for (int j=0;j<murkku.korkeus;j++) {
				/*
				 * Jos ruutu on musta -> valkoinen
				 */	
				if(murkku.kentta[i][j] == murkku.MUSTA) {
					g.setColor(Color.white);
					g.fillRect(i*PIKSELIKOKO, j*PIKSELIKOKO, PIKSELIKOKO, PIKSELIKOKO);
				}
				/*
				 * Jos ruutu on valkoinen -> musta
				 */
				else if(murkku.kentta[i][j] == murkku.VALKOINEN) {
					g.setColor(Color.black);
					g.fillRect(i*PIKSELIKOKO, j*PIKSELIKOKO, PIKSELIKOKO, PIKSELIKOKO);
				}
				/*
				 * Jos ruutu sisältää muurahaisen
				 */
				else if(murkku.kentta[i][j] == murkku.MUURAHAINEN) {
					g.setColor(Color.red);
					g.fillOval(i*PIKSELIKOKO, j*PIKSELIKOKO, PIKSELIKOKO, PIKSELIKOKO);
				}
			}
		}
	}
	/*
	 * Jos muurahainen menossa seuraavaksi ruudun ulkopuolelle, näytä viesti ja lopeta ohjelma
	 */
	public static void loppu() {
		JOptionPane.showMessageDialog(null, "Simulaatio ohi", "Huomio", JOptionPane.ERROR_MESSAGE);
		System.exit(-1);
	}
}
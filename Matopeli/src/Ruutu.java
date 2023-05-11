import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Ruutu extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Peli mato;
	JPanel ruutu;
	static final int LEVEYS = 500; //pikseliä
	static final int KORKEUS = 500; //pikseliä
	static final int PIKSELIKOKO = 30; //yhden pikselin koko
	final int VIIVE = 150; //ms
	Timer ajastin;
	static boolean kaynnissa = false; //ohjelma käynnissä
	boolean painettu = false; //näppäintä painettu
	/*
	 * Lisää paneelin ruutuun. Parametrit raamille.
	 */
	public Ruutu(Peli mato) {
		this.mato = mato;
		setTitle("Matopeli");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ruutu = new JPanel();
		setResizable(false);
		ruutu.setBackground(Color.black);
		setSize(LEVEYS+PIKSELIKOKO,KORKEUS+20);
		addKeyListener(new nappainTapahtuma());
		setLocationRelativeTo(null);
		add(ruutu);
		kaynnissa = true;
	}
	public void piirra() {
		if(kaynnissa) {
			/*
			 * Aloittaa pelin lisäämällä uuden pisteen kentälle. Käynnistää ajastimen viiveelle.
			 */
			mato.uusiPiste();
			ajastin = new Timer(VIIVE, this);
			ajastin.start();
			while(kaynnissa) {
				Graphics g = ruutu.getGraphics();
				/*
				 * Piirtää kentän ääriviivat.
				 */
				g.setColor(Color.white);
				g.drawRect(0, PIKSELIKOKO*2, 515, 425);
				/*
				 * Piirtää pisteen kentälle.
				 */
				g.setColor(Color.red);
				g.fillRect(mato.xPiste+PIKSELIKOKO/4, mato.yPiste+PIKSELIKOKO/4, PIKSELIKOKO/2, PIKSELIKOKO/2);
				/*
				 * Piirtää madon kentälle. Jos pää, piirtää pikselin kokoisen ruudun. Jos vartalo, piirtää puolikkaan pikselin keskelle ruutua.
				 */
				for(int i=0;i<mato.pituus;i++) {
					if(i==0) {
						g.setColor(Color.white);
						g.fillRect(mato.x[i], mato.y[i], PIKSELIKOKO, PIKSELIKOKO);
					}
					else {
						g.setColor(Color.white);
						g.fillRect(mato.x[i]+PIKSELIKOKO/4, mato.y[i]+PIKSELIKOKO/4, PIKSELIKOKO/2, PIKSELIKOKO/2);
					}
				}
				/*
				 * Piirtää pistetilanteen ruudun yläosaan keskelle, pelikentän ulkopuolelle.
				 */
				g.setColor(Color.red);
				g.setFont(new Font("Roboto",Font.PLAIN,40));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Pisteet: "+mato.pisteet, (LEVEYS-metrics.stringWidth("Pisteet: "+mato.pisteet))/2, PIKSELIKOKO);
			}
		}
		else {
			
			/*
			 * Lopetetaan ajastin, piirretään loppu.
			 */
			ajastin.stop();
			loppu();
			Main.kaynnissa = false;
		}
	}
	public void loppu() {
		Graphics g = ruutu.getGraphics();
		/*
		 * Piirtää keskelle ruutua "Peli ohi" -tekstin.
		 */
		g.setColor(Color.red);
		g.setFont(new Font("Chiller",Font.BOLD,75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Peli Ohi", (LEVEYS-metrics1.stringWidth("Peli Ohi"))/2, KORKEUS/2);
		/*
		 * Piirtää lopullisen piustetilanteen samalle paikalle, kuin pisteetkin.
		 */
		g.setColor(Color.gray);
		g.setFont(new Font("Roboto",Font.PLAIN,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Lopputulos: "+mato.pisteet, (LEVEYS-metrics2.stringWidth("Lopputulos: "+mato.pisteet))/2, PIKSELIKOKO);
	}
	/*
	 * Hakee näppäinpainallukset. Käytössä nuolinäppäimet. Jos vastakkainen suunta, älä anna painaa.
	 */
	public class nappainTapahtuma extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(!painettu) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(mato.suunta != 'O') {
						mato.suunta = 'V';
						painettu = true;
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(mato.suunta != 'V') {
						mato.suunta = 'O';
						painettu = true;
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					if(mato.suunta != 'A') {
						mato.suunta = 'Y';
						painettu = true;
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if(mato.suunta != 'Y') {
						mato.suunta = 'A';
						painettu = true;
					}
				}
			}
		}
	}
	/*
	 * Mitä tehdään ajastimen jokaisella ruudunpäivityksellä
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(kaynnissa) {
			mato.liiku();
			mato.pisteTarkastus();
			mato.osumaTarkastus();
			painettu = false;
			repaint();
		}	
	}
}

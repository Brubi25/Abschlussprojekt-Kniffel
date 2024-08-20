package kniffel;

public class Game {
	private Spieler[] spieler;
	private int anzSpieler;
	private int curSpieler;
	private Wurf curWurf;
	
	Game(){
		spieler = new Spieler[6];
		anzSpieler = 0;
	}
	/**
	 * Spielt Hand
	 * @param hand
	 * @return true falls erfolgreich, false sonst
	 */
	public boolean handSpielen(String hand) {
		if(spieler[curSpieler].play(hand, this.curWurf)) {
			nextPlayer();
			return true;
		}
		return false;
	}
	
	public int getWert(String hand) {
		return spieler[curSpieler].getWert(hand, curWurf);
	}
	
	public int nextPlayer() {
		return curSpieler = (curSpieler + 1) % spieler.length;
	}
	
	public int getCurSpieler() {
		return this.curSpieler;
	}
	
	public Wurf getCurWurf() {
		return this.curWurf;
	}
	
	public Wurf wurfeln(boolean[] wurfel) {
		if(this.curWurf == null) {
			this.wurfeln();
			return this.curWurf;
		}
		this.curWurf.neuWuerfeln(wurfel);
		return this.curWurf;
	}
	
	public Wurf wurfeln() {
		return this.curWurf = Wurf.wuerfeln();
	}
	
	public void addPlayer(String name) {
		if(anzSpieler < spieler.length) {
			spieler[anzSpieler++] = new Spieler(name);
		}
	}
	
	public int removePlayer(String name) {
		int pos;
		int pos_gefunden = Integer.MAX_VALUE;
		for(pos = 0; pos < anzSpieler; pos++) {
			if(spieler[pos].getName().equals(name)) {
				spieler[pos] = null;
				anzSpieler--;
				pos_gefunden = pos; //index übergeben wo gefunden
				break;
			}
		}
		for(; pos < anzSpieler; pos++) {
			spieler[pos] = spieler[pos+1];
		}
		
		return pos_gefunden;
	}
	
	
	
	//nächste FEature 1
	//eigentlich Nutzlos
	public void printAll(Wurf W) {
		for(int i = 0; i < anzSpieler; i++) {
			spieler[i].print(W);
		}
	}
	
	public int getAnzahlSpieler() {
		return this.anzSpieler;
	}
	
	public static void main(String[] s) {
		Game g = new Game();
		KniffelGUI gui = new KniffelGUI(g);
		
		//nur für testen
		Wurf test = g.wurfeln(); 
		System.out.println(test);
		boolean[] t = {false, true, true, true, false};
		test = g.wurfeln(t);
		System.out.println(test);
		gui.setVisible(true);
	}
}

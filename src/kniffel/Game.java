package kniffel;

public class Game {
	private Spieler[] spieler;
	private int anzSpieler;
	private int curSpieler;
	private Wurf curWurf;
	private int anzGewurfelt;
	
	Game(){
		spieler = new Spieler[6];
		anzSpieler = 0;
		curSpieler = 0;
		anzGewurfelt = 0;
	}
	
	/**
	 * Spielt Hand
	 * @param hand
	 * @return true falls erfolgreich, false sonst
	 */
	public boolean handSpielen(String hand) {
		if(spieler[curSpieler].play(hand, this.curWurf)) {
			return true;
		}
		return false;
	}
	
	public int getWert(String hand) {
		return spieler[curSpieler].getWert(hand, curWurf);
	}
	
	public int getGespielterWert(String hand) {
		return spieler[curSpieler].getGespielterWert(hand);
	}
	
	public boolean isPlayeble(String hand) {
		return spieler[curSpieler].isPlayeble(hand);
	}
	
	public int nextPlayer() {
		this.curWurf = null;
		return curSpieler = (curSpieler + 1) % anzSpieler;
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
			anzGewurfelt++;
			return this.curWurf;
		}
		this.curWurf.neuWuerfeln(wurfel);
		anzGewurfelt++;
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
	
	public int setCurSpieler(String name) {
		for(int i = 0; i < anzSpieler; i++) {
			if(spieler[i].getName().equals(name)){
				this.curSpieler = i;
				return this.curSpieler;
			}
		}
		return -1;
	}
	
	public void setCurWurf(Wurf wurf) {
		this.curWurf = wurf;
	}
	
	public int getAnzahlSpieler() {
		return this.anzSpieler;
	}
	
	public int getAnzGewurfelt() {
		return this.anzGewurfelt;
	}
	
	public void resetAnzGewurfelt() {
		this.anzGewurfelt = 0;
	}
	
	public boolean isFinished() {
		for(int i = 0; i < this.anzSpieler; i++) {
			if(!spieler[i].isFinished()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Ermittelt Gewinner
	 * @return -1 falls Spiel nicht zuende, Gewinner id sonst
	 */
	public int getWinner() {
		if(!this.isFinished()) {
			return -1;
		}
		int gewinner = 0;
		int punkte = 0;
		
		for(int i = 0; i < anzSpieler; i++) {
			if(spieler[i].getGesamt() > punkte) {
				gewinner = i;
				punkte = spieler[i].getGesamt();
			}
		}
		
		return gewinner;
	}
	
	public String getName(int index) {
		return spieler[index].getName();
	}
}
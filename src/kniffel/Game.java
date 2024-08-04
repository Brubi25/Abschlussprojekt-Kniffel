package kniffel;

public class Game {
	private Spieler[] spieler;
	private int anzSpieler;
	
	Game(){
		spieler = new Spieler[6];
		anzSpieler = 0;
	}
	
	public void addPlayer(String name) {
		if(anzSpieler < spieler.length) {
			spieler[anzSpieler++] = new Spieler(name);
		}
	}
	
	public void removePlayer(String name) {
		int pos;
		for(pos = 0; pos < anzSpieler; pos++) {
			if(spieler[pos].getName() == name) {
				spieler[pos] = null;
				anzSpieler--;
				break;
			}
		}
		for(; pos < anzSpieler; pos++) {
			spieler[pos] = spieler[pos+1];
		}
	}
	
	//eigentlich Nutzlos
	public void printAll(Wurf W) {
		for(int i = 0; i < anzSpieler; i++) {
			spieler[i].print(W);
		}
	}
	
	public static void main(String[] s) {
		Game g = new Game();
		g.addPlayer("Vincent");
		g.addPlayer("Bruno");
		g.removePlayer("Vincent");
		g.addPlayer("Maxim");
		g.addPlayer("Vincent");
		g.printAll(new Wurf(new int[] {1,1,1,1,1}));
	}
}

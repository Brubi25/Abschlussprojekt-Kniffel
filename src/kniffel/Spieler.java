package kniffel;

import java.util.Hashtable;
import java.util.Iterator;

public class Spieler {
	private String name;
	private Hashtable<String, DarstellbarerWert> Haende; 
	
	public Spieler(String name) {
		this.name = name;
		
		Haende = new Hashtable<String, DarstellbarerWert>();
		Haende.put("Aces", Einsen.getInstance());
		Haende.put("Twos", Zweien.getInstance());
		Haende.put("Threes", Dreien.getInstance());
		Haende.put("Fours", Vieren.getInstance());
		Haende.put("Fives", Fuenfen.getInstance());
		Haende.put("Sixes", Sechsen.getInstance());
		Haende.put("Three Of A Kind", DreierPasch.getInstance());
		Haende.put("Four Of A Kind", ViererPasch.getInstance());
		Haende.put("Full House", FullHouse.getInstance());
		Haende.put("Small Straight", KleineStrasse.getInstance());
		Haende.put("Large Straight", GrosseStrasse.getInstance());
		Haende.put("Yahtzee", Kniffel.getInstance());
		Haende.put("Chance", Chance.getInstance());
		Haende.put("Total", new FesterWert(0));
		Haende.put("Total upper section", new FesterWert(0));
		Haende.put("Total lower section", new FesterWert(0));
		Haende.put("Final total", new FesterWert(0));
		Haende.put("Bonus on 63 or more", new FesterWert(0));
	}
	
	public void print(Wurf W) {
		System.out.println(name + ": " + "\t" + W);
		
		Iterator<String> HandName = Haende.keySet().iterator();
		for(DarstellbarerWert Wert : Haende.values()) {
			System.out.println("\t" + HandName.next() + " (" + (Wert.isPlayable() ? "Playable" : "Not Playable") + "): " + (Wert.getValue(W) == 0 ? "---" : Wert.getValue(W)));
		}
	}
	
	public boolean play(String hand, Wurf W) {
		DarstellbarerWert Wert;
		if((Wert = Haende.get(hand)) != null && Wert.isPlayable()) {
			Haende.put(hand, new FesterWert(Wert.getValue(W)));
			this.updateSummen();
			return true;
		}
		return false;
	}
	
	public int getWert(String hand, Wurf W) {
		return Haende.get(hand).getValue(W);
	}
	
	public int getGespielterWert(String hand) {
		DarstellbarerWert Wert;
		if((Wert = Haende.get(hand)) != null && !Wert.isPlayable()) {
			return Wert.getValue(null);
		}
		return -1;
	}
	
	public boolean isPlayeble(String hand) {
		return Haende.get(hand).isPlayable();
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isFinished() {
		for(DarstellbarerWert Wert : Haende.values()) {
			if(Wert.isPlayable()) {
				return false;
			}
		}
		return true;
	}
	
	public void updateSummen() {
		int gesamtOben = 0, gesamtUnten = 0;
		String[] heandeOben = new String[] {"Aces","Two","Threes","Four","Fives","Sixes"};
		String[] heandeUnten = new String[] {"Three Of A Kind","Four Of A Kind","Full House","Small Straight","Large Straight","Yahtzee","Chance"};
		
		for(String S : heandeOben) {
			if(getGespielterWert(S) == -1) {
				continue;
			}
			gesamtOben += getGespielterWert(S);
		}
		
		for(String S : heandeUnten) {
			if(getGespielterWert(S) == -1) {
				continue;
			}
			gesamtUnten += getGespielterWert(S);
		}
		
		Haende.put("Total", new FesterWert(gesamtOben));
		
		if(gesamtOben >= 63) {
			gesamtOben += 35;
			Haende.put("Bonus on 63 or more", new FesterWert(35));
		}
		
		Haende.put("Total upper section", new FesterWert(gesamtOben));
		Haende.put("Total lower section", new FesterWert(gesamtUnten));
		Haende.put("Final total", new FesterWert(gesamtUnten + gesamtOben));
	}
	
	public int getGesamt() {
		return this.getGespielterWert("Final total");
	}
}

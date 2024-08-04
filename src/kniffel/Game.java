package kniffel;

public class Game {
	public static void main(String[] arg) {
		Spieler p1 = new Spieler("Bruno");
		Wurf W = Wurf.wuerfeln();
		p1.print(W);
		W.neuWuerfeln(new boolean[] {false,true,true,false,false});
		p1.print(W);
	}
}

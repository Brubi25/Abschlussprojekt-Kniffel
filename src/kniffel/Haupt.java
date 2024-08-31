package kniffel;

public class Haupt {

	public static void main(String[] s) {
		Game g = new Game();
		KniffelGUI gui = new KniffelGUI(g);
		gui.setVisible(true);
	}
}
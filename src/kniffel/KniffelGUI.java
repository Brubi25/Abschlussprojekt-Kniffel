package kniffel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class KniffelGUI extends JFrame{
	
	public void WuerfelMalen(int num) {
		
	}
	private Game spiel;
	
	//Tabelle mit Werten
	private JPanel Panel1;
	private JTable Tabelle;
	private DefaultTableModel Data;
	private TableColumnModel ColumnModel;
	private JScrollPane ScrollPane;
	private ListSelectionModel lsm;
	private String[] SpaltenBeschriftung = new String[8];
	private Object[][] ReihenBeschriftung = {

			{"Einsen", "nur Einser zaehlen", null, null, null, null, null, null},
			{"Zweien", "nur Zweier zaehlen", null, null, null, null, null, null},
			{"Dreien", "nur Dreier zaehlen", null, null, null, null, null, null},
			{"Vieren", "nur Vierer zaehlen", null, null, null, null, null, null},
			{"Fünfen", "nur Fuenfer zaehlen", null, null, null, null, null, null},
			{"Sechsen", "nur Sechser zaehlen", null, null, null, null, null, null},
			{"gesamt", "          -------->", null, null, null, null, null, null},
			{"Bonus bei 63 oder mehr", "plus 35", null, null, null, null, null, null},
			{"gesamt oberer Teil", "          -------->", null, null, null, null, null, null},
			{"Dreierpasch", "Alle Augen zaehlen", null, null, null, null, null, null},
			{"Viererpasch", "Alle Augen zaehlen", null, null, null, null, null, null},
			{"Full House", "25 Punkte", null, null, null, null, null, null},
			{"Kleine Straße", "30 Punkte", null, null, null, null, null, null},
			{"Große Straße", "40 Punkte", null, null, null, null, null, null},
			{"Kniffel", "50 Punkte", " ", null, null, null, null, null, null},
			{"Chance", "alle Augen zaehlen", null, null, null, null, null, null},
			{"gesamt unterer Teil", "          -------->", null, null, null, null, null, null},
			{"gesamt oberer Teil", "          -------->", null, null, null, null, null, null},
			{"Endsumme", "          -------->", null, null, null, null, null, null},
			};
	private DefaultTableCellRenderer DTCF;
	
	//vor Spielstart mit Hinzufügen/Entfernen von Spielern und Startbutton
	private JPanel Panel2;
	private JButton Hinzufuegen;
	private JTextField HTextfeld;
	private JButton Entfernen;
	private JTextField ETextfeld;
	private JButton Start;
	
	//während Spiel
	private JPanel Panel3;
	private GridBagLayout Gridbaglayout;
	private GridBagConstraints Gridbagconstraints;
	private Insets Insets;
	private boolean[] WuerfelReRoll = {true, true, true, true, true}; //true  == reroll
	private JButton Wuerfel1;
	private JButton Wuerfel2;
	private JButton Wuerfel3;
	private JButton Wuerfel4;
	private JButton Wuerfel5;
	private JButton Wuerfeln;
	private Color toggleOff = new Color(110, 106, 105);
	private Color toggleOn = new Color(222, 106, 216);		
	private Wurf wurf;
	private JButton debugger;
	private JButton ZugBestaetigen;
	private int selRow;
	
	//Debugger
	private JPanel Panel4;
	private JButton Einschreiben;
	private JLabel Ls;
	private JTextField Spieler;
	private JLabel Lh;
	private JTextField Hand;
	private JLabel lW1;
	private JTextField W1;
	private JLabel lW2;
	private JTextField W2;
	private JLabel lW3;
	private JTextField W3;
	private JLabel lW4;
	private JTextField W4;
	private JLabel lW5;
	private JTextField W5;
	private JButton Exit;
	
	
	public KniffelGUI(Game spiel){
		this.spiel = spiel;
		this.setSize(900, 900);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Kniffel");
		this.setLayout(new GridLayout(2,1));
		
		Panel1 = new JPanel();
		Panel1.setLayout(new BorderLayout());
		
		Panel2 = new JPanel();
		Panel2.setLayout(null);
		
		Panel3 = new JPanel();
		Gridbaglayout = new GridBagLayout();
		Gridbagconstraints = new GridBagConstraints();
		Gridbaglayout.setConstraints(Panel3, Gridbagconstraints);
		Insets = new Insets(20,20,20, 20);
		Gridbagconstraints.insets = Insets;
		Panel3.setLayout(Gridbaglayout);
		
		Panel4 = new JPanel();
		Gridbaglayout.setConstraints(Panel4, Gridbagconstraints);
		Panel4.setLayout(Gridbaglayout);
		
		
		this.add(Panel1);
		this.add(Panel2);
		
		//Panel1 -> Tabelle
		SpaltenBeschriftung[0] = "";
		SpaltenBeschriftung[1] = "";
		for(int i = 2; i < SpaltenBeschriftung.length; i++) {
			SpaltenBeschriftung[i] = null;
		}
		
		Data = new DefaultTableModel(ReihenBeschriftung, SpaltenBeschriftung);
		Tabelle = new JTable(Data) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		Tabelle.setColumnSelectionAllowed(false);
		Tabelle.setRowSelectionAllowed(false);
		ScrollPane = new JScrollPane(Tabelle);
		Tabelle.getTableHeader().setReorderingAllowed(false);
		
		ColumnModel = Tabelle.getColumnModel();
		TabelleFormatieren();
		
		DTCF = new DefaultTableCellRenderer();
		DTCF.setBackground(Color.red);
		
		Panel1.add(ScrollPane, BorderLayout.CENTER);
		
		//Panel2 -> vor Spielstart Interface
		Panel2.setBackground(Color.blue);
				
		HTextfeld = new JTextField();
		HTextfeld.setBounds(10, 20, 100, 20);
		Panel2.add(HTextfeld);
		
		Hinzufuegen = new JButton("Spieler hinzufügen");
		Hinzufuegen.setBounds(130, 20, 150, 20);
		Panel2.add(Hinzufuegen);
		Hinzufuegen.addActionListener(e -> spielerdazu());	
		
		ETextfeld = new JTextField();
		ETextfeld.setBounds(320, 20, 100, 20);
		Panel2.add(ETextfeld);
		
		Entfernen = new JButton("Spieler entfernen");
		Entfernen.setBounds(430, 20, 150, 20);
		Panel2.add(Entfernen);
		Entfernen.addActionListener(e -> spielerweg());
		
		Start = new JButton("Spiel starten");
		Start.setBounds(600, 20, 150, 20);
		Panel2.add(Start);
		Start.addActionListener(e -> spielstarten());
		
		//Panel3 -> während Spiel Interface
		Panel3.setBackground(Color.green);
		Wuerfel1 = new JButton(" ");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel1, Gridbagconstraints);
		Wuerfel1.setBackground(toggleOn);
		Wuerfel1.addActionListener(e -> ToggleOnOff(Wuerfel1, 0, WuerfelReRoll));
	
		Wuerfel2 = new JButton(" ");
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel2, Gridbagconstraints);
		Wuerfel2.setBackground(toggleOn);
		Wuerfel2.addActionListener(e -> ToggleOnOff(Wuerfel2, 1, WuerfelReRoll));
		
		Wuerfel3 = new JButton(" ");
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel3, Gridbagconstraints);
		Wuerfel3.setBackground(toggleOn);
		Wuerfel3.addActionListener(e -> ToggleOnOff(Wuerfel3, 2, WuerfelReRoll));
		
		Wuerfel4 = new JButton(" ");
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel4, Gridbagconstraints);
		Wuerfel4.setBackground(toggleOn);
		Wuerfel4.addActionListener(e -> ToggleOnOff(Wuerfel4, 3, WuerfelReRoll));
		
		Wuerfel5 = new JButton(" ");
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel5, Gridbagconstraints);
		Wuerfel5.setBackground(toggleOn);
		Wuerfel5.addActionListener(e -> ToggleOnOff(Wuerfel5, 4, WuerfelReRoll));
		
		Wuerfeln = new JButton("Würfeln");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfeln, Gridbagconstraints);
		Wuerfeln.addActionListener(e -> GUI_wuerfeln(WuerfelReRoll));
	
		debugger = new JButton("Debug-Modus");
		Gridbagconstraints.gridx = 6;
		Gridbagconstraints.gridy = 0;
		Panel3.add(debugger, Gridbagconstraints);
		debugger.addActionListener(e -> debug());
		
		ZugBestaetigen = new JButton("Zug bestätigen");
		Gridbagconstraints.gridx = 7;
		Gridbagconstraints.gridy = 0;
		Panel3.add(ZugBestaetigen, Gridbagconstraints);
		ZugBestaetigen.addActionListener(e -> GUI_ZugBestaetigen());
		
		//Panel 4 -> Debug-Modus
		Panel4.setBackground(Color.MAGENTA);
		Gridbagconstraints.fill = GridBagConstraints.BOTH;
		Gridbagconstraints.insets = new Insets(5, 5, 5, 5);
		lW1 = new JLabel("Würfel 1");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 0;
		Panel4.add(lW1, Gridbagconstraints);

		lW2 = new JLabel("Würfel 2");
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 0;
		Panel4.add(lW2, Gridbagconstraints);
		
		lW3 = new JLabel("Würfel 3");
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 0;
		Panel4.add(lW3, Gridbagconstraints);

		lW4 = new JLabel("Würfel 4");
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 0;
		Panel4.add(lW4, Gridbagconstraints);

		lW5 = new JLabel("Würfel 5");
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 0;
		Panel4.add(lW5, Gridbagconstraints);
		
		W1 = new JTextField();
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 1;
		Panel4.add(W1, Gridbagconstraints);
		
		W2 = new JTextField();
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 1;
		Panel4.add(W2, Gridbagconstraints);
		
		W3 = new JTextField();
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 1;
		Panel4.add(W3, Gridbagconstraints);
		
		W4 = new JTextField();
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 1;
		Panel4.add(W4, Gridbagconstraints);
		
		W5 = new JTextField();
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 1;
		Panel4.add(W5, Gridbagconstraints);
	
		Ls = new JLabel("Spieler");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Ls, Gridbagconstraints);
		
		Spieler = new JTextField();
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Spieler, Gridbagconstraints);
		
		Lh = new JLabel("Hand");
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Lh, Gridbagconstraints);
		
		Hand = new JTextField();
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 2;
		
		Panel4.add(Hand, Gridbagconstraints);
		
		Einschreiben = new JButton("Werte einschreiben");
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Einschreiben, Gridbagconstraints);
		Einschreiben.addActionListener(e -> debug_einschreiben());
		
		Exit = new JButton("Zurück");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 1;
		Panel4.add(Exit, Gridbagconstraints);
		Exit.addActionListener(e -> debug_exit());
	}
	
	
	/*
	 * Nimmt String aus Textfeld und fügt Tabelle + Array hinzu, wenn Spieleranzahl < 6
	 */
	private void spielerdazu() {
		String name = HTextfeld.getText();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "Keinen Namen vergeben.");
		}else if(spiel.getAnzahlSpieler() < 6) {
			spiel.addPlayer(name); //added Player in Backend
			int col = spiel.getAnzahlSpieler() + 1;
			SpaltenBeschriftung[col] = name;
			Data.setDataVector(ReihenBeschriftung, SpaltenBeschriftung);
			TabelleFormatieren();
			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " hinzugefügt");
		}else {
			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Maximale Spieleranzahl erreicht.");
		}
	}
	
	/*
	 * Entfernt Spieler aus Tabelle + Array, sofern vorhanden in Array
	 * 
	 */
	private void spielerweg() {
		String name = ETextfeld.getText();
		int spielerpos = spiel.removePlayer(name); // löscht Spieler und returnt position in Backend Array
		if(spielerpos != Integer.MAX_VALUE) {
			spielerpos += 2;
			SpaltenBeschriftung[spielerpos] = null;
			int SpielerAnzahl  = spiel.getAnzahlSpieler();
			for(int i = spielerpos; i < SpielerAnzahl+2; i++){ //+2 wegen ersten zwei Spalten = ""
				String temp = SpaltenBeschriftung[i];
				SpaltenBeschriftung[i] = SpaltenBeschriftung[i+1];
				SpaltenBeschriftung[i+1] = temp;
			}
			Data.setDataVector(ReihenBeschriftung, SpaltenBeschriftung);
			TabelleFormatieren();
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " gelöscht.");
		} else {
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler nicht vorhanden.");
		}
	}
	
	/*
	 * Entfernt Panel für Hinzufügen/Entfernen von Spielern und Starten
	 * Fügt Spielpanel mit Würfeln etc hinzu
	 */ 
	private void spielstarten() {
		if(spiel.getAnzahlSpieler() != 0) {
			remove(Panel2);
			add(Panel3);
			//vllt aber gibt nur Row, kann funktionieren aber nicht so top
			lsm = Tabelle.getSelectionModel();
			lsm.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!lsm.isSelectionEmpty()) {
						selRow = lsm.getMinSelectionIndex();
					}
				}
			});
			CurSpielerMarkieren();
			revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Es müssen mehr als Null Spieler teilnehmen.");
		}
	}

	private void GUI_wuerfeln(boolean[] wuerfel) {
		if (spiel.getAnzGewurfelt() < 3) {
			wurf = spiel.wurfeln(wuerfel);
			Wuerfel1.setText(Integer.toString(wurf.get(0)));
			Wuerfel2.setText(Integer.toString(wurf.get(1)));
			Wuerfel3.setText(Integer.toString(wurf.get(2)));
			Wuerfel4.setText(Integer.toString(wurf.get(3)));
			Wuerfel5.setText(Integer.toString(wurf.get(4)));
			VorgeschlageneWerteAnzeigen();
		} else {
			JOptionPane.showMessageDialog(null, "Du kannst maximal 3 mal Würfeln");
		}
	}

	private void GUI_ZugBestaetigen() {
		String hand = (String)ReihenBeschriftung[selRow][0];
		if(spiel.handSpielen(hand)) {
			CurSpielerMarkieren();
			EingeschriebeneWerteAnzeigen();
			spiel.nextPlayer();
			CurSpielerMarkieren();
			spiel.resetAnzGewurfelt();
			GUIWuerfelReset();
		}else {
			JOptionPane.showMessageDialog(null, "Falscher Zug!");
		}
	}

	private void ToggleOnOff(JButton button, int index, boolean[] wuerfel) {
		if(wuerfel[index]) {
			wuerfel[index] = false;
			button.setBackground(toggleOff);
		} else {
			wuerfel[index] = true;
			button.setBackground(toggleOn);
		}
	}
	
	private void debug() {
		EingeschriebeneWerteAnzeigen();
		remove(Panel3);
		add(Panel4);
		revalidate();
		repaint();
	}
	
	private void debug_exit() {
		remove(Panel4);
		add(Panel3);
		revalidate();
		repaint();
	}

	private void debug_einschreiben() {
		int[] debug_zahlen = {Integer.parseInt(W1.getText()), Integer.parseInt(W2.getText()), Integer.parseInt(W3.getText()), Integer.parseInt(W4.getText()), Integer.parseInt(W5.getText())};
		Wurf debug_wurf = new Wurf(debug_zahlen);
		spiel.setCurWurf(debug_wurf);
		spiel.setCurSpieler(Spieler.getText());
		if(spiel.handSpielen(Hand.getText())) {
			EingeschriebeneWerteAnzeigen();
		} else {
			JOptionPane.showMessageDialog(null, "Falsche Eingabe");
		}
	}
	
	private void CurSpielerMarkieren() {
		if(ColumnModel.getColumn(spiel.getCurSpieler()+2).getHeaderRenderer()==DTCF) {
			ColumnModel.getColumn(spiel.getCurSpieler()+2).setHeaderRenderer(ColumnModel.getColumn(0).getHeaderRenderer());
		} else {
			ColumnModel.getColumn(spiel.getCurSpieler()+2).setHeaderRenderer(DTCF);
		}
		repaint();
	}
	
	private void EingeschriebeneWerteAnzeigen() {
		int spieler = spiel.getCurSpieler();
		for(int i = 0; i < 16; i++) {
			if(i == 6) {
				i += 3;
			}
			Data.setValueAt(spiel.getGespielterWert((String)ReihenBeschriftung[i][0]), i, spieler+2);
			
		}
	}
	
	private void VorgeschlageneWerteAnzeigen() {
		int spieler = spiel.getCurSpieler();
		for(int i = 0; i < 16; i++) {
			if(i == 6) {
				i += 3;
			}
			Data.setValueAt(spiel.getWert((String)ReihenBeschriftung[i][0]), i, spieler+2);
		}
	}
	
	private void TabelleFormatieren() {
		for(int i = 0; i < SpaltenBeschriftung.length; i++) {
			if(SpaltenBeschriftung[i] == null) {
				ColumnModel.getColumn(i).setMinWidth(0);
				ColumnModel.getColumn(i).setMaxWidth(0);
				ColumnModel.getColumn(i).setWidth(0);
			}
		}
	}
	
	private void GUIWuerfelReset() {
		WuerfelReRoll = new boolean[]{true, true, true, true, true, true};
		Wuerfel1.setText(" ");
		Wuerfel1.setBackground(toggleOn);
		Wuerfel2.setText(" ");
		Wuerfel2.setBackground(toggleOn);
		Wuerfel3.setText(" ");
		Wuerfel3.setBackground(toggleOn);
		Wuerfel4.setText(" ");
		Wuerfel4.setBackground(toggleOn);
		Wuerfel5.setText(" ");
		Wuerfel5.setBackground(toggleOn);
	}
}
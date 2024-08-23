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
		Panel4.setLayout(new GridLayout(4,5));
		
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
		for(int i = 0; i < SpaltenBeschriftung.length; i++) {
			if(SpaltenBeschriftung[i] == null) {
				ColumnModel.getColumn(i).setMinWidth(0);
				ColumnModel.getColumn(i).setMaxWidth(0);
				ColumnModel.getColumn(i).setWidth(0);
			}
		}
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
		lW1 = new JLabel("Würfel 1");
		Panel4.add(lW1);

		lW2 = new JLabel("Würfel 2");
		Panel4.add(lW2);
		
		lW3 = new JLabel("Würfel 3");
		Panel4.add(lW3);

		lW4 = new JLabel("Würfel 4");
		Panel4.add(lW4);

		lW5 = new JLabel("Würfel 5");
		Panel4.add(lW5);
		
		W1 = new JTextField();
		Panel4.add(W1);
		
		W2 = new JTextField();
		Panel4.add(W2);
		
		W3 = new JTextField();
		Panel4.add(W3);
		
		W4 = new JTextField();
		Panel4.add(W4);
		
		W5 = new JTextField();
		Panel4.add(W5);
	
		Ls = new JLabel("Spieler");
		Panel4.add(Ls);
		
		Spieler = new JTextField();
		Panel4.add(Spieler);
		
		Lh = new JLabel("Hand");
		Panel4.add(Lh);
		
		Hand = new JTextField();
		Panel4.add(Hand);
		
		Einschreiben = new JButton("Werte einschreiben");
		Panel4.add(Einschreiben);
		Einschreiben.addActionListener(e -> debug_einschreiben());
		
		Exit = new JButton("Zurück");
		Panel4.add(Exit);
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
			for(int i = col+1;i < SpaltenBeschriftung.length; i++ ) {
				ColumnModel.getColumn(i).setMinWidth(0);
				ColumnModel.getColumn(i).setMaxWidth(0);
				ColumnModel.getColumn(i).setWidth(0);
			}

			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " hinzugefügt");
		}else {
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
			for(int i = 0; i < SpaltenBeschriftung.length; i++) {
				if(SpaltenBeschriftung[i] == null) {
					ColumnModel.getColumn(i).setMinWidth(0);
					ColumnModel.getColumn(i).setMaxWidth(0);
					ColumnModel.getColumn(i).setWidth(0);
				}
			}
			
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " gelöscht.");
		} else {
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
						System.out.println(selRow);
					}
				}
			});
			
			revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Es müssen mehr als Null Spieler teilnehmen.");
		}
	}

	private void GUI_wuerfeln(boolean[] wuerfel) {
		wurf = spiel.wurfeln(wuerfel);
		System.out.println(wurf);
		System.out.println(wurf.sumOfAll());
		
		Wuerfel1.setText(Integer.toString(wurf.get(0)));
		Wuerfel2.setText(Integer.toString(wurf.get(1)));
		Wuerfel3.setText(Integer.toString(wurf.get(2)));
		Wuerfel4.setText(Integer.toString(wurf.get(3)));
		Wuerfel5.setText(Integer.toString(wurf.get(4)));
	}
	
	private void GUI_ZugBestaetigen() {
		//Hand einschreiben in Tabelle
		int spieler = spiel.getCurSpieler();
		System.out.println("[gui_zugbestätigen] curSpiel " + spieler);
		System.out.println("[gui_zugbestätigen] selRow " + selRow);
		System.out.println("[gui_zugbestätigen] Reihe selRow sp+0 " + ReihenBeschriftung[selRow][spieler+0]);
		System.out.println("[gui_zugbestätigen] Reihe selRow sp+1 " +ReihenBeschriftung[selRow][spieler+1]);
		System.out.println("[gui_zugbestätigen] Reihe selRow sp+2 " +ReihenBeschriftung[selRow][spieler+2]);
		if(selRow != 6 && selRow != 7 && selRow != 8 && selRow != 16 && selRow != 17 && selRow != 18 && ReihenBeschriftung[selRow][spieler+2] == null) {
			System.out.println("[gui_zugbestätigen] GetWert " + spiel.getWert((String)ReihenBeschriftung[selRow][0]));
			Data.setValueAt(spiel.getWert((String)ReihenBeschriftung[selRow][0]), selRow, spieler+2);
			spiel.nextPlayer();
			Wuerfel1.setText(" ");
			Wuerfel2.setText(" ");
			Wuerfel3.setText(" ");
			Wuerfel4.setText(" ");
			Wuerfel5.setText(" ");
		}else {
			System.out.println("Falscher Zug");
		}
		
		int SumOben = 0;
		int SumUnten = 0;
		int SumOverall = 0;
		int SpielerAnzahl = spiel.getAnzahlSpieler();
		for(int i = 2; i < SpielerAnzahl+2; i++) {
			for(int j = 0; j < 5; j++) {
				if(Data.getValueAt(j, i) != null) {
					SumOben += (int)Data.getValueAt(j, i);
				}
			}
			
			Data.setValueAt(SumOben, 6, i);
			if(SumOben >= 63) {
				Data.setValueAt(35, 7, i);
				SumOben += 35;
				Data.setValueAt(SumOben, 8, i);
			}
			
			for(int j = 9; j < 16; j++) {
				if(Data.getValueAt(j, i) != null) {
					SumUnten += (int)Data.getValueAt(j, i);
				}
			}
			
			Data.setValueAt(SumUnten, 16, i);
			Data.setValueAt(SumOben, 17, i);
			Data.setValueAt(SumOverall, 18, i);
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
		remove(Panel3);
		add(Panel4);
		revalidate();
	}
	
	private void debug_exit() {
		remove(Panel4);
		add(Panel3);
		validate();
	}

	private void debug_einschreiben() {
		int[] debug_zahlen = {Integer.parseInt(W1.getText()), Integer.parseInt(W2.getText()), Integer.parseInt(W3.getText()), Integer.parseInt(W4.getText()), Integer.parseInt(W5.getText())};
		Wurf debug_wurf = new Wurf(debug_zahlen);
		spiel.setCurWurf(debug_wurf);
		spiel.setCurSpieler(Spieler.getText());
		int spieler = spiel.getCurSpieler();
		int Spalte = spieler+2;
		int Reihe = Integer.MAX_VALUE;
		System.out.println(ReihenBeschriftung.length);
		for(int i = 0; i < ReihenBeschriftung.length; i++) {
			if(ReihenBeschriftung[i][0].equals(Hand.getText())) {
				Reihe = i;
			}
		}
		System.out.println("[gui] Wurf: " + spiel.getCurWurf() + " Spieler: " + spiel.getCurSpieler() + " Tabellenplatz: " + Spalte);
		System.out.println("[gui] Wert: " + spiel.getWert(Hand.getText()));
		Data.setValueAt(spiel.getWert(Hand.getText()), Reihe, Spalte);
	}
}
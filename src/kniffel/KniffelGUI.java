package kniffel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class KniffelGUI extends JFrame{
	private Game spiel;
	
	//Farben definiert
	private Color FarbePanelBackground = new Color(255, 202, 212); 
	private Color FarbeCurSpieler = new Color(0, 128, 128);
	private Color FarbeVorgeschlageneWerte = new Color(103, 197, 155);
	
	//Menubar
	private JMenuBar Menubar;
	private JMenu MenuFunktion;
	private JMenu MenuInformation;
	private JMenuItem Beenden;
	private JMenuItem Regeln;
	private JMenuItem Debug;
	
	//Tabelle mit Werten
	private JPanel Panel1;
	private JTable Tabelle;
	private DefaultTableModel Data;
	private TableColumnModel ColumnModel;
	private JScrollPane ScrollPane;
	private ListSelectionModel Lsm;
	private String[] SpaltenBeschriftung = new String[8];
	private Object[][] ReihenBeschriftung = {
			{"Aces", "The sum of dice with the number 1", null, null, null, null, null, null},
			{"Twos", "The sum of dice with the number 2", null, null, null, null, null, null},
			{"Threes", "The sum of dice with the number 3", null, null, null, null, null, null},
			{"Fours", "The sum of dice with the number 4", null, null, null, null, null, null},
			{"Fives", "The sum of dice with the number 5", null, null, null, null, null, null},
			{"Sixes", "The sum of dice with the number 6", null, null, null, null, null, null},
			{"Total", "          -------->", null, null, null, null, null, null},
			{"Bonus on 63 or more", "plus 35", null, null, null, null, null, null},
			{"Total upper section", "          -------->", null, null, null, null, null, null},
			{"Three Of A Kind", "Sum of all dice", null, null, null, null, null, null},
			{"Four Of A Kind", "Sum of all dice", null, null, null, null, null, null},
			{"Full House", "25 Points", null, null, null, null, null, null},
			{"Small Straight", "30 Points", null, null, null, null, null, null},
			{"Large Straight", "40 Points", null, null, null, null, null, null},
			{"Yahtzee", "50 Points", " ", null, null, null, null, null, null},
			{"Chance", "Sum of all dice", null, null, null, null, null, null},
			{"Total lower section", "          -------->", null, null, null, null, null, null},
			{"Total upper section", "          -------->", null, null, null, null, null, null},
			{"Grand total", "          -------->", null, null, null, null, null, null},
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
	private ImageIcon[] WuerfelNeu = new ImageIcon[5];
	private JButton Wuerfeln;
	private Wurf Wurf;
	private JButton ZugBestaetigen;
	private int SelRow = Integer.MAX_VALUE;
	
	//Debugger
	private JPanel Panel4;
	private JButton Einschreiben;
	private JLabel Ls;
	private JTextField Spieler;
	private JLabel Lh;
	private JTextField Hand;
	private JLabel LW1;
	private JTextField W1;
	private JLabel LW2;
	private JTextField W2;
	private JLabel LW3;
	private JTextField W3;
	private JLabel LW4;
	private JTextField W4;
	private JLabel LW5;
	private JTextField W5;
	private JButton Exit;
	
	//Panel für Spielende
	private JPanel Panel5;
	private JLabel GewinnerLabel;
	private Font GewinnerLabelFont = new Font("Default", Font.PLAIN, 45);
	private JButton BeendenButton;
	
	public KniffelGUI(Game spiel){
		this.spiel = spiel;
		this.setSize(900, 716);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Yahtzee");
		this.setLayout(new GridLayout(2,1));
		
		Panel1 = new JPanel();
		Panel1.setLayout(new BorderLayout());
		
		Panel2 = new JPanel();
		Panel2.setLayout(null);
		
		Panel3 = new JPanel();
		Gridbaglayout = new GridBagLayout();
		Gridbagconstraints = new GridBagConstraints();
		Gridbaglayout.setConstraints(Panel3, Gridbagconstraints);
		Insets = new Insets(15,15,15,15);
		Gridbagconstraints.insets = Insets;
		Panel3.setLayout(Gridbaglayout);
		
		Panel4 = new JPanel();
		Gridbaglayout.setConstraints(Panel4, Gridbagconstraints);
		Panel4.setLayout(Gridbaglayout);
		
		Panel5 = new JPanel();
		Panel5.setLayout(new GridBagLayout());
		   
		this.add(Panel1);
		this.add(Panel2);
		
		//Menuleiste
		Menubar = new JMenuBar();
		MenuFunktion = new JMenu("Function");
		MenuInformation = new JMenu("Information");
		Beenden = new JMenuItem("Exit");
		Debug = new JMenuItem("Debug-Mode");
		Regeln = new JMenuItem("Rules");
		
		Menubar.add(MenuFunktion);
		Menubar.add(MenuInformation);
		MenuFunktion.add(Beenden);
		MenuInformation.add(Regeln);
	
		Beenden.addActionListener(e -> System.exit(0));
		Regeln.addActionListener(e -> RegelnAnzeigen());
		Debug.addActionListener(e -> debug());
		this.setJMenuBar(Menubar);
		
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
		DTCF.setBackground(FarbeCurSpieler);
		DTCF.setHorizontalAlignment(SwingConstants.CENTER);
		
		Panel1.add(ScrollPane, BorderLayout.CENTER);
		
		//Panel2 -> vor Spielstart Interface
		Panel2.setBackground(FarbePanelBackground);
				
		HTextfeld = new JTextField();
		HTextfeld.setBounds(15, 20, 150, 20);
		Panel2.add(HTextfeld);
		
		Hinzufuegen = new JButton("Add Player");
		Hinzufuegen.setBounds(190, 20, 150, 20);
		Panel2.add(Hinzufuegen);
		Hinzufuegen.addActionListener(e -> spielerdazu());	
		
		ETextfeld = new JTextField();
		ETextfeld.setBounds(365, 20, 150, 20);
		Panel2.add(ETextfeld);
		
		Entfernen = new JButton("Remove Player");
		Entfernen.setBounds(540, 20, 150, 20);
		Panel2.add(Entfernen);
		Entfernen.addActionListener(e -> spielerweg());
		
		Start = new JButton("Start Game");
		Start.setBounds(715, 20, 150, 20);
		Panel2.add(Start);
		Start.addActionListener(e -> spielstarten());
		
		//Panel3 -> während Spiel Interface
		Panel3.setBackground(FarbePanelBackground);
		
		for(int i = 0; i < WuerfelNeu.length; i++) {
			WuerfelNeu[i] = new ImageIcon(WurfelGUI.noDice);
			JLabel wuerfel = new JLabel(WuerfelNeu[i]);
			final int pos = i;
			wuerfel.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					ToggleOnOff(wuerfel,pos,WuerfelReRoll);
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {	
				}
				public void mouseEntered(MouseEvent e) {
					ToggleHover(wuerfel,pos,true, WuerfelReRoll);
				}
				public void mouseExited(MouseEvent e) {
					ToggleHover(wuerfel,pos,false, WuerfelReRoll);
				}
			});
			Panel3.add(wuerfel);
		}
		
		Wuerfeln = new JButton("Roll Dice");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfeln, Gridbagconstraints);
		Wuerfeln.addActionListener(e -> GUI_wuerfeln(WuerfelReRoll));
	
		ZugBestaetigen = new JButton("Confirm Move");
		Gridbagconstraints.gridx = 6;
		Gridbagconstraints.gridy = 0;
		Panel3.add(ZugBestaetigen, Gridbagconstraints);
		ZugBestaetigen.addActionListener(e -> GUI_ZugBestaetigen());
	
		//Panel 4 -> Debug-Modus
		Panel4.setBackground(FarbePanelBackground);
		Gridbagconstraints.fill = GridBagConstraints.BOTH;
		Gridbagconstraints.insets = new Insets(5, 5, 5, 5);
		
		LW1 = new JLabel("Dice 1");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 0;
		Panel4.add(LW1, Gridbagconstraints);

		LW2 = new JLabel("Dice 2");
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 0;
		Panel4.add(LW2, Gridbagconstraints);
		
		LW3 = new JLabel("Dice 3");
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 0;
		Panel4.add(LW3, Gridbagconstraints);

		LW4 = new JLabel("Dice 4");
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 0;
		Panel4.add(LW4, Gridbagconstraints);

		LW5 = new JLabel("Dice 5");
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 0;
		Panel4.add(LW5, Gridbagconstraints);
		
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
	
		Ls = new JLabel("Player");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Ls, Gridbagconstraints);

		Lh = new JLabel("Hand");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 3;
		Panel4.add(Lh, Gridbagconstraints);
		
		Spieler = new JTextField();
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 2;
		Gridbagconstraints.gridwidth = 4;
		Panel4.add(Spieler, Gridbagconstraints);
	
		Hand = new JTextField();
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 3;
		Panel4.add(Hand, Gridbagconstraints);
		
		Einschreiben = new JButton("Confirm");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 3;
		Panel4.add(Einschreiben, Gridbagconstraints);
		Einschreiben.addActionListener(e -> debug_einschreiben());
		
		Exit = new JButton("Back");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 2;
		Panel4.add(Exit, Gridbagconstraints);
		Exit.addActionListener(e -> debug_exit());
		
		//Panel5 Gewinnerpanel
		Panel5.setBackground(FarbePanelBackground);
		Gridbagconstraints.insets = new Insets(5,5,5,5);
		
		GewinnerLabel = new JLabel("The Winner is (insert random name)!");
		GewinnerLabel.setFont(GewinnerLabelFont);
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 0;
	    Panel5.add(GewinnerLabel, Gridbagconstraints);
	    
	    BeendenButton = new JButton("Exit Game");
	    Gridbagconstraints.gridx = 6;
		Gridbagconstraints.gridy = 0;
	    Panel5.add(BeendenButton, Gridbagconstraints);
	    BeendenButton.addActionListener(e -> System.exit(0)); 
	}

	/*
	 * Nimmt String aus Textfeld und fügt Tabelle + Array hinzu, wenn Spieleranzahl < 6
	 */
	private void spielerdazu() {
		String name = HTextfeld.getText();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "No name given.");
		}else if(spiel.getAnzahlSpieler() < 6) {
			spiel.addPlayer(name);
			int col = spiel.getAnzahlSpieler() + 1;
			SpaltenBeschriftung[col] = name;
			Data.setDataVector(ReihenBeschriftung, SpaltenBeschriftung);
			TabelleFormatieren();
			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "The Player " + name + " has been added.");
		}else {
			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Maximum number of players reached.");
		}
	}
	
	/*
	 * Entfernt Spieler aus Tabelle + Array, sofern vorhanden in Array
	 * 
	 */
	private void spielerweg() {
		String name = ETextfeld.getText();
		int spielerpos = spiel.removePlayer(name); 
		if(spielerpos != Integer.MAX_VALUE) {
			spielerpos += 2;
			SpaltenBeschriftung[spielerpos] = null;
			int SpielerAnzahl  = spiel.getAnzahlSpieler();
			for(int i = spielerpos; i < SpielerAnzahl + 2; i++){ 
				String temp = SpaltenBeschriftung[i]; 
				SpaltenBeschriftung[i] = SpaltenBeschriftung[i+1];
				SpaltenBeschriftung[i+1] = temp;
			}
			Data.setDataVector(ReihenBeschriftung, SpaltenBeschriftung);
			TabelleFormatieren();
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Player " + name + " deleted.");
		} else {
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Player not available.");
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
			//Quelle: https://www.youtube.com/watch?v=5dK4dA39INk
			Lsm = Tabelle.getSelectionModel();
			Lsm.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!Lsm.isSelectionEmpty()) {
						SelRow = Lsm.getMinSelectionIndex();
					}
				}
			});
			//Quelle Ende
			CurSpielerMarkieren();
			MenuFunktion.add(Debug);
			revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "More than zero players must participate.");
		}
	}

	private void GUI_wuerfeln(boolean[] wuerfel) {
		if (spiel.getAnzGewurfelt() < 3) {
			Wurf = spiel.wurfeln(wuerfel);
			for(int i = 0; i < WuerfelNeu.length; i++) {
				WuerfelNeu[i].setImage(wuerfel[i] ? WurfelGUI.getImage(Wurf.get(i)) : WurfelGUI.getImageGrey(Wurf.get(i)));
			}
			VorgeschlageneWerteAnzeigen();
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "You can roll the dice a maximum of 3 times.");
		}
	}

	private void GUI_ZugBestaetigen() {
		if(SelRow < ReihenBeschriftung.length) {
			String hand = (String)ReihenBeschriftung[SelRow][0];
			if(spiel.handSpielen(hand)) {
				SpielFertig();
				CurSpielerMarkieren();
				EingeschriebeneWerteAnzeigen();
				spiel.nextPlayer();
				CurSpielerMarkieren();
				spiel.resetAnzGewurfelt();
				GUIWuerfelReset();
			}else {
				JOptionPane.showMessageDialog(null, "Wrong move!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please roll the dice or select a row.");
		}
	}

	private void ToggleOnOff(JLabel button, int index, boolean[] wuerfel) {
		if(spiel.getCurWurf() == null) {
			return;
		}
		if(wuerfel[index]) {
			wuerfel[index] = false;
			WuerfelNeu[index].setImage(WurfelGUI.getImageGrey(spiel.getCurWurf().get(index)));
		} else {
			wuerfel[index] = true;
			WuerfelNeu[index].setImage(WurfelGUI.getImage(spiel.getCurWurf().get(index)));
		}
		repaint();
	}
	
	private void ToggleHover(JLabel button, int index, boolean on, boolean[] wuerfel) {
		if(spiel.getCurWurf() == null) {
			return;
		}
		if(on) {
			WuerfelNeu[index].setImage(WurfelGUI.getImageHover(spiel.getCurWurf().get(index)));
		}else {
			WuerfelNeu[index].setImage(wuerfel[index] ? WurfelGUI.getImage(spiel.getCurWurf().get(index)) : WurfelGUI.getImageGrey(spiel.getCurWurf().get(index)));
		}
		repaint();
	}
	
	private void RegelnAnzeigen() {
		String rules = "Yahtzee Rules:\n\n" +
                "1. The game consists of 13 rounds. In each round, the player may roll the dice up to three times.\n" +
                "2. After each roll, the player can choose to keep any dice and re-roll the others.\n" +
                "3. After the final roll, the player must enter a score into one of the 13 categories.\n\n" +
                "Categories and Scoring:\n" +
                "Upper Section:\n" +
                " - Aces to Sixes: Sum of the respective dice values.\n" +
                " - Bonus: 35 points for 63 or more points.\n\n" +
                "Lower Section:\n" +
                " - Three of a Kind: At least three dice of the same number. Score = Sum of all dice.\n" +
                " - Four of a Kind: At least four dice of the same number. Score = Sum of all dice.\n" +
                " - Full House: Three of one number + two of another. Score = 25 points.\n" +
                " - Small Straight: Four consecutive numbers. Score = 30 points.\n" +
                " - Large Straight: Five consecutive numbers. Score = 40 points.\n" +
                " - Yahtzee: Five of the same number. Score = 50 points.\n" +
                " - Chance: Any combination. Score = Sum of all dice.\n\n" +
                "Scoring:\n" +
                " - Each category can only be scored once.\n" +
                " - If the dice do not match the category, the score is 0.\n" +
                " - End of the game: After 13 rounds, all categories are filled. Total score = Sum of all points + bonuses.";
 
 JOptionPane.showMessageDialog(this, rules, "Spielregeln", JOptionPane.INFORMATION_MESSAGE);
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

	private void debug_einschreiben(){
		try {
			int[] debug_zahlen = {Integer.parseInt(W1.getText()), Integer.parseInt(W2.getText()), Integer.parseInt(W3.getText()), Integer.parseInt(W4.getText()), Integer.parseInt(W5.getText())};
			Wurf debug_wurf = new Wurf(debug_zahlen);
			spiel.setCurWurf(debug_wurf);
			CurSpielerMarkieren();
			if(spiel.setCurSpieler(Spieler.getText()) == -1) {
				JOptionPane.showMessageDialog(null, "The Player " + Spieler.getText() + " does not exist!");
				return;
			}
			CurSpielerMarkieren();
			if(spiel.handSpielen(Hand.getText())) {
				EingeschriebeneWerteAnzeigen();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect entry. Check if the throw or what you want to play is written correct.");
				return;
			}
			SpielFertig();
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "In Dice 1-5 has to be a number.");
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
		for(int i = 0; i < 19; i++) {
			if(spiel.getGespielterWert((String)ReihenBeschriftung[i][0]) == -1) {
				Data.setValueAt(null, i, spieler+2);
			} else {
				int wert = spiel.getWert((String)ReihenBeschriftung[i][0]);
				Data.setValueAt(wert == 0 ? "---" : wert, i, spieler+2);
			}
			ColumnModel.getColumn(spieler+2).setCellRenderer(ColumnModel.getColumn(0).getCellRenderer());
		}
	}
	
	private void VorgeschlageneWerteAnzeigen() {
		int spieler = spiel.getCurSpieler();
		boolean[] playeble = new boolean[19];
		for(int i = 0; i < 19; i++) {
			int wert = spiel.getWert((String)ReihenBeschriftung[i][0]);
			Data.setValueAt(wert == 0 ? "---" : wert, i, spieler+2);
			playeble[i] = spiel.isPlayeble((String)ReihenBeschriftung[i][0]);
		}
		
		ColumnModel.getColumn(spieler+2).setCellRenderer(new DefaultTableCellRenderer(){
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        // Formatting here
		        setForeground(playeble[row] ? FarbeVorgeschlageneWerte  : Color.black);
		        return c;
		    }
		});
		Tabelle.repaint();
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
		for(int i = 0; i < WuerfelNeu.length; i++) {
			WuerfelNeu[i].setImage(WurfelGUI.noDice);
		}
	}
	
	private void SpielFertig() {
		int Gewinner = spiel.getWinner();
		if(Gewinner != -1) {
			remove(Panel3);
			remove(Panel4);
			add(Panel5);
			GewinnerLabel.setText("The Winner is " + spiel.getName(Gewinner) + "!");
		}
	}
}
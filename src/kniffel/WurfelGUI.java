package kniffel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WurfelGUI{
	final static String[] filenames = new String[] {"one","two","three","four","five","six"};
	
	final public static Image noDice = readImage(new File("img/dice-six-faces-no-side.png"));
	
	public static Image getImage(int num){
		return readImage(new File("img/dice-six-faces-" + filenames[num-1] + ".png"));
	}
	
	public static Image getImageGrey(int num){
		return readImage(new File("img/dice-six-faces-" + filenames[num-1] + "-unselected.png"));
	}

	public static Image readImage(File file) {
		try {
			return ImageIO.read(file).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ImageIcon getIcon(int num) {
		return new ImageIcon(getImage(num));
	}
}

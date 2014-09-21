package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Background {
	
	public static JLabel getLabel(){
		return new JLabel(new ImageIcon("images" + File.separator + "logo.jpeg"));
	}
}

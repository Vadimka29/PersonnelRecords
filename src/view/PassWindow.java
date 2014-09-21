package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.javafx.tk.Toolkit;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import controller.ConsoleController;

public class PassWindow extends JFrame {
	private static PassWindow window;
	private Container cnt;
	private JPanel menuPanel;
	private JLabel label;
	private JTextField field;
	private static final String PASSWORD = "pass";
	
	private PassWindow(String option){
		super();
		//setResizable(false);
		this.setTitle("Personnel Records");
		cnt = this.getContentPane();
		JLabel background = Background.getLabel();
		cnt.add(background, BorderLayout.CENTER);
		background.setLayout(new GridLayout(3,3));
//		cnt.setBackground(defaultColor);
		setBounds(500, 250, 400, 200);
		Font f = new Font("Helvetica", Font.ITALIC, 14);
		label = new JLabel(" Enter password: ");
		label.setFont(f);
		field = new JTextField();
		//add action listener to pass textField
		field.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				String pass = tf.getText();
				if(!pass.equals(PASSWORD)) {
					//if pass is empty
					if(!pass.isEmpty())
						tf.setBackground(Color.RED);
					else {
						tf.setBackground(new Color(255,255,255));
					}
				}
				else {
					if(pass.equals(PASSWORD))
					switch (option) {
					case "console":
						dispose();
						ConsoleController cc = ConsoleController.getInstance();
						break;
					case "gui":
						dispose();
						Window w = Window.getInstance();
						break;
					}
				}
					
			}
		});
		menuPanel = new JPanel(new GridLayout(3,1));
		menuPanel.setBackground(new Color(0,0,0,0));
		menuPanel.add(label);
		menuPanel.add(field);
		for(int i = 0; i < 4; i++)
			background.add(new JLabel());
		background.add(menuPanel);
		for(int i = 0; i < 4; i++)
			background.add(new JLabel());
		setVisible(true);
	}
	
	public static PassWindow getInstance(String option){
		if(window == null)
			window = new PassWindow(option);
		return window;
	}
}

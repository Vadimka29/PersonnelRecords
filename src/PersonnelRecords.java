import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.PassWindow;

public class PersonnelRecords {
	public static void main(String[] args){
		String option = null;
		if(args.length > 0){
			option = args[0].toLowerCase();
		switch (option) {
		case "console":
			PassWindow cw = PassWindow.getInstance(option);
			break;
		case "gui":
			PassWindow gw  = PassWindow.getInstance(option);
			break;
		default:
			String message = "There is no such program mode!";
			JOptionPane.showMessageDialog(new JFrame(), message,
					"ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
		}
		PassWindow defaultGuiWindow = PassWindow.getInstance("gui");
	}
}

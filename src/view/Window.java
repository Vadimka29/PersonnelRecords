package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javafx.scene.layout.Border;
import javafx.scene.text.TextAlignment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.ConsoleController;
import list.MyArrayList;
import model.Employee;
import model.ModelContainer;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private ModelContainer mc;
	private static Window w;
	private Container cnt;
	private JTabbedPane pane;
	private JPanel add;
	private JPanel remove;
	private JPanel find;
	private Color defaultColor;
	private JTextField firstNameField;
	private JLabel firstNameLabel;
	private JTextField lastNameField;
	private JLabel lastNameLabel;
	private JTextField departmentField;
	private JLabel departmentLabel;
	private JTextField addressField;
	private JLabel addressLabel;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JTextField birthdayField;
	private JLabel birthdayLabel;
	private JButton submit;
	
	private Window(){
		super();
		//get model of data
		mc = ConsoleController.readModelFromBinFile();
		setTitle("Personnel Records");
		cnt = this.getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		defaultColor = new Color(0,0,50,100);
		//new jtablepane with tabs
		pane = new JTabbedPane();
		add = new JPanel();
		add.setLayout(new GridLayout(2,1));
		JTable table = createTable();
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(table.getTableHeader());
		box.add(table);
		JScrollPane scroll = new JScrollPane(box);
		//add.add(box);
		add.add(scroll);
		Font f = new Font("Helvetica", Font.ITALIC, 16);
		table.setEnabled(false);
		JPanel inputPanel = new JPanel(new GridLayout(4,3));
		inputPanel.setBackground(defaultColor);
		inputPanel.setForeground(new Color(255,255,255));
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(f);
		firstNameField = new JTextField();
		Dimension d = new Dimension(700,30);
		firstNameField.setMaximumSize(d);
		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(f);
		lastNameField = new JTextField();
		lastNameField.setMaximumSize(d);
		departmentLabel = new JLabel("Department");
		departmentLabel.setFont(f);
		departmentField = new JTextField();
		departmentField.setMaximumSize(d);
		birthdayLabel = new JLabel("Birthday");
		birthdayLabel.setFont(f);
		birthdayField = new JTextField();
		birthdayField.setMaximumSize(d);
		addressLabel = new JLabel("Address");
		addressLabel.setFont(f);
		addressField = new JTextField();
		addressField.setMaximumSize(d);
		phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(f);
		phoneField = new JTextField();
		phoneField.setMaximumSize(d);
		
		inputPanel.setBackground(defaultColor);
		//firstName panel
		JPanel firstName = new JPanel();
		firstName.setBackground(defaultColor);
		firstName.setLayout(new BoxLayout(firstName, BoxLayout.Y_AXIS));
		firstNameLabel.setForeground(new Color(255,255,255));
		firstName.add(firstNameLabel);
		firstName.add(firstNameField);
		inputPanel.add(firstName);
		
		//LastNamePanel
		JPanel lastName = new JPanel();
		lastName.setBackground(defaultColor);
		lastNameLabel.setForeground(new Color(255,255,255));
		lastName.setLayout(new BoxLayout(lastName, BoxLayout.Y_AXIS));
		lastName.setBackground(defaultColor);
		lastName.add(lastNameLabel);
		lastName.add(lastNameField);
		inputPanel.add(lastName);
		
		//Department panel
		JPanel department = new JPanel();
		String[] dItems = {
				"Services","Marketing","Human", 
				"Financial","Purchasing", "Sales", 
				"IT","Inventory", "QualityAsurance", 
				"Insurance", "Licenses", "Operational", 
				"Customers", "Staff", "Customer", "Organizational"
		};
		JComboBox departmentBox = new JComboBox(dItems);
		departmentBox.setMaximumSize(new Dimension(380,30));
		departmentLabel.setForeground(new Color(255,255,255));
		department.setLayout(new BoxLayout(department, BoxLayout.Y_AXIS));
		department.setBackground(defaultColor);
		department.add(departmentLabel);
		department.add(departmentBox);
		inputPanel.add(department);
		
		//birthay panel
		JPanel birthday = new JPanel();
		birthday.setForeground(new Color(255,255,255));
		birthdayLabel.setForeground(new Color(255,255,255));
		birthday.setLayout(new BoxLayout(birthday, BoxLayout.Y_AXIS));
		birthday.setBackground(defaultColor);
		birthday.add(birthdayLabel);
		birthday.add(birthdayField);
		inputPanel.add(birthday);
		
		//address panel
		JPanel address = new JPanel();
		address.setForeground(new Color(255,255,255));
		addressLabel.setForeground(new Color(255,255,255));
		address.setLayout(new BoxLayout(address, BoxLayout.Y_AXIS));
		address.setBackground(defaultColor);
		address.add(addressLabel);
		address.add(addressField);
		inputPanel.add(address);
		
		//phone panel
		JPanel phone = new JPanel();
		phone.setForeground(new Color(255,255,255));
		phoneLabel.setForeground(new Color(255,255,255));
		phone.setLayout(new BoxLayout(phone, BoxLayout.Y_AXIS));
		phone.setBackground(defaultColor);
		phone.add(phoneLabel);
		phone.add(phoneField);
		inputPanel.add(phone);
		submit = new JButton("Submit");
		submit.setMaximumSize(new Dimension(10,10));
		String items[] = {
				"Employee",
				"Manager",
				"DivisionManager"
		};
		JComboBox combobox = new JComboBox(items);
		combobox.setBackground(new Color(250,250,250,150));
		JLabel tempLable = new JLabel();
		inputPanel.add(firstName);
		inputPanel.add(tempLable);
		inputPanel.add(lastName);
		inputPanel.add(birthday);
		inputPanel.add(tempLable);
		inputPanel.add(department);
		inputPanel.add(address);
		inputPanel.add(tempLable);
		inputPanel.add(phone);
		inputPanel.add(combobox);
		inputPanel.add(tempLable);
		submit.setFont(f);
		submit.setBackground(new Color(250,250,250,150));
		inputPanel.add(submit);
		
		
		
		
		
		add.add(inputPanel);
		//list of employees
		remove = new JPanel();
		remove.setBackground(defaultColor);
		find = new JPanel();
		find.setBackground(defaultColor);
		pane.addTab("add", add);
		pane.addTab("remove", remove);
		pane.addTab("find", find);
		cnt.add(pane);
		setVisible(true);
	}
	public static Window getInstance(){
		if(w == null)
			w = new Window();
		return w;
	}
	private JTable createTable(){
		MyArrayList<Employee> list = mc.getAllEmployees();
		String[] columns = {
			"ID",
			"First Name",
			"Last Name",
			"Department",
			"Birthday date",
			"Adress",
			"Phone"
		};
		Object[][] data = new Object[list.size()][columns.length];
		for(int i = 0; i < list.size(); i++){
			Employee e = list.get(i);
			if(e != null){
				data[i][0] =  e.getId();
				data[i][1] = e.getFirstName();
				data[i][2] = e.getLastName();
				data[i][3] = e.getDepartment();
				GregorianCalendar bday = e.getBirthdayDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				String birthday = (bday != null)? sdf.format(bday.getTime()) : null;
				data[i][4] = birthday;
				data[i][5] = e.getAddress();
				data[i][6] = e.getPhone();
			}
		}
		return new JTable(data,columns);
	}
}

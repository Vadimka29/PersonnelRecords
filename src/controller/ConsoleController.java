package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.Scanner;

import list.MyArrayList;
import model.*;

public class ConsoleController {
	private static ConsoleController controller;
	private Scanner sc;
	private static ModelContainer container;

	//Singleton
	public static ConsoleController getInstance(){
		if(controller == null)
			controller = new ConsoleController();
		return controller;
	}
	private ConsoleController() {
		//read  container object from save.bin
		container = readModelFromBinFile();
		System.out.println("\t<----------Hello! This is console version of program!---------->");
		sc = new Scanner(System.in);
		for (;;) {
			System.out.println("Please enter command: ");
			System.out.print("> ");
			String cmd = null;
			String[] data = null;
			try {
				//get all commands
				data = sc.nextLine().split(" ");
				cmd = data[0].toLowerCase();
			} catch (Exception e) {
				System.out.println(e + " Try to input command once more!");
				continue;
			}
			//Choose command method
			switch (cmd) {
			case "add":
				addToContainer(data);
				break;
			case "show":
				show(data);
				break;
			case "set":
				setData(data);
				break;
			case "pay":
				paySalary(data);
				break;
			case "salaries":
				showSalaries(data);
				break;
			case "find":
				findRecord(data);
				break;
			case "remove":
				removeData(data);
				break;
			case "help":
				showHelp();
				break;
			case "exit":
				saveInfo();
				System.exit(0);
			case "report":
				makeReport(data);
				break;
			default:
				System.out.println("Invalid command! Try to input command once more!");
				break;
			}
		}
	}
	/**
	 * Add new employees.
	 * @param data - different options.
	 */
	private void addToContainer(String[] data){
		Employee em = null;
		try{
		String type = data[1].toLowerCase();
		String firstName = data[2];
		String lastName = data[3];
		String department = data[4];
		em = EmployeeFactory.createEmployee(type, firstName, lastName, department);
		} catch(Exception e){
			System.out.println("Invalid command! Try to input command once more!");
			return;
		}
		container.add(em);
	}
	/**
	 * Show list of employees in diff. modes.
	 * @param data - different options.
	 */
	private void show(String[] data){
		String option = null;
		try {
		option = data[1].toLowerCase();
		} catch(Exception e){
			System.out.println("Invalid command! Try to input command once more!");
			return;
		}
		switch(option){
		case "all":
			if(container.isEmpty())
				System.out.println("List of employees is empty!");
			System.out.println(container);
			break;
			//show certain type of employee
		default:
			MyArrayList<Employee> listOfCertainType = null;
			try {
				listOfCertainType = container.getType(option);
				System.out.println(listOfCertainType);
			} catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println("Invalid command! Try to input command once more!");
			}
		}
	}
	/**
	 * Set data for employees.
	 * @param data.
	 */
	private void setData(String[] data){
		String option = null;
		String inputValue = null;
		long id;
		try{
			option = data[1].toLowerCase();
			inputValue = data[2];
			id = Long.parseLong(data[3]);
		} catch(Exception e){
			System.out.println("Invalid command! Try to input command once more!");
			return;
		}
		switch(option){
		case "birthday":
			setBirthdayData(inputValue, id);
			break;
		case "address":
			setAddress(inputValue, id);
			break;
		case "phone":
			setPhone(inputValue, id);
			break;
		case "department":
			setDepartment(inputValue, id);
			break;
		case "bonus":
			setBonus(inputValue, id);
			break;
		case "coefficient":
			setCoefficient(inputValue, id);
			break;
		default:
			System.out.println("Invalid command! Try to input command once more!");
			break;
		}
	}
	private void paySalary(String[] data){
		int salarySize;
		long id;
		GregorianCalendar date = null;
		try { 
			salarySize = Integer.parseInt(data[1]);
			id = Long.parseLong(data[2]);
			if(data.length == 4)
				date = parseDate(data[3]);
			Employee em = container.find(id);
			if(date == null){
				em.paySalary(salarySize);
			} else {
				em.paySalary(salarySize, date);
			}
		} catch(NumberFormatException e0){
			System.out.println("Illegal argument!");
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		} catch(ClassCastException e){
			if(date == null)
				System.out.println("Invalid input! Check size of salary");
			else
				System.out.println("Invalid input! Check date");
			return;
		} catch(Exception e){
			System.out.println("Invalid command! Try to input command once more!");
			return;
		}
	}

	private void showSalaries(String[] data) {
		long id;
		try {
			id = Long.parseLong(data[1]);
			Employee em = container.find(id);
			if (em.getSalaries().isEmpty())
				System.out.println("Employee doesn't have salaries!");
			else {
				System.out.println(em.getId() + "." + em.getFirstName() + " "
						+ em.getLastName() + "'s salaries: ");
				for (Salary salary : em.getSalaries())
					System.out.print(salary);
			}
		} catch (IllegalArgumentException e1) {
			// System.out.println(e1.getMessage());
			e1.printStackTrace();
			return;
		} catch (Exception e) {
			System.out
					.println("Invalid command! Try to input command once more!");
			return;
		}
	}

	/**
	 * Find employees by id, birthday date, first letter of lastName.
	 * 
	 * @param data
	 */
	private void findRecord(String[] data) {
		String option = null;
		String prefix = null;
		GregorianCalendar fromGc = null;
		GregorianCalendar toGc = null;
		long id = 0;
		try {
			option = data[1].toLowerCase();
			MyArrayList<Employee> findResult = new MyArrayList<>();
			switch (option) {
			case "id":
				findResult.clear();
				id = Long.parseLong(data[2]);
				Employee em = container.find(id);
				System.out.println(em);
				break;
			case "lastname":
				findResult.clear();
				prefix = data[2];
				container.find(findResult, prefix);
				if(findResult.isEmpty())
					System.out.println("No Finding Result!");
				else
					System.out.println(findResult);
				break;
			case "birthday":
				findResult.clear();
				fromGc = parseDate(data[2]);
				toGc = parseDate(data[3]);
				container.find(findResult, fromGc, toGc);
				if(findResult.isEmpty())
					System.out.println("No Finding Result!");
				else
					System.out.println(findResult);
				break;
			default:
				System.out.println("Invalid command! Try to input command once more!");
				break;
			}
		} catch(NumberFormatException e0){
			System.out.println("Illegal argument!");
		} catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println("Invalid command! Try to input command once more!");
		}
	}
	private void makeReport(String[] data){
		String format = null;
		Department department = null;
		try {
			format = data[1].toLowerCase();
			department = Department.valueOf(data[2]);
			MyArrayList<Employee> findResult = new MyArrayList<>();
			switch(format){
			case "html":
				findResult.clear();
				container.getAllEmployeeFromDepartment(findResult, department);
				ReportController.makeHtmlReport(findResult);
				break;
			}
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		} catch(ClassCastException e1){
			System.out.println("Illegal format");
		} catch(Exception e2){
			System.out.println("Invalid command! Try to input command once more!");
		}
	}
	/**
	 * Parse date from String such 29.07.2014
	 * @param date
	 * @return
	 * @throws IllegalArgumentException
	 */
	private GregorianCalendar parseDate(String date) throws IllegalArgumentException{
		try {
			//split input data
			String[] partedDate = date.split("\\.");
			if(partedDate[0].charAt(0) == '0')
				partedDate[0] = partedDate[0].substring(1);
			int  day = Integer.parseInt(partedDate[0]);
			//if month started from 0.
			if(partedDate[1].charAt(0) == '0')
				partedDate[1] = partedDate[1].substring(1);
			int  month = Integer.parseInt(partedDate[1]) - 1;
			int  year = Integer.parseInt(partedDate[2]);
			GregorianCalendar gc = new GregorianCalendar(year, month, day);
			return gc;
		} catch(Exception e){
			throw new IllegalArgumentException("Invalid argument for parsing date");
		}
	}
	/**
	 * Set birthday date for employee with identifier id.
	 * @param date - date of birthday.
	 * @param id - identifier of employee.
	 */
	private void setBirthdayData(String date, long id){
		try{
			GregorianCalendar birthdayDate = parseDate(date);
			Employee e = container.find(id);
			e.setBirthdayDate(birthdayDate);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Set address for employee with identifier id.
	 * @param address - address of employee.
	 * @param id - identifier of employee.
	 */
	private void setAddress(String address, long id){
		try {
			Employee e = container.find(id);
			e.setAddress(address);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	private void setPhone(String phone, long id){
		try {
			Employee e = container.find(id);
			//validation of phone
			for(int i = 0; i < phone.length(); i++){
				if(!Character.isDigit(phone.charAt(i)) && 
						phone.charAt(i) != '-')
					throw new IllegalArgumentException("Invalid phone");
			}
			e.setPhone(phone);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

	private void setBonus(String value, long id) {
		try {
			int bonus = Integer.parseInt(value);
			Manager m = (Manager) container.find(id);
			m.setBonusForEachSalary(bonus);
		} catch(NumberFormatException e){
			System.out.println("Bonus value is incorrect!");
		}
		catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		} catch (ClassCastException e2) {
			System.out.println("Employee with id " + id + " doesn't have bonus!");
		} catch (Exception e3) {
			System.out.println("Invalid command! Try to input command once more!");
		}
	}
	private void setCoefficient(String value, long id){
		try {
			int coefficient = Integer.parseInt(value);
			DivisionManager dm = (DivisionManager) container.find(id);
			dm.setCoefficient(coefficient);
		} catch(NumberFormatException e){
			System.out.println("Coefficient value is incorrect!");
		} catch(IllegalArgumentException e1){
			System.out.println(e1.getMessage());
		} catch(ClassCastException e2){
			System.out.println("Employee with id " + id + " doesn't have coefficient!");
		} catch(Exception e3){
			System.out.println("Invalid command! Try to input command once more!");
		}
	}
	private void setDepartment(String department, long id){
		try {
			Employee e = container.find(id);
			e.setDepartment(department);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Serialize container to save.bin file.
	 * @return serialize status.
	 */
	private boolean saveInfo() {
		File file = new File("save.bin");
		try (FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			// write count of employees for making correct id.
			oos.writeLong(Employee.getEmployeeCounter());
			oos.writeObject(container);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void removeData(String[] data){
		long id;
		try {
			id = Long.parseLong(data[1]);
			container.remove(id);
		} catch(Exception e){
			System.out.println("Invalid command! Try to input command once more!");
		}
	}
	public void showHelp(){
		System.out.println("********** HELP **********");
		System.out.println("Syntax of commands: ");
		StringBuffer sb = new StringBuffer();
		sb.append("1) ADD NEW EMPLOYEE: " + "add <employee_type> <first_name> <last_name> <department>\n");
		sb.append("2) SHOW ALL EMPLOYEE: " + "show all\n");
		sb.append("3) SHOW CERTAIN TYPE OF EMPLOYEE: " + "show <employee_type>\n");
		sb.append("4) SET BIRTHDAY DATA: " + "set birthday <dd.mm.yyyy> <id>\n");
		sb.append("5) SET ADDRESS: " + "set address <country_city_street> <id>\n");
		sb.append("6) SET PHONE: " + "set phone <XXX-XX-XX> <id>\n");
		sb.append("7) SET DEPARTMENT: " + "set department <department_name> <id>\n");
		sb.append("8) SET BONUS: " + "set bonus <value> id\n");
		sb.append("9) SET COEFFICIENT: " + "set coefficient <value> <id>\n");
		sb.append("10) PAY SALARY TO EMPLOYEE WITH TODAY DATE: " + "pay <salary_size> <id>");
		sb.append("11) PAY SALARY TO EMPLOYEE WITH OTHER DATE: " + "pay <salary_size> <id> <dd.mm.yyyy>");
		sb.append("12) SHOW SALARIES OF EMPLOYEE: " + "salaries <id>\n"); 
		sb.append("13) FIND EMPLOYEE BY ID: " + "find id <id>\n");
		sb.append("14) FIND EMPLOYEE BY PREFIX OF LAST NAME: " + "find LastName <prefix>\n");
		sb.append("15) FIND EMPLOYEE BY BIRTHDAY DATE: " + "find birthday <dd.mm.yyyy(from)> <dd.mm.yyyy(to)>\n");
		sb.append("16) REMOVE EMPLOYEE: " + "remove <id>\n");
		sb.append("17) SHOW HELP: " + "help\n");
		sb.append("18) EXIT: "  + "exit\n");
		System.out.println(sb.toString());
	}
	public static ModelContainer readModelFromBinFile(){
		ModelContainer mc = null;
		File file = new File("save.bin");
		if(file.exists()){
			try(FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);){
				long employeeCounter = ois.readLong();
				Employee.setEmployeeCounter(employeeCounter);
				mc = (ModelContainer) ois.readObject();
			} catch(Exception e){
				System.out.println("Attention! There is problem of reading save.bin. New list of employees is created!");
				mc = new ModelContainer();
			}
		}
		else
			mc = new ModelContainer();
		return mc;
	}
}
import java.util.ArrayList;

import controller.ConsoleController;
import list.MyArrayList;
import model.*;

public class PersonnelRecords {
	public static void main(String[] args){
		ArrayList<Employee> lst = new ArrayList<>();
		MyArrayList<Employee> list = new MyArrayList<>();
		
		Employee person = new DivisionManager("Akymov", "Vadym", "IT");
		person.setBirthdayDate(1995, 6, 29);
		list.add(person);
		lst.add(person);
		//lst.add(person);
		//System.out.println(person);
		
		Manager manager = new Manager("Petrenko","Petro" , "Develop");
		list.add(manager);
		lst.add(manager);
		//System.out.println(manager);
		
		
		DivisionManager dm = new DivisionManager("Sergey", "Sergeevich", "Credits");
		lst.add(dm);
		DivisionManager d = null;
		list.add(dm);
		list.add(d);
		lst.add(d);
		//System.out.println(list);
		ConsoleController cc = new ConsoleController();
	}
}

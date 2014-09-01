import java.util.Calendar;
import java.util.GregorianCalendar;

import employee.*;


public class PersonnelRecords {
	public static void main(String[] args){
		Employee person = new DivisionManager("Akymov", "Vadym", "IT");
		person.setBirthdayDate(1995, 6, 29);
		System.out.println(person);
	}
}

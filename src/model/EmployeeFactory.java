package model;

/**
 * Simple Factory has been created for working with employee data model.s
 * @author vadim
 *
 */
public class EmployeeFactory {
	
	public static Employee createEmployee(String type, String firstName, String lastName, 
			String department ){
			switch (type) {
			case "employee":
				return new Employee(firstName, lastName, department);
			case "manager":
				return new Manager(firstName, lastName, department);
			case "divisionmanager":
				return new DivisionManager(firstName, lastName, department);
			default:
				throw new IllegalArgumentException("Invalid type of personnel!");
			}
	}
}
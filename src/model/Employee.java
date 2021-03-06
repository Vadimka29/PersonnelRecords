package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Employee implements Serializable {
	private static final long serialVersionUID = 7494431400780761504L;
	private static long employeeCounter;
	private long id;
	private String firstName;
	private String lastName;
	private GregorianCalendar birthdayDate;
	//we need to add the salary at the end of list or remove first during O(1)
	private LinkedList<Salary> salaryList;
	private String address;
	private String phone;
	private Department department;
	
	public Employee(String firstName, String lastName, String department){
		id = employeeCounter;
		salaryList = new LinkedList<Salary>();
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			this.department = Department.valueOf(department);
		} catch(IllegalArgumentException e){System.out.println("There is no such department!");}
		employeeCounter ++;
	}

	/**
	 * Pay salary to employee with today date.
	 * @param salarySize
	 */
	public void paySalary(int salarySize){
		Salary s = new Salary(salarySize);
		salaryList.addLast(s);
	}
	/**
	 * Pay salary to employee and specify date.
	 * @param salarySize
	 * @param year
	 * @param month
	 * @param day
	 */
	public void paySalary(int salarySize, int year, int month, int day){
		//numeration of months starts from zero.
		Salary s = new Salary(salarySize, year, month, day);
		salaryList.addLast(s);
	}
	public void paySalary(int salarySize, GregorianCalendar date){
		Salary s = new Salary(salarySize, date);
		salaryList.addLast(s);
	}
	
	@Override
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		toReturn.append("ID: " + id + "\n");
		//Use reflection to determine the class of employee
		toReturn.append("Position: " + this.getClass().getName().substring(6) + "\n");
		toReturn.append("First name: " + firstName + "\n");
		toReturn.append("Last name: " + lastName + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String birthday = (birthdayDate != null)? sdf.format(birthdayDate.getTime()) : null;
		toReturn.append("Birthday date: " + birthday + "\n");
		toReturn.append("Address: " + ((address != null)? address : null) + "\n");
		toReturn.append("Phone: " + ((phone != null)? phone : null) + "\n");
		toReturn.append("Department: " + department + "\n");
		return toReturn.toString();
	}
	//getters and setters
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setBirthdayDate(int year, int month, int day){
		birthdayDate = new GregorianCalendar(year, month, day);
	}
	public void setBirthdayDate(GregorianCalendar birthdayDate){
		this.birthdayDate = birthdayDate;
	}
	public GregorianCalendar getBirthdayDate() {
		return birthdayDate;
	}
	public LinkedList<Salary> getSalaries() {
		return salaryList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	//подумать насчет того выкидывать ли ошибку
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		try {
			this.department = Department.valueOf(department);
		} catch(IllegalArgumentException e){System.out.println("There is no such department!");}
	}
	//count of all employees
	public static long getEmployeeCounter(){
		return employeeCounter;
	}
	public static void setEmployeeCounter(long counter){
		employeeCounter = counter;
	}
}

package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;

import list.MyArrayList;

public class ModelContainer implements Serializable {
	private static final long serialVersionUID = 4347067379740606799L;
	private MyArrayList<Employee> list;
	
	
	public ModelContainer(){
		list = new MyArrayList<Employee>();
	}
	//подумать потом о производительности методов, пока O(n)
	public boolean add(Employee em){
		return list.add(em);
	}
	public int size(){
		return list.size();
	}
	public boolean isEmpty(){
		return list.isEmpty();
	}
	public MyArrayList<Employee> getType(String queryType){
		MyArrayList<Employee> listofCertainEmployee = new MyArrayList<>();
		Iterator<Employee> it = list.iterator();
		while(it.hasNext()){
			Employee e = it.next();
			//myarraylist can store null
			if (e != null) {
				String employeeType = e.getClass().getName().substring(6).toLowerCase();
				if (queryType.equals(employeeType))
					listofCertainEmployee.add(e);
			}
		}
		if(listofCertainEmployee.isEmpty())
			throw new IllegalArgumentException("There is no such type of employee or list of employees is empty!");
		return listofCertainEmployee;
	}
	/**
	 * Find Employee with certain id.
	 * @param id - identifier of employee.
	 * @return
	 */
	public Employee find(long id) throws IllegalArgumentException{
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			if(id == e.getId())
				return e;
		}
		throw new IllegalArgumentException("There are not any employees with such id!");
	}
	public void find(MyArrayList<Employee> findResult,String prefix){
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			String lastName = e.getLastName();
			if(lastName.startsWith(prefix))
				findResult.add(e);
		}
	}		
	public void find(MyArrayList<Employee> findResult, GregorianCalendar from, GregorianCalendar to){
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			GregorianCalendar birthday = e.getBirthdayDate();
			//проверить не ошибся ли в настройке диапазона
			if(birthday.after(from) && birthday.before(to))
				findResult.add(e);
		}
	}
	public void getAllEmployeeFromDepartment(MyArrayList<Employee> findResult, Department department){
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			if(e.getDepartment() == department)
				findResult.add(e);
		}
	}
	public MyArrayList<Employee> getAllEmployees(){
		return list;
	}
	/**
	 * Remove employee from list by id.
	 * @param id - identifier of employee.
	 * @return remove status.
	 */
	public boolean remove(long id) {
		try {
			Employee em = find(id);
			list.remove(em);
			return true;
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	@Override
	public String toString(){
		return list.toString();
	}
}
package model;

import java.util.GregorianCalendar;
import list.MyArrayList;

public class Container {
	private MyArrayList<Employee> list;
	
	public Container(){
		list = new MyArrayList<>();
	}
	//подумать потом о производительности методов, пока O(n)
	public boolean add(Employee em){
		return list.add(em);
	}
	public boolean remove(int id){
		int size = list.size();
		for(int i = 0; i < size; i++){
			if(list.get(i).getId() == id)
				return (list.remove(id) == null)? true: false;
		}
		return false;
	}
	public MyArrayList<Employee> find(String prefix){
		MyArrayList<Employee> findResult = new MyArrayList<>();
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			String lastName = e.getLastName();
			if(lastName.startsWith(prefix))
				findResult.add(e);
		}
		return findResult;
	}
	public MyArrayList<Employee> find(GregorianCalendar from, GregorianCalendar to){
		MyArrayList<Employee> findResult = new MyArrayList<>();
		int size = list.size();
		for(int i = 0; i < size; i++){
			Employee e = list.get(i);
			GregorianCalendar birthday = e.getBirthdayDate();
			//проверить не ошибся ли в настройке диапазона
			if(birthday.after(from) && birthday.before(to))
				findResult.add(e);
		}
		return findResult;
	}	
}

package employee;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

class Salary {
	private int salarySize;
	private GregorianCalendar date;
	
	/**
	 * This constructor detects date automatically.
	 * You need to specify the size of salary.
	 * @param salarySize - size of salary.
	 */
	public Salary(int salarySize){
		this.salarySize = salarySize;
		date = new GregorianCalendar();
	}
	/**
	 * You need to specify the size of salary and date.
	 * @param salarySize
	 * @param year
	 * @param month
	 * @param day
	 */
	public Salary(int salarySize, int year,int month, int day){
		this.salarySize = salarySize;
		date = new GregorianCalendar(year,month,day);
	}
	public Salary(int salarySize, GregorianCalendar date){
		this.salarySize = salarySize;
		this.date = date;
	}
	@Override
	public String toString(){
		StringBuffer toReturn = new StringBuffer();
		toReturn.append("Salary: " + salarySize);
		SimpleDateFormat sdf = 	new SimpleDateFormat("FF.dd.yyyy");
		toReturn.append(" Date: " + sdf.format(date));
		return toReturn.toString();
	}
	//getters and setters
	public int getSalarySize() {
		return salarySize;
	}

	// Надо подумать надо ли мне менять зарплату в какой-то момент
	//+ также надо подумать надо ли мне менять дату получения зарплаты, скорее всего нет
	public void setSalarySize(int salarySize) {
		this.salarySize = salarySize;
	}
	
	public GregorianCalendar getDate() {
		return date;
	}
}

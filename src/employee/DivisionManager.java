package employee;

public class DivisionManager extends Manager {
	private int coefficientForEachSalary;
	
	public DivisionManager(String firstName, String lastName, String department) {
		super(firstName, lastName, department);
		//standart value of coefficient
		coefficientForEachSalary = 1;
	}
	/**
	 * Pay salary to  division manager with today date.
	 * @param salarySize
	 */
	@Override
	public void paySalary(int salarySize){
		super.paySalary(getBonusForEachSalary() + 
				coefficientForEachSalary * salarySize);
	}
	/**
	 * Pay salary to manager and specify date.
	 * @param salarySize
	 * @param year
	 * @param month
	 * @param day
	 */
	@Override
	public void paySalary(int salarySize, int year, int month, int day){
		super.paySalary(getBonusForEachSalary() + 
				coefficientForEachSalary * salarySize, year, month, day);
	}
	
	public String toString(){
		StringBuffer toReturn = new StringBuffer(super.toString());
		toReturn.append("Coefficient for each salary: " + coefficientForEachSalary + "\n");
		return toReturn.toString();
	}
	
	//getters and setters
	public int getCoefficient() {
		return coefficientForEachSalary;
	}
	public void setCoefficient(int coefficient) {
		this.coefficientForEachSalary = coefficient;
	}
}

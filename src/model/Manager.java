package model;

public class Manager extends Employee {
	private static final long serialVersionUID = -1692645806639353707L;
	private int bonusForEachSalary;
	
	public Manager(String firstName, String lastName, String department) {
		super(firstName, lastName, department);	
	}
	/**
	 * Pay salary to manager with today date.
	 * @param salarySize
	 */
	@Override
	public void paySalary(int salarySize){
		super.paySalary(salarySize + bonusForEachSalary);
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
		super.paySalary(salarySize + bonusForEachSalary,
				year, month, day);
	}
	
	@Override
	public String toString(){
		StringBuffer toReturn = new StringBuffer(super.toString());
		toReturn.append("Bonus for each salary: " + bonusForEachSalary + "$\n");
		return toReturn.toString();
	}
	//getters and setters
	public int getBonusForEachSalary() {
		return bonusForEachSalary;
	}
	public void setBonusForEachSalary(int bonusForEachSalary) {
		this.bonusForEachSalary = bonusForEachSalary;
	}
}
//Duru Baþtunalý 150120075
//Payroll class

public class Payroll {
	//Data field
	private int workHour;
	private int itemCount;
	
	//Payroll constructor
	public Payroll(int workHour, int itemCount) {
		this.workHour = workHour;
		this.itemCount = itemCount;		
	}
	
	//Calculate the salary for each employee
	public int calculateSalary() {
		return workHour * 3 + itemCount * 2;
	}
	
	//Print the work hour and item count
	public String toString() {
		return "The workhour is " + workHour + " and the produced item count is " + itemCount + " .";
	}
	
	//Getters and setters
	public int getWorkHour() {
		return workHour;
	}

	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
}

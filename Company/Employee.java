//Duru Baþtunalý 150120075
//Employee class

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Employee extends Person {
	private double salary;
	private Calendar hireDate;
	private Department department;
	public static int numberofEmployees;
	
	//Constructors
	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, String gender, Calendar birthDate, 
			String maritalStatus, String hasDriverLicence, double salary, 
			Calendar hireDate, Department department) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
		this.salary = salary;
		this.hireDate = hireDate;
		this.department = department;
		numberofEmployees++;
	}
	
	public Employee(Person person, double salary, Calendar hireDate, Department department) {
		super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), 
				person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence());
		this.salary = salary;
		this.hireDate = hireDate;
		this.department = department;
		numberofEmployees++;
	}
	
	//Getters, setters, toString, methods
	public double raiseSalary(double percent) {
		this.setSalary(this.getSalary() * (1.0 + percent));
		return this.getSalary();
	}
	
	public double raiseSalary(int amount) {
		this.setSalary(this.getSalary() + amount);
		return this.getSalary();
	}

	//Getters and setters
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public java.util.Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.util.Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getNumberofEmployees() {
		return numberofEmployees;
	}

	public void setNumberofEmployees(int numberofEmployees) {
		Employee.numberofEmployees = numberofEmployees;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		Date date = hireDate.getTime();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
		String strDateHire = format1.format(date);
		
		return "Employee Info [salary=" + salary + ", hireDate=" + strDateHire;
	}
	
	@SuppressWarnings("deprecation")
	public String toString(int a, int b) {
		Date date = hireDate.getTime();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
		String strDateHire = format1.format(date);
		return "Employee Info [salary=" + salary + ", hireDate=" + strDateHire + "]";
	}
}

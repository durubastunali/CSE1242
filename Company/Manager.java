//Duru Baþtunalý 150120075
//Manager class

import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Employee {
	private ArrayList<RegularEmployee> regularEmployees = new ArrayList<RegularEmployee>();
	private double bonusBudget;
	
	//Constructors
	public Manager() {
	}

	public Manager(int id, String firstName, String lastName, String gender, Calendar birthDate, 
			String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, 
			Department department,double bonusBudget) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department);
		this.bonusBudget = bonusBudget;
		
	}
	
	public Manager(Employee employee, double bonusBudget) {
		super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(), 
				employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(), 
				employee.getSalary(), employee.getHireDate(), employee.getDepartment());
		this.bonusBudget = bonusBudget;
	}
	
	//Getters, setters, toString, methods
	public void addEmployee(RegularEmployee e) {
		regularEmployees.add(e);
	}
	
	public void removeEmployee(RegularEmployee e) {
		regularEmployees.remove(e);
	}
	
	public void distributeBonusBudget() {
		double unit = 0;
		double totalAmount = 0;
		for (int i = 0; i < regularEmployees.size(); i++) {
			totalAmount += regularEmployees.get(i).getSalary() * regularEmployees.get(i).getPerformanceScore();
		}
		unit = this.bonusBudget / totalAmount;
		for (int i = 0; i < regularEmployees.size(); i++) {
			double bonusAmount = 0;
			bonusAmount = regularEmployees.get(i).getSalary() * regularEmployees.get(i).getPerformanceScore() * unit;
			regularEmployees.get(i).setBonus(((int)(Math.round(bonusAmount * 200)))/100.0);
		}
	}

	@Override
	public String toString() {
		return "Manager [id: " + getId() + ", " + getFirstName() + " " + getLastName();
	}

	public ArrayList<RegularEmployee> getRegularEmployees() {
		return regularEmployees;
	}

	public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
		this.regularEmployees = regularEmployees;
	}

	public double getBonusBudget() {
		return bonusBudget;
	}

	public void setBonusBudget(double bonusBudget) {
		this.bonusBudget = bonusBudget;
	}

}

//Duru Baþtunalý 150120075
//Factory class

public class Factory {
	private String name;
	private Storage storage;
	private double itemPrice;
	private Employee[] employees = new Employee[0];
	private Payroll[] payrolls = new Payroll[0];
	
	//Factory constructor
	public Factory(String name, int capacity, double itemPrice) {
		this.name = name;
		this.itemPrice = itemPrice;
		this.storage = new Storage(capacity);
	}
	
	//Calculate the revenue
	public double getRevenue() {
		return itemPrice * Item.numberOfItems;
	}
	
	//Calculate the total salary of all employees
	public double getPaidSalaries() {
		double totalPaidSalaries = 0;
		for (int i = 0; i < payrolls.length; i++) {
			totalPaidSalaries += payrolls[i].calculateSalary();
		}
		return totalPaidSalaries;
	}
	
	//Add employee
	public void addEmployee (Employee employee) {
		Employee[] targetEmployees = new Employee[employees.length + 1];
			for (int i = 0; i < employees.length; i++) 
				targetEmployees[i] = employees[i];
			targetEmployees[employees.length] = employee;
			employees = targetEmployees; 
			//Load the items the added employee creates into the storage
			Item[] newStorage = employee.startShift();
			for (int i = 0; i < newStorage.length; i++)
				storage.addItem(newStorage[i]);
			}
	
	//Remove employee
	public Employee removeEmployee(int id) {
		int indexEmployee = -1;
		Employee removeEmployee = null;
		for (int i = 0; i < employees.length; i++) {
			if (employees[i].getId() == id) {
				indexEmployee = i;
				if (i == -1) 
					System.out.println("Employee does not exist");
			}
		}
		
		if (employees.length == 0) 
			System.out.println("There are no employees!");
		else {
			removeEmployee = employees[indexEmployee];
			Payroll payroll = removeEmployee.endShift();
			addPayroll(payroll);
			Employee[] targetEmployees = new Employee[employees.length - 1];
			//Copy the array without the removed employee into another resized array
			for (int a = 0, b = 0; a < employees.length; a++) {
				if (employees[indexEmployee].getId() != employees[a].getId()) {
					targetEmployees[b] = employees[a];
					b++;
				}
			}
			//Copy the resized array to the original array
			employees = targetEmployees;
		}
		return removeEmployee;
	}

	//Add payroll
	private void addPayroll(Payroll payroll) {
		Payroll[] targetPayroll = new Payroll[payrolls.length + 1];
		//Copy the original array into a resized array
		for (int i = 0; i < payrolls.length; i++) 
			targetPayroll[i] = payrolls[i];
		targetPayroll[payrolls.length] = payroll;
		//Copy the resized array to the original array
		payrolls = targetPayroll;
	}

	//Getters and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Payroll[] getPayrolls() {
		return payrolls;
	}

	public void setPayrolls(Payroll[] payrolls) {
		this.payrolls = payrolls;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
}

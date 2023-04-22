//Duru Baþtunalý 150120075
//Employee class

public class Employee {
	//Data field
	private int id;
	private String name;
	private String surname;
	private int workHour;
	private int speed;
	private Item[] items;
	private Payroll payroll;

	//Employee constructor
	public Employee(int id, String name, String surname, int workHour, int speed) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.workHour = workHour;
		this.speed = speed;	
	}
	
	//Assign an randomly generated id between 1 and 100 for each item
	public Item[] startShift() {
		items = new Item[0];
		Item[] items = new Item[speed * workHour];
		for (int i = 0; i < speed * workHour; i++) 
			items[i] = new Item((int)(Math.random() * 100 + 1));
		return items;
	}
	
	//Create a Payroll object and assign to payroll, then return
	public Payroll endShift() {
		Payroll payroll = new Payroll(workHour, workHour * speed);
		return payroll;
	}
	
	//Print the id, speed, work hour, and item count
	public String toString() {
		return "This is the employee with id " + id + " speed " + speed + ". The work hour is " + workHour + " and the produced item count is " + workHour * speed + ".";
	}
	
	//Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getWorkHour() {
		return workHour;
	}

	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}
	
}

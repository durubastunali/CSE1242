//Test class

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		Scanner input = new Scanner(file);
		
		//Keep objects in ArrayLists polymorphically
		ArrayList<Object> objects1 = new ArrayList<Object>(); 
				
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Department> departments = new ArrayList<Department>();
		ArrayList<Project> mainProjects = new ArrayList<Project>();
		ArrayList<Product> mainProducts = new ArrayList<Product>();
		ArrayList<Manager> managers = new ArrayList<Manager>();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ArrayList<RegularEmployee> mainRegularEmployees = new ArrayList<RegularEmployee>();
		ArrayList<Developer> developers = new ArrayList<Developer>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<SalesEmployee> salesEmployees = new ArrayList<SalesEmployee>();
				
		//Read the inputs from the text file, create constructors
		while (input.hasNext()) {
			String firstWord = input.next();
			//Department
			if (firstWord.equals("Department")) {
				//Take the inputs from the text file
				int departmentID = input.nextInt();
				String departmentName = input.next();
				Department department = new Department(departmentID, departmentName);
				//Add objects to related ArrayLists
				departments.add(department);
				objects1.add(department);
			}
						
			//Project
			else if (firstWord.equals("Project")) {
				//Take the inputs from the text file
				String projectName = input.next();
				//Convert the given String to date format
				String stringDate = input.next();
				Calendar calendar = parseCalendar(stringDate);
				
				String projectState = input.next();
				Project project = new Project(projectName, calendar, projectState);
				//Add objects to related ArrayLists
				mainProjects.add(project);	
				objects1.add(project);
			}
			
			//Product
			else if (firstWord.equals("Product")) {
				//Take the inputs from the text file
				String productName = input.next();
				//Convert the given String to date format
				String stringDate = input.next();
				Calendar calendar = parseCalendar(stringDate);
				
				double productPrice = input.nextDouble();
				Product product = new Product(productName, calendar, productPrice);
				//Add objects to related ArrayLists
				mainProducts.add(product);
				objects1.add(product);
			}
			
			//Person
			else if (firstWord.equals("Person")) {
				//Take the inputs from the text file
				String firstName = input.next();
				String lastName = input.next();
				int id = input.nextInt();
				String gender = input.next();
				//Convert the given String to date format
				String stringDate = input.next();
				Calendar calendar = parseCalendar(stringDate);
				
				String maritalStatus = input.next();
				String hasLicence = input.next();
				Person person = new Person(id, firstName, lastName, gender, calendar, maritalStatus, hasLicence);
				//Add objects to related ArrayLists
				people.add(person);
				objects1.add(person);
			}
			
			//Employee
			else if (firstWord.equals("Employee")) {
				//Take the inputs from the text file
				int id = input.nextInt();
				double salary = input.nextDouble();
				//Convert the given String to date format
				String stringDate = input.next();
				Calendar calendar = parseCalendar(stringDate);
				
				String departmentName = input.next();
				Department empDepartment = null;
				for (int i = 0; i < departments.size(); i++) {
					if (departmentName.equals(departments.get(i).getDepartmentName())) 
						empDepartment = departments.get(i);
				}
				Person empPerson = null;
				for (int i = 0; i < people.size(); i++) {
					if (id == people.get(i).getId()) 
						empPerson = people.get(i);
				}
				Employee employee = new Employee(empPerson, salary, calendar, empDepartment);
				//Add objects to related ArrayLists
				employees.add(employee);
				objects1.add(employee);
			}
			
			//RegularEmployee
			else if (firstWord.equals("RegularEmployee")) {
				int id = input.nextInt();
				double performanceScore = input.nextDouble();
				Employee regEmployee = null;
				for (int i = 0; i < employees.size(); i++) {
					if (id == employees.get(i).getId()) 
						regEmployee = employees.get(i);					
				}			
				RegularEmployee regularEmployee = new RegularEmployee(regEmployee, performanceScore);
				//Add objects to related ArrayLists
				mainRegularEmployees.add(regularEmployee);
				objects1.add(regularEmployee);
				}
			
			//Developer
			else if (firstWord.equals("Developer")) {
				int id = input.nextInt();
				String str = input.nextLine();
				String words[] = str.split(" ");
				ArrayList<Project> devProjects = new ArrayList<Project>();
				for (int i = 0; i < words.length; i++) {
					for (int j = 0; j < mainProjects.size(); j++) {
						if (mainProjects.get(j).getProjectName().equals(words[i])) 
							devProjects.add(mainProjects.get(j));
					}
				}		
				RegularEmployee devRegEmployee = null;
				for (int i = 0; i < mainRegularEmployees.size(); i++) {
					if (id == mainRegularEmployees.get(i).getId()) 
						devRegEmployee = mainRegularEmployees.get(i);
				}
				Developer developer = new Developer(devRegEmployee, devProjects);
				//Add objects to related ArrayLists
				developers.add(developer);
				objects1.add(developer);
			}
			
			//Customer
			else if (firstWord.equals("Customer")) {
				int customerId = input.nextInt();
				String str = input.nextLine();
				String words[] = str.split(" ");
				ArrayList<Product> cusProducts = new ArrayList<Product>();
				for (int i = 0; i < words.length; i++) {
					for (int j = 0; j < mainProducts.size(); j++) {
						if (mainProducts.get(j).getProductName().equals(words[i])) 
							cusProducts.add(mainProducts.get(j));				
					}
				}			
				Person cusPerson = null;
				for (int i = 0; i < people.size(); i++) {
					if (customerId == people.get(i).getId()) 
						cusPerson = people.get(i);					
				}
				Customer customer = new Customer(cusPerson, cusProducts);
				//Add objects to related ArrayLists
				customers.add(customer);
				objects1.add(customer);
			}
			
			//Manager
			else if (firstWord.equals("Manager")) {
				int id = input.nextInt();
				double bonusBudget = input.nextDouble();
				Employee manEmployee = null;
				for (int i = 0; i < employees.size(); i++) {
					if (id == employees.get(i).getId()) 
						manEmployee = employees.get(i);
				}
				Manager manager = new Manager(manEmployee, bonusBudget);
				//Add objects to related ArrayLists
				managers.add(manager);
				objects1.add(manager);
			}
			
			//Sales Employee
			else if (firstWord.equals("SalesEmployee")) {
				int id = input.nextInt();
				String str = input.nextLine();
				String words[] = str.split(" ");
				ArrayList<Product> salesProducts = new ArrayList<Product>();
				for (int i = 0; i < words.length; i++) {
					for (int j = 0; j < mainProducts.size(); j++) {
						if (mainProducts.get(j).getProductName().equals(words[i])) 
							salesProducts.add(mainProducts.get(j));				
					}
				}
				RegularEmployee salesRegEmployee = null;
				for (int i = 0; i < mainRegularEmployees.size(); i++) {
					if (id == mainRegularEmployees.get(i).getId()) 
						salesRegEmployee = mainRegularEmployees.get(i);					
				}
				SalesEmployee regEmp = new SalesEmployee(salesRegEmployee, salesProducts);
				//Add objects to related ArrayLists
				salesEmployees.add(regEmp);
				objects1.add(regEmp);
			}
		}
		
		//Add employees to related departments
		for (int d = 0; d < managers.size(); d++) {
			for (int b = 0; b < mainRegularEmployees.size(); b++) {
				if (managers.get(d).getDepartment().getDepartmentId() == mainRegularEmployees.get(b).getDepartment().getDepartmentId())
					managers.get(d).addEmployee(mainRegularEmployees.get(b));
			}
		}
		for (Manager man: managers) {
			for(SalesEmployee salesEmp: salesEmployees) {
				if (salesEmp.getDepartment().getDepartmentId() == man.getDepartment().getDepartmentId())
					man.addEmployee(salesEmp);
			}
			for(Developer dev : developers) {
				if (dev.getDepartment().getDepartmentId() == man.getDepartment().getDepartmentId())
					man.addEmployee(dev);
			}
			ArrayList<Integer> allIds = new ArrayList<Integer>();
			for(RegularEmployee regularEmp : mainRegularEmployees) {
				if (regularEmp.getDepartment().getDepartmentId() == man.getDepartment().getDepartmentId()) {
					for (Developer develop: developers) {
						if (develop.getId() == regularEmp.getId()) 
							allIds.add(develop.getId());
					} 
					for (SalesEmployee salesEmp : salesEmployees) {
						if (salesEmp.getId() == regularEmp.getId()) 
							allIds.add(salesEmp.getId());
					}
					if (!allIds.contains(regularEmp.getId())) 
						man.addEmployee(regularEmp);
				}
			}
		}
		
		//Distributing bonus budget and raising salary of managers
		for (int i = 0; i < managers.size(); i++) {
			managers.get(i).distributeBonusBudget();
			managers.get(i).raiseSalary(0.2);
		}
		
		//Raising salary of regular employees
		for (int i = 0; i < mainRegularEmployees.size(); i++) {
			mainRegularEmployees.get(i).raiseSalary(0.3);
		}
		
		//Raising salary of developers
		for (int i = 0; i < developers.size(); i++) {
			developers.get(i).raiseSalary(0.23);
		}
		
		//Raising salary of sales employees
		for (int i = 0; i < salesEmployees.size(); i++) {
			salesEmployees.get(i).raiseSalary(0.18);
		}
		
		//Raising salary of a specific sales employee
		double mostSales = 0;
		for (int d = 0; d < salesEmployees.get(0).getSales().size(); d++) {
			mostSales += salesEmployees.get(0).getSales().get(d).getPrice();
		}
		double newSales = 0;
		int theEmp = 0;
		for (int e = 1; e < salesEmployees.size(); e++) {
			for (int f = 0; f < salesEmployees.get(e).getSales().size(); f++) {
				newSales += salesEmployees.get(e).getSales().get(f).getPrice();
			}
			if (newSales > mostSales) 
				theEmp = e;
			}
		salesEmployees.get(theEmp).raiseSalary(1000);
		int workers = 0;
		int devPlusSales = 0;
		
		//Outputs
		//Department
		for(int c = 0; c < departments.size(); c++) {
			System.out.println("************************************************");
			System.out.println(departments.get(c).toString());	
			if (developers.size() > 0) {
				for (int i = 0; i < developers.size(); i++) {
					if (departments.get(c).getDepartmentId() == developers.get(i).getDepartment().getDepartmentId()) {
						workers++;
						devPlusSales++;
					}
				}
			}
			if(salesEmployees.size() > 0) {
				for (int i = 0; i < salesEmployees.size(); i++) {
					if (departments.get(c).getDepartmentId() == salesEmployees.get(i).getDepartment().getDepartmentId()) {
						workers++;
						devPlusSales++;
					}
				}
			}
			if (mainRegularEmployees.size() > 0) {
				for (int i = 0; i < mainRegularEmployees.size(); i++) {
					if (departments.get(c).getDepartmentId() == mainRegularEmployees.get(i).getDepartment().getDepartmentId())
						workers++;
				}
			}
			for (int i = 0; i < managers.size(); i++) {
				if (managers.get(i).getDepartment().getDepartmentId() == departments.get(c).getDepartmentId()) {
					System.out.println("	" + managers.get(i).toString());
					System.out.println("		# of Employees: " + (workers - devPlusSales) + "]"); 
				}
			}
			workers = 0;
			devPlusSales = 0;
			if (developers.size() > 0) {
				System.out.println("			1. Developer");
				for (int j = 0; j < developers.size(); j++) {
					if (developers.get(j).getDepartment().getDepartmentId() == departments.get(c).getDepartmentId()) {
						System.out.println("				" + developers.get(j).toString(1));
						System.out.println("				" + developers.get(j).toString(1, 1));
						System.out.println("				" + developers.get(j).toString(1, 1, 1));
						System.out.println("				" + developers.get(j).toString(1, 1, 1, 1));
					}
				}
			}
			if (salesEmployees.size() > 0) {
				System.out.println("			2. SalesEmployee");
				for (int j = 0; j < salesEmployees.size(); j++) {
					if (salesEmployees.get(j).getDepartment().getDepartmentId() == departments.get(c).getDepartmentId()) {
						System.out.println("				" + salesEmployees.get(j).toString(1));
						System.out.println("				" + salesEmployees.get(j).toString(1, 1));
						System.out.println("				" + salesEmployees.get(j).toString(1, 1, 1));
						System.out.println("				" + salesEmployees.get(j).getSales().toString());
					}	
				}
			}
			if(mainRegularEmployees.size() > 0){
                ArrayList<Integer> regularIds = new ArrayList<Integer>();
                for(int i = 0; i < mainRegularEmployees.size(); i++){
                    if(mainRegularEmployees.get(i).getDepartment().getDepartmentId() == departments.get(c).getDepartmentId()){
                        for (int j = 0; j < developers.size(); j++){
                            if(mainRegularEmployees.get(i).getId() == developers.get(j).getId()){
                                regularIds.add(developers.get(j).getId());
                            }
                        }
                        for (int k = 0; k < salesEmployees.size(); k++){
                            if(salesEmployees.get(k).getId() == mainRegularEmployees.get(i).getId()){
                                regularIds.add(salesEmployees.get(k).getId());
                            }
                        }
                        if(!regularIds.contains(mainRegularEmployees.get(i).getId())){
                        	System.out.println("			3. Regular Employee");
                        	System.out.println("				" + mainRegularEmployees.get(i).toString(1));;
                            System.out.println("				" + mainRegularEmployees.get(i).toString(1,1));
                            System.out.println("				" + mainRegularEmployees.get(i).toString(1,1,1));
                            System.out.println();
                        }
                    }
                }
            }
		}
		
		System.out.println();
		System.out.println();
		System.out.println();		
		
		//Customers
		System.out.println("**********************CUSTOMERS************************");
		for (int b = 0; b < customers.size(); b++) {
			System.out.println(customers.get(b).toString());
		}
		
		System.out.println();
		System.out.println();
		
		//People
		System.out.println("**********************PEOPLE************************");
		for (int a = 0; a < people.size(); a++) {
			boolean isOnlyPerson = true;
			for (int b = 0; b < customers.size(); b++) {
				if (people.get(a).getId() == customers.get(b).getId()) {
					isOnlyPerson = false;
				}
			}
			for (int c = 0; c < employees.size(); c++) {
				if (people.get(a).getId() == employees.get(c).getId())
					isOnlyPerson = false;		
			}
			if (isOnlyPerson)
				System.out.println(people.get(a).toString());
		}	
		System.out.println();
	}
	
	private static Calendar parseCalendar(String date) {
		String[] spltDate = date.split("/");
		int day = Integer.parseInt(spltDate[0]);
		int month = Integer.parseInt(spltDate[1]);
		int year = Integer.parseInt(spltDate[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar;
	}
}

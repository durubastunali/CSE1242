//Duru Baþtunalý 150120075
//Developer class

import java.util.ArrayList;
import java.util.Calendar;

public class Developer extends RegularEmployee {
	private ArrayList<Project> projects = new ArrayList<Project>();
	public static int numberOfDevelopers;
	
	//Constructors
	public Developer() {
	}
	
	public Developer(int id, String firstName, String lastName, String gender,
			Calendar birthDate, String maritalStatus, String hasDriverLicence,
			double salary, Calendar hireDate, Department department, double
			pScore, ArrayList<Project> p) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, 
				salary, hireDate, department, pScore);
		this.projects = p;
		numberOfDevelopers++;
	}
	
	public Developer(RegularEmployee re, ArrayList<Project> p) {
		super(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), re.getMaritalStatus(), re.getHasDriverLicence(), 
				re.getSalary(), re.getHireDate(), re.getDepartment(), re.getPerformanceScore());
		this.projects = p;
		numberOfDevelopers++;
	}
	
	//Getters, setters, toString, methods
	public boolean addProject(Project s) {
		return projects.add(s);
	}
	
	public boolean removeProject(Project s) {
		return projects.remove(s);
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public static int getNumberOfDevelopers() {
		return numberOfDevelopers;
	}

	public static void setNumberOfDevelopers(int numberOfDevelopers) {
		Developer.numberOfDevelopers = numberOfDevelopers;
	}

	@Override
	public String toString() {
		String strProjects = "";
		for (int i = 0; i < projects.size(); i++) {
			strProjects += projects.get(i).toString();
			if (i != projects.size() - 1)
				strProjects += ", ";
		}
		return "Developer [projects=" + strProjects + "]";
	}
	
	public String toString(int a, int b, int c, int d) {
		String strProjects = "";
		for (int i = 0; i < projects.size(); i++) {
			strProjects += projects.get(i).toString();
			if (i != projects.size() - 1)
				strProjects += ", ";
		}
		return "[" + strProjects + "]";
	}
}

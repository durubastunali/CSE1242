//Duru Baştunalı 150120075
//Department class

public class Department {
	private int departmentId;
	private String departmentName;
	
	//Constructors
	public Department() {
	}
	
	public Department(int departmentId, String departmentName) {
		setDepartmentId(departmentId);
		setDepartmentName(departmentName);
	}

	//Getters, setters, toString, methods
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		try {
			if(departmentName.length() <= 2) {
				throw new Exception("Input must be more than two letters.");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		this.departmentName = departmentName;
	}
}

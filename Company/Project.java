//Project class

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Project {
	private String projectName;
	private java.util.Calendar startDate;
	private boolean state;
	
	//Constructors
	public Project() {
	}
	
	public Project(String pName, Calendar startDate, String state) {
		setProjectName(pName);
		this.startDate = startDate;
		setState(state);
	}
	
	//Getters, setters, toString, methods
	public void setState(String state) {
		try {
			if (state.length() <= 2 && !state.equals("Open") && !state.equals("Close")) {
				throw new Exception("State must be Open or Close");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		if (state.equals("Open"))
			this.state = true;
		else
			this.state = false;
	}
	
	public String getState() {
		if (state == true)
			return "Open";
		else
			return "Close";
	}
	
	public void close() {
		this.state = false;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		try {
			if (projectName.length() <= 2) {
				throw new Exception("Project name can't be less than 3 letters");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		this.projectName = projectName;
	}

	public java.util.Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Calendar startDate) {
		this.startDate = startDate;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		Date date = startDate.getTime();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
		String projectDate = format1.format(date);
		return "Project [projectName=" + projectName + ", startDate=" + projectDate + ", state=" + state + "]";
	}	
}

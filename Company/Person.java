//Person class

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private byte gender; //1: Woman, 2: Man
	private Calendar birthDate; 
	private byte maritalStatus; //1: Single, 2: Married
	private boolean hasDriverLicence; //true: Yes, false: No
	
	//Constructors
	public Person() {
	}
	
	public Person(int id, String firstName, String lastName, String gender, Calendar birthDate, 
			String maritalStatus, String hasDriverLicence) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		
		if (gender.equals("Man"))
			setGender("Man");
		else if (gender.equals("Woman"))
			setGender("Woman");
		else
			setGender("");
		
		this.birthDate = birthDate;
		
		if (maritalStatus.equals("Single"))
			setMaritalStatus("Single");
		else if (maritalStatus.equals("Married"))
			setMaritalStatus("Married");
		else 
			setMaritalStatus("");
		
		if (hasDriverLicence.equals("Yes"))
			setHasDriverLicence("Yes");
		else if (hasDriverLicence.equals("No"))
			setHasDriverLicence("No");
		else
			setHasDriverLicence("");
	}

	//Getters, setters, toString, methods
	public void setGender(String gender) {
		try {
			if (gender.length() <= 2 && !gender.equals("Man") && !gender.equals("Woman"))
				throw new Exception("Gender must be Man or Woman");
			} 
		catch (Exception e) {
			System.out.println(e);
		}
		if (gender.equals("Man"))
			this.gender = 2;
		else if (gender.equals("Woman"))
			this.gender = 1;
	}
	
	public String getGender() {
		if (gender == 1)
			return "Woman";
		else
			return "Man";
	}
	
	public void setMaritalStatus(String status) {
		try {
			if (status.length() <= 2 && !status.equals("Married") && !status.equals("Single"))
				throw new Exception("Marital status must be Single or Married");
			} 
		catch (Exception e) {
			System.out.println(e);
		}
		if (status.equals("Single"))
			this.maritalStatus = 1;
		else if (status.equals("Married"))
			this.maritalStatus = 2;
	}
	
	public String getMaritalStatus() {
		if (maritalStatus == 1)
			return "Single";
		else
			return "Married";
	}
	
	public void setHasDriverLicence (String info) {
		try {
			if (!info.equals("Yes") && !info.equals("No"))
				throw new Exception("Driver Licence info must be Yes or No");
			} 
		catch (Exception e) {
			System.out.println(e);
		}
		if (info.equals("Yes"))
			this.hasDriverLicence = true;
		else if (info.equals("No")) 
			this.hasDriverLicence = false;
	}
	
	public String getHasDriverLicence() {
		if (hasDriverLicence == true)
			return "Yes";
		else
			return "No";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public java.util.Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
	//toString method
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		String strGender = "";
		String strMaritalStatus = "";
		String strHasDriverLicence = "";
		
		if (this.gender == 1)
			strGender = "Woman";
		else if (this.gender == 2)
			strGender = "Man";
		
		if (this.maritalStatus == 1)
			strMaritalStatus = "Single";
		else if (this.maritalStatus == 2)
			strMaritalStatus = "Married";
		
		if (this.hasDriverLicence)
			strHasDriverLicence = "Yes";
		else
			strHasDriverLicence = "No";
		
		Date date = birthDate.getTime();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
		String strDateBirth = format1.format(date);
		
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + strGender
				+ ", birthDate=" + strDateBirth + ", maritalStatus=" + strMaritalStatus + ", hasDriverLicence="
				+ strHasDriverLicence + "]";
	}
	
	public String toString(int i) {
		String strGender = "";
		if (this.gender == 1)
			strGender = "Woman";
		else if (this.gender == 2)
			strGender = "Man";
		return "Person Info [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + strGender + "]";
	}
}
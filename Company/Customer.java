//Duru Baþtunalý 150120075
//Customer class

import java.util.ArrayList;
import java.util.Calendar;

public class Customer extends Person {
	private ArrayList<Product> products = new ArrayList<Product>();
	
	//Constructors
	public Customer() {
	}
	
	public Customer(int id, String firstName, String lastName, String gender, Calendar birthDate, 
			String maritalStatus, String hasDriverLicence, ArrayList<Product> products) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
		setProducts(products);
	}
	
	public Customer(Person person, ArrayList<Product> products) {
		super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(), 
				person.getMaritalStatus(), person.getHasDriverLicence());
		setProducts(products);
	}

	//Getters, setters, toString, methods
	public ArrayList<Product> getProducts() {
		return this.products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Customer [id:" + getId() + " [products=" + products + "]";
	}
}



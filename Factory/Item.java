//Duru Baþtunalý 150120075
//Item class

public class Item {
	//Data field
	private int id;
	public static int numberOfItems;
	
	//Item constructor
	public Item(int id) {
		this.id = id;
		numberOfItems++; //Increment the numberOfItems per item an employee creates
	}

	//Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getNumberOfItems() {
		return numberOfItems;
	}

	public static void setNumberOfItems(int numberOfItems) {
		Item.numberOfItems = numberOfItems;
	}
	
	
	
}

//Duru Baþtunalý 150120075
//Storage class

public class Storage {
	//Data f
	private int capacity;
	private Item[] Items;
	
	//Storage constructor
	public Storage(int capacity) {
		this.capacity = capacity;
	}
	
	//Create another array with the recently added item
	public void addItem(Item item){
		// Add produced items into the storage
		Items = new Item[0];
		Item[] targetItem = new Item[Items.length + 1];
		for (int i=0 ; i<Items.length;i++)
			targetItem[i] = Items[i];
		    targetItem[Items.length] = item;
		    //Copy the extended array to the original array
		    Items = targetItem;
    }
	
	//Getters and setters
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Item[] getItems() {
		return Items;
	}
	public void setItems(Item[] items) {
		Items = items;
	}
	
	
}

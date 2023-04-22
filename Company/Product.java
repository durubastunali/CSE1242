//Product class

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Product {
	private String productName;
	private java.util.Calendar saleDate;
	private double price;
	
	//Constructors
	public Product() {
	}
	
	public Product(String sName, Calendar sDate, double price) {
		setProductName(sName);
		this.saleDate = sDate;
		this.price = price;
	}

	//Getters, setters, toString, methods
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		try {
			if (productName.length() <= 2) {
				throw new Exception("Product name can't be less than 3 letters");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		this.productName = productName;
	}

	public java.util.Calendar getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(java.util.Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		Date date = saleDate.getTime();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
		String strTranDate = format1.format(date);
		return "Product [productName=" + productName + ", transactionDate=" + strTranDate + ", price=" + price + "]";
	}
}

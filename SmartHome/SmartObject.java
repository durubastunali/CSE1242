//SmartObject class

public abstract class SmartObject {
	private String alias; //Name of a smart device
	private String macId; //ID for connecting internet
	private String IP; //Communicate with other devices
	private boolean connectionStatus; //Connected or not
	
	//Constructor
	public SmartObject() {
	}
	
	//Methods
	//connect method: connects the device with given IP 
	public boolean connect(String IP) {
		setIP(IP);
		System.out.println(this.alias + " connection established");
		this.connectionStatus = true;
		return this.connectionStatus;
	}
	
	//disconnect method: disconnects the device
	public boolean disconnnect() {
		setIP(null);
		this.connectionStatus = false;
		return this.connectionStatus;
	}
	
	//SmartObjectToString method: prints the info of a device
	public void SmartObjectToString() {
		System.out.println("This is " + getClass().getSimpleName() + " device " + this.alias);
		System.out.println("	MacId: " + this.macId);
		System.out.println("	IP: " + this.IP);
	}
	
	//controlConnection method: checks if the device is connected
	public boolean controlConnection() {
		if (connectionStatus == false) {
			System.out.println("This device is not connected." + getClass().getSimpleName() + " -> " + this.alias);
			return false;
		}
		return true;
	}
	
	//Abstract methods
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
	
	//Getters and Setters
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		this.IP = iP;
	}
	public boolean isConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
}

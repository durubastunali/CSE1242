//SmartCamera class

public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera>{
	private boolean status; //Whether the camera is recording or not
	private int batteryLife;
	private boolean nightVision; //Whether the camera has a night vision or not
	
	//Constructors
	public SmartCamera() {
	}
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		super.setAlias(alias);
		super.setMacId(macId);
		this.nightVision = nightVision;
		this.batteryLife = batteryLife;
	}
	
	//Methods
	//recordOn method: starts recording
	public void recordOn(boolean isDay) {
		if (super.isConnectionStatus()) {
			if ((!status && isDay) || (!status && !isDay && this.nightVision)) {
				System.out.println("Smart Camera - " + super.getAlias() + " is turned on now");
				setStatus(true);
			}
			else if ((status && isDay) || (status && !isDay && this.nightVision)) {
				System.out.println("Smart Camera - " + super.getAlias() + " has been already turned on");
				setStatus(true);
			}
			else if (!isDay && !this.nightVision) {
				System.out.println("Sorry! Smart Camera - " + super.getAlias() + " does not have night vision feature.");
			}
			else
				setStatus(false);
		}
	}
	
	//recordOff method: stops recording
	public void recordOff() {
		if (super.isConnectionStatus()) {
			if (status) 
				System.out.println("Smart Camera - " + super.getAlias() + " is turned off now");
			else 
				System.out.println("Smart Camera - " + super.getAlias() + " has been already turned off");
		}
		setStatus(false);
	}
	
	//testObject method: starts testing the object
	public boolean testObject() {
		if(super.isConnectionStatus()) {
			System.out.println("Test is starting for SmartCamera");
			super.SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			recordOn(true);
			recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			recordOn(false);
			recordOff();
			System.out.println("Test completed for SmartCamera");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	//shutDownObject method: stops recording
	public boolean shutDownObject() {
		if (super.isConnectionStatus()) {
			super.SmartObjectToString();
			if (this.status) {
				recordOff();
			}
			return true;
		}
		else 
			return false; 
	}
	
	//controlMotion method: checks whether there's a motion
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		if (hasMotion) {
			System.out.println("Motion detected!");
			if (isDay) 
				recordOn(true);
			else {
				if (this.nightVision)
					recordOn(false);
			}
			return true;
		}
		else
			System.out.println("Motion not detected!");
			return false;
	}
	
	//compareTo method: compares battery life
	public int compareTo(SmartCamera smartCamera) {
		int batStat = 0;
		if (this.batteryLife > smartCamera.batteryLife) 
			batStat = 1;
		
		else if (this.batteryLife == smartCamera.batteryLife) 
			batStat = 0;
		else if (this.batteryLife < smartCamera.batteryLife)
			batStat = -1;
		return batStat;
	}
	
	//toString method: returns whether camera is recording or not
	@Override
	public String toString() {
		String stat = "";
		if (status)
			stat = "recording";
		else
			stat = "not recording";
		return "Smart Camera -> " + super.getAlias() + "'s battery life is " + this.batteryLife + " status is " + stat;
	}
	
	//Getters and Setters
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}
	public boolean isNightVision() {
		return nightVision;
	}
	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
}

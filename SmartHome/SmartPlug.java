//SmartPlug class

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartPlug extends SmartObject implements Programmable {
	private boolean status; //Whether the plug is on or off
	private boolean programAction;
	private Calendar programTime; //Eventually setted time
	private Calendar displayedTime = new GregorianCalendar(); //Current time to be setted
	private Calendar currentTime = new GregorianCalendar(); //Current time
	
	//Constructors
	public SmartPlug() {
	}
	public SmartPlug(String alias, String macId) {
		super.setAlias(alias);
		super.setMacId(macId);
	}
	
	//Methods
	//turnOn method: turns on the plug	
	public void turnOn() {
		if (super.isConnectionStatus()) {
			if (!status) {
				System.out.println("Smart Plug - " + super.getAlias() + " is turned on now (Current time: "
					+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
					+ ":" + currentTime.get(Calendar.SECOND) + ")");
			}
			else	 
				System.out.println("Smart Plug - " + super.getAlias() + " has been already turned on");
		}
		this.status = true;
	}
	
	//turnOff method: turns off the plug
	public void turnOff() {
		if (super.isConnectionStatus()) {
			if (status) {
				System.out.println("Smart Plug - " + super.getAlias() + " is turned off now (Current time: "
						+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
						+ ":" + currentTime.get(Calendar.SECOND) + ")");
			}
			else 
				System.out.println("Smart Plug - " + super.getAlias() + " has been already turned off");
		}
		this.status = false;
	}
	
	//testObject method: tests the object by turning it on and off
	public boolean testObject() {
		if (super.isConnectionStatus()) {
			System.out.println("Test is starting for SmartPlug");
			super.SmartObjectToString();
			turnOn();
			turnOff();
			System.out.println("Test completed for SmartPlug");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	//shutDownObject method: shuts the plug down
	public boolean shutDownObject() {
		if (super.isConnectionStatus()) {
			super.SmartObjectToString();
			if (this.status) {
				turnOff();
			}
			return true;
		}
		else 
			return false; 
	}
	
	//setTimer method: turn on or off plugs based on the given seconds
	public void setTimer (int seconds) {
		displayedTime.set(Calendar.SECOND, currentTime.get(Calendar.SECOND) + seconds);
		if (super.isConnectionStatus()) {
			if(isStatus()) {
				System.out.println("Smart plug - " + super.getAlias() + " will be turned off " + seconds + " seconds later! (Current Time: "
						+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE) + ":" + currentTime.get(Calendar.SECOND)
						+ ")");
			}
			else {
				System.out.println("Smart plug - " + super.getAlias() + " will be turned on " + seconds + " seconds later! (Current Time: "
						+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE) + ":" + currentTime.get(Calendar.SECOND)
						+ ")");
			}
		}
		setProgramTime(displayedTime);
	}

	//cancelTimer method: sets programTime to null
	public void cancelTimer() {
		if (super.isConnectionStatus()) {
			setProgramTime(null);
		}
	}

	//runProgram method: sets programAction and then turns on the plug if off/turns off the plug if on
	public void runProgram() {
		if (this.programTime != null) {
			currentTime = new GregorianCalendar();
			if (displayedTime.get(Calendar.SECOND) == currentTime.get(Calendar.SECOND)) {
				setProgramTime(null);
				setProgramAction(true);	
			}
			else 
				setProgramAction(false);
			if (super.isConnectionStatus()) {
				if (isProgramAction()) {
					System.out.println("runProgram -> " + getClass().getSimpleName() + " - " + getAlias());
					setProgramTime(null);
					if (status)
						turnOff();
					else
						turnOn();
				}
			}
		}
	}

	//Getters and Setters
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Calendar getProgramTime() {
		return programTime;
	}
	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}
	public boolean isProgramAction() {
		return programAction;
	}
	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	public Calendar getDisplayedTime() {
		return displayedTime;
	}
	public void setDisplayedTime(Calendar displayedTime) {
		this.displayedTime = displayedTime;
	}
	public Calendar getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Calendar currentTime) {
		this.currentTime = currentTime;
	}
}

//SmartLight Class

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable {
	private boolean hasLightTurned; //Whether the light is on or off
	private boolean programAction;
	private Calendar programTime; //Eventually setted time
	private Calendar displayedTime = new GregorianCalendar(); //Current time to be setted
	private Calendar currentTime = new GregorianCalendar(); //Current time
	
	//Constructors
	public SmartLight() {	
	}
	public SmartLight(String alias, String macId) {	
		super.setAlias(alias);
		super.setMacId(macId);	
	}
	
	//Methods
	//turnOnLight method: turns on the light
	public void turnOnLight() {
		if (super.isConnectionStatus()) {
			if (!hasLightTurned) {
				System.out.println("Smart Light - " + super.getAlias() + " is turned on now (Current time: "
					+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
					+ ":" + currentTime.get(Calendar.SECOND) + ")");
			}
			else 
				System.out.println("Smart Light - " + super.getAlias() + " has been already turned on");
		}
		this.hasLightTurned = true;
	}
	
	//turnOffLight method: turns off the light
	public void turnOffLight() {		
		if (super.isConnectionStatus()) {
			if (hasLightTurned) {
				System.out.println("Smart Light - " + super.getAlias() + " is turned off now (Current time: "
					+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
					+ ":" + currentTime.get(Calendar.SECOND) + ")");
			}
			else 
				System.out.println("Smart Light - " + super.getAlias() + " has been already turned off");
		}
		this.hasLightTurned = false;
	}
	
	//testObject method: tests the object by turning it on and off
	public boolean testObject() {
		if (super.isConnectionStatus()) {
			System.out.println("Test is starting for SmartLight");
			super.SmartObjectToString();
			turnOnLight();
			turnOffLight();
			System.out.println("Test completed for SmartLight");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	//shutDownObject: shuts the light down
	public boolean shutDownObject() {
		if (super.isConnectionStatus()) {
			super.SmartObjectToString();
			if (this.hasLightTurned) {
				turnOffLight();
			}
			return true;
		}
		else 
			return false; 
	}
	
	//onLeave method: turns off the light
	public void onLeave() {
		if (super.isConnectionStatus()) {
			setHasLightTurned(false);
			System.out.println("On Leave -> Smart Light - " + super.getAlias());
			System.out.println("Smart Light - " + super.getAlias() + " is turned off now (Current time: "
					+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
					+ ":" + currentTime.get(Calendar.SECOND) +")");
		}
	}
	
	//onCome method: turns on the light
	public void onCome() {	
		if (super.isConnectionStatus()) {
			setHasLightTurned(true);
			System.out.println("On Come -> Smart Light - " + super.getAlias());
			System.out.println("Smart Light - " + super.getAlias() + " is turned on now (Current time: "
					+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE)
					+ ":" + currentTime.get(Calendar.SECOND) + ")");
		}
	}
	
	//setTimer method: turn on or off lights based on the given seconds
	public void setTimer(int seconds) {	
		this.displayedTime.set(Calendar.SECOND, currentTime.get(Calendar.SECOND) + seconds);
		if (super.isConnectionStatus()) {
			if(isHasLightTurned()) {
				System.out.println("Smart light - " + super.getAlias() + " will be turned off " + seconds + " seconds later! (Current Time: "
						+ currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE) + ":" + currentTime.get(Calendar.SECOND)
						+ ")");
			}
			else {
				System.out.println("Smart light - " + super.getAlias() + " will be turned on " + seconds + " seconds later! (Current Time: "
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
	
	//runProgram method: sets programAction and then turns on the light if off/turns off the light if on
	public void runProgram() {	
		if(this.programTime != null) {
			currentTime = new GregorianCalendar();
			if (displayedTime.get(Calendar.SECOND)== currentTime.get(Calendar.SECOND)) {
				setProgramTime(null);
				setProgramAction(true);
			}
			else 
				setProgramAction(false);
			if (super.isConnectionStatus()) {
				if (isProgramAction()) {
					System.out.println("runProgram -> " + getClass().getSimpleName() + " - " + getAlias());
					setProgramTime(null);
					if (hasLightTurned)
						turnOffLight();
					else
						turnOnLight();
				}
			}
		}
	}
	
	//Getters and Setters
	public boolean isHasLightTurned() {
		return hasLightTurned;
	}
	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
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

//SmartHome class

import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>(); //ArrayList of all smart devices
	
	//Constructor
	public SmartHome() {
	}
	
	//Methods
	//addSmartObject method: add the given smartObject and declare an IP
	public boolean addSmartObject(SmartObject smartObject) {
		smartObjectList.add(smartObject);
		String stringIndexIP = "";
		int intIndexIP = 100;
		String defaultIP = "10.0.0.";
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i).equals(smartObject))
				intIndexIP += i;
		}
		stringIndexIP = intIndexIP + "";
		defaultIP = defaultIP + stringIndexIP;
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("--------------------------------------------------------------------------");
		smartObject.connect(defaultIP);
		smartObject.testObject();
		return true;
	}
	
	//removeSmartObject method: removes the given smart object from the ArrayList
	public boolean removeSmartObject(SmartObject smartObject) {
		return smartObjectList.remove(smartObject);
	}
	
	//controlLocation method: invokes onCome or onLeave methods
	public void controlLocation(boolean onCome) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LocationControl: OnCome");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartLight) {
				SmartLight smartLightObj = (SmartLight)(smartObjectList.get(i));
				if (onCome) 
					smartLightObj.onCome();
				else
					smartLightObj.onLeave();
			}
		}
	}
	
	//controlMotion method: invokes SmartCamera's controlMotion method
	public void controlMotion(boolean hasMotion, boolean isDay) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("MotionControl: HasMotion, isDay");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartCamera) {
				SmartCamera smartCameraObj = (SmartCamera)(smartObjectList.get(i));
				smartCameraObj.controlMotion(hasMotion, isDay);
			}
		}
	}
	
	//controlProgrammable method: invokes runProgram at the exact time
	public void controlProgrammable() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: runProgram");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartLight) {
				SmartLight smartLightObj = (SmartLight)(smartObjectList.get(i));	
				smartLightObj.runProgram(); 
			}
			else if(smartObjectList.get(i) instanceof SmartPlug) {
				SmartPlug smartPlugObj = (SmartPlug)(smartObjectList.get(i));
				smartPlugObj.runProgram();
			}
		}
	}
	
	//controlTimer method: sets a time for a device
	public void controlTimer(int seconds) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 10 seconds");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartLight) {
				SmartLight smartLightObj = (SmartLight)(smartObjectList.get(i));
				if (seconds > 0)
					smartLightObj.setTimer(seconds);
				else
					smartLightObj.cancelTimer();
			}
			else if (smartObjectList.get(i) instanceof SmartPlug) {
				SmartPlug smartPlugObj = (SmartPlug)(smartObjectList.get(i));
				if (seconds > 0)
					smartPlugObj.setTimer(seconds);
				else
					smartPlugObj.cancelTimer();
			}
		}
	}
	
	//controlTimerRandomly: sets a time for a device randomly
	public void controlTimerRandomly() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 0, 5 or 10 seconds randomly");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartLight) {
				SmartLight smartLightObj = (SmartLight)(smartObjectList.get(i));
				int randomNumber = (int)(Math.random() * 3) * 5;
				if (randomNumber > 0)
					smartLightObj.setTimer(randomNumber);
				else
					smartLightObj.cancelTimer();
			}
			else if (smartObjectList.get(i) instanceof SmartPlug) {
				SmartPlug smartPlugObj = (SmartPlug)(smartObjectList.get(i));
				int randomNumber = (int)(Math.random() * 3) * 5;
				if (randomNumber > 0)
					smartPlugObj.setTimer(randomNumber);
				else
					smartPlugObj.cancelTimer();
			}
		}
	}
	
	//sortCameras method: sort cameras based on their battery life
	public void sortCameras() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("--------------------------------------------------------------------------");
		int numberOfCams = 0;
		ArrayList<SmartCamera> smartCameras = new ArrayList<SmartCamera>();
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (smartObjectList.get(i) instanceof SmartCamera) {
				numberOfCams++;
				SmartCamera smartCamObj = (SmartCamera)(smartObjectList.get(i));
				smartCameras.add(smartCamObj);
			}
		}
		int[] batterySort = new int[numberOfCams];
		for (int i = 0; i < smartCameras.size(); i++) {
			batterySort[i] = smartCameras.get(i).getBatteryLife();	
		}
		Arrays.sort(batterySort);
		for (int i = 0; i < batterySort.length; i++) {
			for (int j = 0; j < smartCameras.size(); j++) {
				if (batterySort[i] == smartCameras.get(j).getBatteryLife()) 
					System.out.println(smartCameras.get(j).toString());
			}
		}		 
	}
	 
	//Getters and Setters
	public ArrayList<SmartObject> getSmartObjectList() {	
		return smartObjectList;
	}	
	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}	
}

package configurations;

import APIs.Chassis;
import APIs.Crown;
import APIs.Gyroscope;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;

public class RobotConfiguration {
	private boolean chassisAdded, pneumaticsAdded, hybridWheelsAdded, wenchAdded, crownAdded, gyroscopeAdded;
	private Chassis chassis;
	private Pneumatics pneumatics;
	private HybridWheels hybridWheels;
		private boolean wheelsToggled;
	private Wench wench;
	private Crown crown;
		private boolean crownToggled;
	private Gyroscope gyroscope;
	
	public RobotConfiguration() {
		chassisAdded = false;
		pneumaticsAdded = false;
		hybridWheelsAdded = false;
			wheelsToggled = false;
		wenchAdded = false;
		crownAdded = false;
			crownToggled = false;
		gyroscopeAdded = false;
	}
	
	public void addChassis(Chassis chassis) {
		chassisAdded = true;
		this.chassis = chassis;
	}
	
	public void updateChassisLeftSide(double leftX, double leftY) {
		chassis.driveLeftSide(leftY);
	}
	
	public void updateChassisRightSide(double rightX, double rightY) {
		chassis.driveRightSide(rightY);
	}
	
	public void updateChassis(double leftX, double leftY, double rightX, double rightY) {
		if(!chassisAdded) return;
		chassis.drive(leftY, rightY);
	}
	
	public void addPneumatics(Pneumatics pneumatics) {
		pneumaticsAdded = true;
		this.pneumatics = pneumatics;
	}
	
	public void updatePneumatics() {
		if(!pneumaticsAdded) return;
		pneumatics.compressAirIfNeeded();
	}
	
	public void addHybridWheels(HybridWheels hybridWheels) {
		hybridWheelsAdded = true;
		this.hybridWheels = hybridWheels;
	}
	
	public void updateHybridWheels(boolean toggleGrip) {
		if(!hybridWheelsAdded) return;
		if(toggleGrip && !wheelsToggled) {
			hybridWheels.toggleGrip(); wheelsToggled = true;
		} else if(!toggleGrip && wheelsToggled) wheelsToggled = false;
	}
	
	public void addWench(Wench wench) {
		wenchAdded = true;
		this.wench = wench;
	}
	
	public void updateWench(boolean up, boolean down) {
		if(!wenchAdded) return;
		if(up) wench.climb();
		else if(down) wench.fall();
		else wench.stop();
	}
	
	public void addCrown(Crown crown) {
		crownAdded = true;
		this.crown = crown;
	}
	
	public void updateCrown(boolean toggleGrip) {
		if(!crownAdded) return;
		if(toggleGrip && !crownToggled) {
			crown.togglePosition(); crownToggled = true;
		} else if(!toggleGrip && crownToggled) crownToggled = false;
	}
	
	public void addGyroscope(Gyroscope gyroscope) {
		gyroscopeAdded = true;
		this.gyroscope = gyroscope;
		this.gyroscope.reset();
	}
	
	public boolean getGyroscopeAdded() {
		return gyroscopeAdded;
	}
}

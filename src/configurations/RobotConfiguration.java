package configurations;

import APIs.Chassis;
import APIs.Gyroscope;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;

public class RobotConfiguration {
	private boolean chassisAdded, pneumaticsAdded, hybridWheelsAdded, wenchAdded, gyroscopeAdded;
	private Chassis chassis;
	private Pneumatics pneumatics;
	private HybridWheels hybridWheels;
		private boolean toggled;
	private Wench wench;
	private Gyroscope gyroscope;
	
	public RobotConfiguration() {
		chassisAdded = false;
		pneumaticsAdded = false;
		hybridWheelsAdded = false;
			toggled = false;
		wenchAdded = false;
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
		if(toggleGrip && !toggled) {
			hybridWheels.toggleGrip(); toggled = true;
		} else if(!toggleGrip && toggled) toggled = false;
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
	
	public void addGyroscope(Gyroscope gyroscope) {
		gyroscopeAdded = true;
		this.gyroscope = gyroscope;
		this.gyroscope.reset();
	}
	
	public boolean getGyroscopeAdded() {
		return gyroscopeAdded;
	}
}

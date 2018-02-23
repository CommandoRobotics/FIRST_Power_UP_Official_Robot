package configurations;

import APIs.Chassis;
import APIs.Gyroscope;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;

public class PowerUPRobotConfiguration extends RobotConfiguration{
	public PowerUPRobotConfiguration(Chassis chassis, Pneumatics pneumatics, HybridWheels hybridWheels, Wench wench, Gyroscope gyroscope) {
		this.addChassis(chassis);
		this.addPneumatics(pneumatics);
		this.addHybridWheels(hybridWheels);
		this.addWench(wench);
		this.addGyroscope(gyroscope);
	}
}

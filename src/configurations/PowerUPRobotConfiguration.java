package configurations;

import APIs.Chassis;
import APIs.Crown;
import APIs.Gyroscope;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;

public class PowerUPRobotConfiguration extends RobotConfiguration{
	public PowerUPRobotConfiguration(Chassis chassis, Pneumatics pneumatics, HybridWheels hybridWheels, Wench wench, Crown crown,
			Gyroscope gyroscope) {
		this.addChassis(chassis);
		this.addPneumatics(pneumatics);
		this.addHybridWheels(hybridWheels);
		this.addWench(wench);
//		this.addCrown(crown);
		this.addGyroscope(gyroscope);
	}
}

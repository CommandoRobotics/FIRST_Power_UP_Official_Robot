package configurations;

import APIs.Chassis;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PowerUPRobotConfiguration extends RobotConfiguration{
	public PowerUPRobotConfiguration(Chassis chassis, Pneumatics pneumatics, HybridWheels hybridWheels, Wench wench, Gyro gyroscope) {
		this.addChassis(chassis);
		this.addPneumatics(pneumatics);
		this.addHybridWheels(hybridWheels);
		this.addWench(wench);
		this.addGyroscope(gyroscope);
	}
}

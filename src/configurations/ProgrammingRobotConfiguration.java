package configurations;

import APIs.Chassis;

public class ProgrammingRobotConfiguration extends RobotConfiguration {
	public ProgrammingRobotConfiguration(Chassis chassis) {
		this.addChassis(chassis);
	}
}

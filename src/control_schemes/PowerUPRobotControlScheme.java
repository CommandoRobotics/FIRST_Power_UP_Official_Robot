package control_schemes;

import configurations.RobotConfiguration;

public class PowerUPRobotControlScheme extends RobotControlScheme {

	public PowerUPRobotControlScheme(RobotConfiguration robotConfiguration) {
		super(robotConfiguration);
	}	
	
	public void updateRobot() {
		robotConfiguration.updateChassis(left_joystick_x, -left_joystick_y, right_joystick_x, -right_joystick_y);
		robotConfiguration.updateHybridWheels((x || b));
		robotConfiguration.updateWench(y, a);
	}
}

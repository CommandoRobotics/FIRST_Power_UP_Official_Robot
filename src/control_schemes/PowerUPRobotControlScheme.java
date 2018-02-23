package control_schemes;

import configurations.RobotConfiguration;

public class PowerUPRobotControlScheme extends RobotControlScheme {

	public PowerUPRobotControlScheme(RobotConfiguration robotConfiguration) {
		super(robotConfiguration);
	}	
	
	public void updateRobot() {
		robotConfiguration.updateChassis(left_joystick_x, -left_joystick_y, right_joystick_x, -right_joystick_y);
		robotConfiguration.updateHybridWheels(x || b || right_joystick_thumb);
		robotConfiguration.updateWench(left_joystick_trigger || y, right_joystick_trigger || a);
	}
}

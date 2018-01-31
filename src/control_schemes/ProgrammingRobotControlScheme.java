package control_schemes;

import configurations.RobotConfiguration;
import edu.wpi.first.wpilibj.Joystick;

public class ProgrammingRobotControlScheme extends RobotControlScheme {

	public ProgrammingRobotControlScheme(RobotConfiguration robotConfiguration) {
		super(robotConfiguration);
	}

	public void updateXBoxOnly(Joystick xbox) {
		leftJoystick(0, -xbox.getRawAxis(XBOX_LEFT_Y));
		rightJoystick(0, -xbox.getRawAxis(XBOX_RIGHT_Y));
	}

	public void updateJoysticksAndXBox(Joystick leftJoystick, Joystick rightJoystick, Joystick xbox) {
		
	}
	
	public void updateRobot() {
		robotConfiguration.updateChassis(left_joystick_x, left_joystick_y, right_joystick_x, right_joystick_y);;
	}
}

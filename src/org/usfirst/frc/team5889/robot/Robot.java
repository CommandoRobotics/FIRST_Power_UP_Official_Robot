package org.usfirst.frc.team5889.robot;

import APIs.Chassis;
import APIs.HybridWheels;
import APIs.Pneumatics;
import configurations.RobotConfiguration;
import control_schemes.PowerUPRobotControlScheme;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Controls
	Joystick leftJoystick;
	Joystick rightJoystick;
	Joystick xbox;
	
	Chassis chassis;
	
	Pneumatics pneumatics;
	HybridWheels hybridWheels;
	
	RobotConfiguration robotConfiguration;
	PowerUPRobotControlScheme robotControlScheme;
		
	Timer timer = new Timer();
	
	//Joystick varables
	double xValue;
	double yValue;
		
	public void robotInit() {
		chassis = new Chassis(0, 1, 2, 3);
		hybridWheels = new HybridWheels(4, 5, 6, 7);
		
		pneumatics = new Pneumatics(0, 1);
		
		robotConfiguration = new RobotConfiguration();
		robotControlScheme = new PowerUPRobotControlScheme(robotConfiguration);
		
//		autonomousRecorder = new AutonomousRecorder();
		
		xbox = new Joystick(0);		
	}

	public void autonomousInit() {
		timer.reset();
		timer.start();
		chassis.drive(0.5, 0.5);
		Timer.delay(3);
		chassis.drive(0, 0);
	}

	public void autonomousPeriodic() {
		
	}

	public void teleopInit() {
		chassis.drive(0, 0);
//		autonomousRecorder.beginAutonomousRecording(robotControlScheme);
	}

	public void teleopPeriodic() {
//		try {
//			autonomousRecorder.updateFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		robotControlScheme.updateXBoxOnly(xbox);
		robotControlScheme.updateRobot();
	}
	
	public void testPeriodic() {

	}
}


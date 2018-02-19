package org.usfirst.frc.team5889.robot;

import APIs.Chassis;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;
import configurations.PowerUPRobotConfiguration;
import control_schemes.PowerUPRobotControlScheme;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	Wench wench;
	Gyro gyroscope;
	UsbCamera cameraOne;
	UsbCamera cameraTwo;
	
	SmartDashboard dashboard;
	
	PowerUPRobotConfiguration robotConfiguration;
	PowerUPRobotControlScheme robotControlScheme;
		
	Timer timer = new Timer();
	
	SendableChooser<String> autonomousChooser;
		
	public void robotInit() {
		chassis = new Chassis(9, 7, 8, 6);
		pneumatics = new Pneumatics();
		hybridWheels = new HybridWheels(5, 6);
		wench = new Wench(5);
		gyroscope = new AnalogGyro(1);
		cameraOne = CameraServer.getInstance().startAutomaticCapture(0);
		cameraTwo = CameraServer.getInstance().startAutomaticCapture(1);
		
		robotConfiguration = new PowerUPRobotConfiguration(chassis, pneumatics, hybridWheels, wench, gyroscope);
		
		robotControlScheme = new PowerUPRobotControlScheme(robotConfiguration);
		
//		autonomousRecorder = new AutonomousRecorder();
		
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		xbox = new Joystick(2);	
		
		autonomousChooser = new SendableChooser<>();
		autonomousChooser.addDefault("Don't Run Program", "Don't Run Program");
		autonomousChooser.addObject("Drive Straight", "Drive Straight");
		autonomousChooser.addObject("Drop Block Off On Switch", "Drop Block Off On Switch");
		SmartDashboard.putNumber("Gyro", 0);
		SmartDashboard.putData("Autonomous Selection", autonomousChooser);
	}

	public void autonomousInit() {
		gyroscope.reset();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(autonomousChooser.getSelected().equals("Drive Straight")) driveStraight();
		else if(autonomousChooser.getSelected().equals("Drop Block Off On Switch")) dropBlockOffOnSwitch(gameData);	
		else if(autonomousChooser.getSelected().equals("Drop Block Off On Scale")) dropBlockOffOnScale(gameData);
	}
	
	private void driveStraight() {
		timer.reset();
		timer.start();
		chassis.drive(0.5, 0.5);
		Timer.delay(3);
		chassis.drive(0, 0);
	}
	
	private void dropBlockOffOnSwitch(String gameData) {
		timer.reset();
		timer.start();
		chassis.drive(0.5, 0.5);
		Timer.delay(3);
		chassis.drive(0, 0);
		Timer.delay(0.1);
	}
	
	private void dropBlockOffOnScale(String gameData) {
		
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
		pneumatics.compressAirIfNeeded();
		
		robotControlScheme.updateJoysticksAndXBox(leftJoystick, rightJoystick, xbox);
//		robotControlScheme.updateXBoxOnly(leftJoystick);
		robotControlScheme.updateRobot();
		SmartDashboard.putNumber("Gyro", gyroscope.getAngle());
	}
	
	public void testPeriodic() {

	}
}


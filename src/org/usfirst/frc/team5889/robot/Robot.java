package org.usfirst.frc.team5889.robot;

import APIs.Chassis;
import APIs.Gyroscope;
import APIs.HybridWheels;
import APIs.Pneumatics;
import APIs.Wench;
import configurations.PowerUPRobotConfiguration;
import control_schemes.PowerUPRobotControlScheme;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
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
	//Distances and Times
	private static final double WALL_TO_AUTO_LINE = 10;
	private static final double WALL_TO_SWITCH = 14;
	private static final double WALL_TO_SCALE = 27;
	private static final double TO_OTHER_SIDE_OF_SWITCH = 16;
	private static final double TO_OTHER_SIDE_OF_SCALE = 16;
	private static final double HALF_POWER_NINETY_DEGREE_ROTATION_TIME = 1.2;
	
	//Robot Positions
	private static final int LEFT = 1;
	private static final int CENTER = 2;
	private static final int RIGHT = 3;
	
	//Controls
	Joystick leftJoystick;
	Joystick rightJoystick;
	Joystick xbox;
	
	Chassis chassis;
	Pneumatics pneumatics;
	HybridWheels hybridWheels;
	Wench wench;
	Gyroscope gyroscope;
	
	DigitalInput limitSwitch;
	UsbCamera cameraOne;
	UsbCamera cameraTwo;
	
	SmartDashboard dashboard;
	
	PowerUPRobotConfiguration robotConfiguration;
	PowerUPRobotControlScheme robotControlScheme;
	Timer timer = new Timer();
	
	SendableChooser<String> autonomousChooser;
	SendableChooser<String> robotPositionChooser;
		
	public void robotInit() {
		chassis = new Chassis(9, 7, 8, 6);
		pneumatics = new Pneumatics();
		hybridWheels = new HybridWheels(5, 6);
		wench = new Wench(5);
		gyroscope = new Gyroscope();
		
		cameraOne = CameraServer.getInstance().startAutomaticCapture(0);
		cameraTwo = CameraServer.getInstance().startAutomaticCapture(1);
		limitSwitch = new DigitalInput(0);
		
		robotConfiguration = new PowerUPRobotConfiguration(chassis, pneumatics, hybridWheels, wench, gyroscope);
		
		robotControlScheme = new PowerUPRobotControlScheme(robotConfiguration);
		
//		autonomousRecorder = new AutonomousRecorder();
		
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		xbox = new Joystick(2);	
		
		autonomousChooser = new SendableChooser<>();
		autonomousChooser.addDefault("Don't Run Program", "Don't Run Program");
		autonomousChooser.addObject("Cross Auto Line", "Cross Auto Line");
		autonomousChooser.addObject("Drive To Switch And Drop", "Drive To Switch And Drop");
		autonomousChooser.addObject("Drive To Scale And Drop", "Drive To Scale And Drop");
		
		robotPositionChooser = new SendableChooser<>();
		robotPositionChooser.addDefault("Left", "Left");
		robotPositionChooser.addObject("Center", "Center");
		robotPositionChooser.addObject("Right", "Right");
		
		SmartDashboard.putNumber("Gyro", 0);
		SmartDashboard.putData("Autonomous Selection", autonomousChooser);
	}

	public void autonomousInit() {
		hybridWheels.close();
		gyroscope.reset();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		int location = deriveRobotLocation();
		if(autonomousChooser.getSelected().equals("Cross Auto Line")) crossAutoLine();
		else if(autonomousChooser.getSelected().equals("Drive To Switch And Drop")) driveToSwitchAndDrop(location, gameData);
		else if(autonomousChooser.getSelected().equals("Drive To Scale And Drop")) driveToScaleAndDrop(location, gameData);
	}
	
	private int deriveRobotLocation() {
		if(robotPositionChooser.getSelected().equals("Left")) return LEFT;
		else if(robotPositionChooser.getSelected().equals("Center")) return CENTER;
		else if(robotPositionChooser.getSelected().equals("Right")) return RIGHT;
		
		return -1;
	}
	
	private void proceedForward(double distance) {
		timer.reset();
		chassis.drive(0.525, 0.475);
		timer.start();
		Timer.delay(3.4 * (distance / WALL_TO_SWITCH));
		chassis.drive(0, 0);
	}
	
	private void rotate(double rotation) {
		timer.reset();
		timer.start();
		if(rotation >= 0) chassis.drive(0.5, -0.5);
		else chassis.drive(-0.5, 0.5);
		Timer.delay(HALF_POWER_NINETY_DEGREE_ROTATION_TIME * (rotation / 90));
		chassis.drive(0, 0);
	}
	
	private void crossAutoLine() {
		proceedForward(WALL_TO_AUTO_LINE + 3);
		chassis.drive(0, 0);
	}
	
	private void driveToSwitchAndDrop(int robotLocation, String gameData) {
		//1 is left, 3 is right
		char correspondingColorPosition = gameData.charAt(0);
		int scoringPosition = (correspondingColorPosition == 'L') ? 1 : ((correspondingColorPosition == 'R') ? 3 : 0);
		
		//If this is negative, our robot needs to go left. If it is positive, our robot needs to go right.
		//Otherwise, the scoring position is just ahead.
		int relativeScoringPosition = scoringPosition - robotLocation;
		
		double adjustment = 0;
		if(relativeScoringPosition != 0) adjustment = WALL_TO_SWITCH - 5;
		
		//Let's move forward off of the wall.
		proceedForward(WALL_TO_SWITCH - adjustment);
		Timer.delay(0.5);
		
		//We have now driven forward. If we are not lined up with our scoring location already, we need to move to the side now.
		//Otherwise, we can skip ahead to turning to face the switch.
		if(relativeScoringPosition < 0) {
			rotate(-90);
			proceedForward(TO_OTHER_SIDE_OF_SWITCH * Math.abs(relativeScoringPosition / 2));
			rotate(90);
			proceedForward(WALL_TO_SWITCH - adjustment);
		} else if(relativeScoringPosition > 0) {
			rotate(90);
			proceedForward(TO_OTHER_SIDE_OF_SWITCH * Math.abs(relativeScoringPosition / 2));
			rotate(-90);
			proceedForward(WALL_TO_SWITCH - adjustment);
		}
		
		//Now we need to face the switch.
		double rotation = 0;
		if(scoringPosition == LEFT) rotation = 90;
		else if(scoringPosition == RIGHT) rotation = -90;
		
		//Complete the rotation, drive forward to close any distance between our bot and the switch, then let the block fall.
		rotate(rotation);
		proceedForward(1.5);
		hybridWheels.open();
	}
	
	private void driveToScaleAndDrop(int robotLocation, String gameData) {
		//1 is left, 3 is right
		char correspondingColorPosition = gameData.charAt(0);
		int scoringPosition = (correspondingColorPosition == 'L') ? 1 : ((correspondingColorPosition == 'R') ? 3 : 0);
		
		//If this is negative, our robot needs to go left. If it is positive, our robot needs to go right.
		//Otherwise, the scoring position is just ahead.
		int relativeScoringPosition = scoringPosition - robotLocation;
		
		double adjustment = 0;
		if(relativeScoringPosition != 0) adjustment = WALL_TO_SCALE - 5;
		
		//Let's move forward off of the wall.
		proceedForward(WALL_TO_SCALE - adjustment);
		Timer.delay(0.5);
		
		//We have now driven forward. If we are not lined up with our scoring location already, we need to move to the side now.
		//Otherwise, we can skip ahead to turning to face the scale.
		if(relativeScoringPosition < 0) {
			rotate(-90);
			proceedForward(TO_OTHER_SIDE_OF_SCALE * Math.abs(relativeScoringPosition / 2));
			rotate(90);
			proceedForward(WALL_TO_SCALE - adjustment);
		} else if(relativeScoringPosition > 0) {
			rotate(90);
			proceedForward(TO_OTHER_SIDE_OF_SCALE * Math.abs(relativeScoringPosition / 2));
			rotate(-90);
			proceedForward(WALL_TO_SCALE - adjustment);
		}
		
		//Now we need to face the scale.
		double rotation = 0;
		if(scoringPosition == LEFT) rotation = 90;
		else if(scoringPosition == RIGHT) rotation = -90;
		
		//Complete the rotation, drive forward to close any distance between our bot and the scale, then let the block fall.
		rotate(rotation);
		proceedForward(1.5);
		hybridWheels.open();
	}
	
	public void autonomousPeriodic() {
		
	}

	public void teleopInit() {
		chassis.drive(0, 0);
		gyroscope.reset();
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
		
		//False is pressed and true is unpressed
		System.out.println(limitSwitch.get());
	}
	
	public void testPeriodic() {

	}
}


package APIs;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Chassis {
	private Spark leftFront;
	private Spark rightFront;
	private Spark leftRear;
	private Spark rightRear;
	
	double scale;
	
	public Chassis(int LFPort, int LRPort, int RFPort, int RRPort) {
		leftFront = new Spark(LFPort);
		leftRear = new Spark(LRPort);
		rightFront = new Spark(RFPort);
		rightRear = new Spark(RRPort);
		
		scale = 0.8;
	}
	
	public void drive(double leftY, double rightY) {
		if(Math.abs(leftY) < 0.04) leftY = 0;
		if(Math.abs(rightY) < 0.04) rightY = 0;
		
		leftY *= scale; rightY *= scale;
		
		leftFront.set(leftY); leftRear.set(leftY);
		rightFront.set(-rightY); rightRear.set(-rightY);
	}
	
	public void driveLeftSide(double leftY) {
		if(Math.abs(leftY) < 0.04) leftY = 0;
		
		leftY *= scale;
		
		leftFront.set(leftY); leftRear.set(leftY);
	}
	
	public void driveRightSide(double rightY) {
		if(Math.abs(rightY) < 0.04) rightY = 0;
		
		rightY *= scale;
		
		rightFront.set(-rightY); rightRear.set(-rightY);
	}
	
	public void turnLeft(double power) {
		driveLeftSide(-power);
		driveRightSide(power);
	}
	
	public void turnRight(double power) {
		driveLeftSide(power);
		driveRightSide(-power);
	}
	
	public void seekRotation(Gyro gyro, double targetAngle, double power) {
		while(targetAngle < 0) targetAngle += 360;
		targetAngle %= 360;
		
		double currentAngle = gyro.getAngle();
		while(currentAngle < 0) currentAngle += 360;
		currentAngle %= 360;
		
		double clockwiseDistance = 0, counterClockwiseDistance = 0;
		if(targetAngle < currentAngle) {
			counterClockwiseDistance = currentAngle - targetAngle;
			clockwiseDistance = 360 - counterClockwiseDistance;
		} else if(targetAngle > currentAngle) {
			clockwiseDistance = targetAngle - currentAngle;
			counterClockwiseDistance = 360 - clockwiseDistance;
		}
		
		if(counterClockwiseDistance < clockwiseDistance) turnLeft(power);
		else if(clockwiseDistance < counterClockwiseDistance) turnRight(power);
		
		double tolerance = 0.5;
		while(targetAngle - tolerance < currentAngle) ;
	}
}

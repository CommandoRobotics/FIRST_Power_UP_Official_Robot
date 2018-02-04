package APIs;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class HybridWheels {
	Spark leftWheels, rightWheels;
	Solenoid leftSolenoid, rightSolenoid;
	
	public HybridWheels(int leftWheelsPort, int rightWheelsPort, int leftSolenoidPort, int rightSolenoidPort) {
		leftWheels = new Spark(leftWheelsPort);
		rightWheels = new Spark(rightWheelsPort);
		leftSolenoid = new Solenoid(leftSolenoidPort);
		rightSolenoid = new Solenoid(rightSolenoidPort);
	}
	
	public void pull(double power) {
		leftWheels.set(power);
		rightWheels.set(power);
	}
	
	public void push(double power) {
		leftWheels.set(-power);
		rightWheels.set(-power);
	}
	
	public void stop() {
		leftWheels.set(0);
		rightWheels.set(0);
	}
	
	public void open() {
		leftSolenoid.set(true);
		rightSolenoid.set(false);
	}
	
	public void close() {
		leftSolenoid.set(false);
		rightSolenoid.set(true);
	}
	
	public void toggleGrip() {
		leftSolenoid.set(!leftSolenoid.get());
		rightSolenoid.set(!rightSolenoid.get());
	}
}

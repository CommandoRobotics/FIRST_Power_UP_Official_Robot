package APIs;

import edu.wpi.first.wpilibj.Spark;

public class HybridWheels {
	Spark leftWheels, rightWheels;
		
	public HybridWheels(int leftWheelsPort, int rightWheelsPort) {
		leftWheels = new Spark(leftWheelsPort);
		rightWheels = new Spark(rightWheelsPort);
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
}

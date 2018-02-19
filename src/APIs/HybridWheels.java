package APIs;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class HybridWheels {
	private Solenoid leftSolenoid, rightSolenoid;
	
	public HybridWheels(int leftSolenoidPort, int rightSolenoidPort) {
		leftSolenoid = new Solenoid(leftSolenoidPort);
		rightSolenoid = new Solenoid(rightSolenoidPort);
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
		if(leftSolenoid.get() == rightSolenoid.get()) close();
		else {
			leftSolenoid.set(!leftSolenoid.get());
			rightSolenoid.set(!rightSolenoid.get());
		}
	}
}

package APIs;

import edu.wpi.first.wpilibj.Solenoid;

public class Crown {
	private Solenoid solenoidOneLeft, solenoidTwoLeft, solenoidThreeLeft,
		solenoidOneRight, solenoidTwoRight, solenoidThreeRight;
	
	public Crown(int solenoidOnePort, int solenoidTwoPort, int solenoidThreePort) {
		solenoidOneLeft = new Solenoid(solenoidOnePort);
		solenoidTwoLeft = new Solenoid(solenoidTwoPort);
		solenoidThreeLeft = new Solenoid(solenoidThreePort);
		solenoidOneLeft = new Solenoid(solenoidOnePort);
		solenoidTwoLeft = new Solenoid(solenoidTwoPort);
		solenoidThreeLeft = new Solenoid(solenoidThreePort);
	}
	
	
	public void lift() {
		
	}
}

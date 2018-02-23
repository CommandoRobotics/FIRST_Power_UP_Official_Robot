package APIs;

import edu.wpi.first.wpilibj.Solenoid;

public class Crown {
	private Solenoid solenoidOneLeft, solenoidTwoLeft, solenoidThreeLeft,
		solenoidOneRight, solenoidTwoRight, solenoidThreeRight;
	
	public Crown(int solenoidOneLeftPort, int solenoidOneRightPort, int solenoidTwoLeftPort, int solenoidTwoRightPort,
			int solenoidThreeLeftPort, int solenoidThreeRightPort) {
		solenoidOneLeft = new Solenoid(solenoidOneLeftPort);
		solenoidOneRight = new Solenoid(solenoidOneRightPort);
		
		solenoidTwoLeft = new Solenoid(solenoidTwoLeftPort);
		solenoidTwoRight = new Solenoid(solenoidTwoRightPort);
		
		solenoidThreeLeft = new Solenoid(solenoidThreeLeftPort);
		solenoidThreeRight = new Solenoid(solenoidThreeRightPort);
	}
	
	
	public void lift() {
		solenoidOneLeft.set(true);
		solenoidTwoLeft.set(true);
		solenoidThreeLeft.set(true);
		solenoidOneRight.set(false);
		solenoidTwoRight.set(false);
		solenoidThreeRight.set(false);
	}
	
	public void lower() {
		solenoidOneLeft.set(false);
		solenoidTwoLeft.set(false);
		solenoidThreeLeft.set(false);
		solenoidOneRight.set(true);
		solenoidTwoRight.set(true);
		solenoidThreeRight.set(true);
	}
	
	public void togglePosition() {
		if(solenoidOneLeft.get() == solenoidTwoLeft.get() == solenoidThreeLeft.get()) lift();
		else {
			solenoidOneLeft.set(!solenoidOneLeft.get());
			solenoidTwoLeft.set(!solenoidTwoLeft.get());
			solenoidThreeLeft.set(!solenoidThreeLeft.get());
			solenoidOneRight.set(!solenoidOneRight.get());
			solenoidTwoRight.set(!solenoidTwoRight.get());
			solenoidThreeRight.set(!solenoidThreeRight.get());
		}
	}
}

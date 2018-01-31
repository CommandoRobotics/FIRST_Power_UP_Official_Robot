package APIs;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class HybridWheels {
	
	Compressor compressor;
	
	Solenoid solenoidLeft;
	Solenoid solenoidRight;
	
	public HybridWheels(int solenodiLeftPort, int solenoidRightPort) {
		compressor = new Compressor(0);
		
		solenoidLeft = new Solenoid(solenoidLeftPort);
		solenoidRight = new Solenoid(solenoidRightPort);
	}
	
}

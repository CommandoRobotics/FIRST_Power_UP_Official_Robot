package APIs;

import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics {
	Compressor compressor;
	
	public Pneumatics(int solenoidLeftPort, int solenoidRightPort) {
		compressor = new Compressor(0);
	}
	
	public void compressAirIfNeeded() {
		if(compressor.getPressureSwitchValue()) stopCompressing();
		else startCompressing();
	}
	
	public void startCompressing() {
		compressor.start();
	}
	
	public void stopCompressing() {
		compressor.stop();
	}
}

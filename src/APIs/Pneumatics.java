package APIs;

import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics {
	private Compressor compressor;
	
	public Pneumatics() {
		compressor = new Compressor(0);
	}
	
	public void compressAirIfNeeded() {
		System.out.println("Pressure Low: " + compressor.getPressureSwitchValue());
		if(!compressor.getPressureSwitchValue()) startCompressing();
		else stopCompressing();
	}
	
	public void startCompressing() {
		compressor.start();
	}
	
	public void stopCompressing() {
		compressor.stop();
	}
}

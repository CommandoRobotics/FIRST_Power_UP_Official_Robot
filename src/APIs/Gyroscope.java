package APIs;

import Gyroscope.ADIS16448_IMU;

public class Gyroscope {
	private ADIS16448_IMU gyro;
	
	public Gyroscope() {
		gyro = new ADIS16448_IMU();
	}
	
	public void getRotation() {
		
	}
	
	public void reset() {
		gyro.reset();
	}
}

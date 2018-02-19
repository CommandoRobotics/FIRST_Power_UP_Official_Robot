package APIs;

import edu.wpi.first.wpilibj.Spark;

public class Wench {
	private Spark wench;
	
	public Wench(int wenchPort) {
		wench = new Spark(wenchPort);
	}
	
	public void climb() {
		wench.set(0.6);
	}
	
	public void fall() {
		wench.set(-0.6);
	}
	
	public void stop() {
		wench.set(0);
	}
}

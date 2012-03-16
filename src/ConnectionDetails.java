/**
 * 
 * Moises Florez and Peter Matano
 *
 */
public class ConnectionDetails{
	
	private int deviceID;
	private boolean connectionOpen;
	private boolean connectionClose;
	
	public int getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}
	public boolean isConnectionOpen() {
		return connectionOpen;
	}
	public void setConnectionOpen(boolean connectionOpen) {
		this.connectionOpen = connectionOpen;
	}
	public boolean isConnectionClose() {
		return connectionClose;
	}
	public void setConnectionClose(boolean connectionClose) {
		this.connectionClose = connectionClose;
	}
}

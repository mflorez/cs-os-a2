
public class ReadInterruptData {

	int connectionID;
	int readToAddress;
	int nValue;
	boolean interruptEnabled;
	
	public int getConnectionID() {
		return connectionID;
	}
	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}
	public int getReadToAddress() {
		return readToAddress;
	}
	public void setReadToAddress(int readToAddress) {
		this.readToAddress = readToAddress;
	}
	public int getnValue() {
		return nValue;
	}
	public void setnValue(int nValue) {
		this.nValue = nValue;
	}
	public boolean isInterruptEnabled() {
		return interruptEnabled;
	}
	public void setInterruptEnabled(boolean interruptEnabled) {
		this.interruptEnabled = interruptEnabled;
	}	
	
	public void ResetValue() {
		connectionID = 0;
		readToAddress = 0;
		nValue = 0;
		interruptEnabled = false;
	}
}

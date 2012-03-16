
public class WriteInterruptData {

	int connectionID;
	int writeFromAddress;
	int nValue;
	boolean interruptEnabled;
	
	public int getConnectionID() {
		return connectionID;
	}
	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}
	public int getWriteFromAddress() {
		return writeFromAddress;
	}
	public void setWriteFromAddress(int writeFromAddress) {
		this.writeFromAddress = writeFromAddress;
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
		writeFromAddress = 0;
		nValue = 0;
		interruptEnabled = false;
	}	
}

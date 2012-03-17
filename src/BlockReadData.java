
public class BlockReadData extends Block {

	private boolean readStarted;
	private boolean readComplete;
				
	public boolean isReadStarted() {
		return readStarted;
	}

	public void setReadStarted(boolean readStarted) {
		this.readStarted = readStarted;
	}

	public boolean isReadComplete() {
		return readComplete;
	}

	public void setReadComplete(boolean readComplete) {
		this.readComplete = readComplete;
	}	
}

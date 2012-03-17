
public class BlockWriteData  extends Block {

	private boolean writeStarted;
	private boolean writeComplete;

	public boolean isWriteStarted() {
		return writeStarted;
	}

	public void setWriteStarted(boolean writeStarted) {
		this.writeStarted = writeStarted;
	}

	public boolean isWriteComplete() {
		return writeComplete;
	}

	public void setWriteComplete(boolean writeComplete) {
		this.writeComplete = writeComplete;
	}
	
	
}

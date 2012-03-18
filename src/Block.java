
public class Block {

	private int blockNumber;
	private int blockAddress;
	private boolean programsStarted;
	private boolean programLoadingComplete;
		
	public int getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
	public int getBlockAddress() {
		return blockAddress;
	}
	public void setBlockAddress(int blockAddress) {
		this.blockAddress = blockAddress;
	}
	
	public boolean isProgramsStarted() {
		return programsStarted;
	}

	public void setProgramsStarted(boolean programsStarted) {
		this.programsStarted = programsStarted;
	}
	
	public boolean isProgramLoadingComplete() {
		return programLoadingComplete;
	}
	
	public void setProgramLoadingComplete(boolean programLoadingComplete) {
		this.programLoadingComplete = programLoadingComplete;
	}	
	
}

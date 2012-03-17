
public class BlockReadWriteDetails {

	private BlockReadData blockReadData;
	private BlockWriteData blockWriteData;
	
	public BlockReadWriteDetails(){
		blockReadData = new BlockReadData();
		blockWriteData = new BlockWriteData();
	}

	public BlockReadData getBlockReadData() {
		return blockReadData;
	}

	public void setBlockReadData(BlockReadData blockReadData) {
		this.blockReadData = blockReadData;
	}

	public BlockWriteData getBlockWriteData() {
		return blockWriteData;
	}

	public void setBlockWriteData(BlockWriteData blockWriteData) {
		this.blockWriteData = blockWriteData;
	}	
}

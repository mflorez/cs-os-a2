import java.util.List;

public class MemoryManager {

	private List<BlockReadWriteDetails> blockReadWriteDetailList;
	
	public MemoryManager(List<BlockReadWriteDetails> blockReadWriteDetailList){
		this.blockReadWriteDetailList = blockReadWriteDetailList;
	}

	public List<BlockReadWriteDetails> getBlockReadWriteDetailList() {
		return blockReadWriteDetailList;
	}

	public void setBlockReadWriteDetailList(
			List<BlockReadWriteDetails> blockReadWriteDetailList) {
		this.blockReadWriteDetailList = blockReadWriteDetailList;
	}
	
	/**
	 * Find the block based on the address and block information
	 */
	public BlockReadWriteDetails findWriteBlockDetails(int blockNumber, int writeFromAddress) {
		BlockReadWriteDetails rwDt = null;
		for (BlockReadWriteDetails rwDetails : blockReadWriteDetailList){
			int blockWriteAddressData = rwDetails.getBlockWriteData().getBlockAddress();
			int blockNumberData = rwDetails.getBlockWriteData().getBlockNumber();			
			if (blockWriteAddressData == writeFromAddress && blockNumberData == blockNumber){
				rwDt = rwDetails;
				return rwDt;
			}			
		}
		return rwDt;
	}
}

import java.util.List;

public class MemoryManager {

	private List<BlockReadWriteDetails> blockReadWriteDetailList;
	
	
	int nextBlock;	
		
	public int getNextBlock() {
		return nextBlock++;
	}
	
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;		
	}

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
	 * Find the block based on the address and block information.
	 * @param blockWriteData
	 * @return
	 */
	public BlockReadWriteDetails findReadWriteBlockDetails(BlockWriteData blockWriteData) {
		int blockNumber = blockWriteData.getBlockNumber();
		int writeFromAddress = blockWriteData.getBlockAddress();
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
	
	/**
	 * Find the block based on the address and block information.
	 * @param blockReadData
	 * @return
	 */
	public BlockReadWriteDetails findReadWriteBlockDetails(BlockReadData blockReadData) {
		int blockNumber = blockReadData.getBlockNumber();
		int writeFromAddress = blockReadData.getBlockAddress();
		BlockReadWriteDetails rwDt = null;
		for (BlockReadWriteDetails rwDetails : blockReadWriteDetailList){
			int blockWriteAddressData = rwDetails.getBlockReadData().getBlockAddress();
			int blockNumberData = rwDetails.getBlockReadData().getBlockNumber();
			System.out.println("blockWriteAddressData: " + blockWriteAddressData);
			System.out.println("blockNumberData: " + blockNumberData);
			
			if (blockWriteAddressData == writeFromAddress && blockNumberData == blockNumber){
				rwDt = rwDetails;
				return rwDt;
			}			
		}
		System.out.println("blockNumber: " + blockNumber);
		System.out.println("writeFromAddress: " + writeFromAddress);
		
		return rwDt;
	}
	
	public boolean isBlockInReadWriteBlockDetailsList(int blockNumber, int writeFromAddress){
		boolean blockFound = false;
		for (BlockReadWriteDetails rwDetails : blockReadWriteDetailList){
			int blockWriteAddressData = rwDetails.getBlockReadData().getBlockAddress();
			int blockNumberData = rwDetails.getBlockReadData().getBlockNumber();
			System.out.println("blockWriteAddressData: " + blockWriteAddressData);
			System.out.println("blockNumberData: " + blockNumberData);			
			if (blockWriteAddressData == writeFromAddress && blockNumberData == blockNumber){
				return true;
			}	
		}
		System.out.println("blockNumber: " + blockNumber);
		System.out.println("writeFromAddress: " + writeFromAddress);		
		return blockFound;
	}
}

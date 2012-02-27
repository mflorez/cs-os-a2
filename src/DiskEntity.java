import java.util.List;
import java.util.ArrayList;
/**
 * 
 * Moises Florez and Peter Matano
 *
 */
public class DiskEntity {
	private List<BlockEntity> diskBlockList;
	/**
	 * 	Get block list
	 * @return
	 */
	public List<BlockEntity> getBlockEntityList() {
		return diskBlockList;
	}

	/**
	 * Set block list
	 * @param blockEntityList
	 */
	public void setBlockEntityList(List<BlockEntity> blockEntityList) {
		this.diskBlockList = blockEntityList;
	}

	/*
	 * Disk entity
	 */
	public DiskEntity(){
		diskBlockList = new ArrayList<BlockEntity>();
	}
	
	/*
	 *Add the block 
	 */
	public void addBlock(BlockEntity block) {
		this.diskBlockList.add(block);
	}
	
	/*
	 * Get block entity
	 */
	public BlockEntity getBlockEntity(int index) {
		return this.diskBlockList.get(index);
	}
}

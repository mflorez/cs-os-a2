import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Moises Florez and Peter Matano
 *
 */
public class OS implements OperatingSystem {
	private Hardware simHW;
	private DiskEntity dEnt;
	private ProgramEntity proEnt;
	private int blockCounter = 1;
	private int diskBlockStartAddress = 0;
	private boolean startPrograms;
		
	public int getProcessCount() {
		return proEnt.getBlockEntityList().size();
	}

	private int countdown = 3000;	
	public OS(Hardware hw) {
		simHW = hw; // Set simulator hardware.
		proEnt = new ProgramEntity();
		dEnt = new DiskEntity();		
	}

	/**
	 * OS Interrupt handler 
	 */
	@Override
	public void interrupt(Hardware.Interrupt it) {
		switch (it) {
		case illegalInstruction:
			printLine("Interrupt: illegalInstruction");
			simHW.store(Hardware.Address.haltRegister, 2);
			break;	
		case reboot:
			printLine("Interrupt: reboot");
			// Load the disk to primary store one block at the time.			
			simHW.store(Hardware.Address.diskBlockRegister, 0);
			simHW.store(Hardware.Address.diskAddressRegister, Hardware.Address.userBase);
			simHW.store(Hardware.Address.diskCommandRegister, Hardware.Disk.readCommand);			
			simHW.store(Hardware.Address.PCRegister, Hardware.Address.idleStart);//Set PCRegister to prevent illegal instruction interrupt
			
			diskBlockStartAddress = simHW.fetch(Hardware.Address.diskAddressRegister); // Disk start address information.
					
			break;
		case systemCall:
			printLine("Interrupt: systemCall");
			operatingSystemCall(simHW.fetch(Hardware.Address.systemBase));
			break;		
		case invalidAddress:
			printLine("Interrupt: invalidAddress");
			simHW.store(Hardware.Address.haltRegister, 2);
			break;			
		case disk:
			printLine("Interrupt: disk");			
			// int programBlocks = simHW.fetch(Hardware.Address.userBase);//Find how many blocks first program occupies 
			// int nextBlockStartaddress = simHW.fetch(Hardware.Address.diskAddressRegister) + 32; //Find where to load next block
			int exeProgramsBlockCount = getExeProgsBlockCount();  // total programs block count.
			
			if(exeProgramsBlockCount == 0) //If disk is empty then halt OS
			{
				simHW.store(Hardware.Address.haltRegister, 2);
			}			
			
			if (blockCounter < exeProgramsBlockCount) // Loads executable programs blocks into User Space in addition to index block.
			{				
				diskBlockStartAddress += Hardware.Disk.blockSize; // Move to the next block address by adding 32 words per block.
				printLine("Next block start address: " + diskBlockStartAddress);
				
				simHW.store(Hardware.Address.diskBlockRegister, blockCounter++);//Next block from disk   			
				simHW.store(Hardware.Address.diskAddressRegister, diskBlockStartAddress);//Set next block start address			
				simHW.store(Hardware.Address.diskCommandRegister, Hardware.Disk.readCommand);//Read from disk to primary storage					
				if (blockCounter == exeProgramsBlockCount) {
					startPrograms = true;
					printLine("Info: startPrograms = true");
				}				
				simHW.store(Hardware.Address.PCRegister, Hardware.Address.idleStart);//Set PCRegister to prevent illegal instruction interrupt
			}		
			
			if (startPrograms){ // If all disks are loaded, execute the first program.		
				this.createDiskEntity(); // Create the disk entity blocks.
				
				List<WordEntity> iEntity = dEnt.getBlockEntity(0).getWordEntityList();
				this.queueProcessExecution(iEntity);	
				simHW.store(Hardware.Address.countdownRegister, countdown); // Set a timer to start program execution.
				
				startPrograms = false;				
			} 			
			break;
		case terminal:
			printLine("Interrupt: terminal");
			break;
		case countdown:
			int cDownReg = simHW.fetch(Hardware.Address.countdownRegister);
			if (cDownReg == 0) { // Count down finished reset to stop it from hanging.
				this.simHW.store(Hardware.Address.countdownRegister, countdown); // set a new count down to keep system from hanging.
				simHW.store(Hardware.Address.PCRegister, Hardware.Address.idleStart);//Set PCRegister to prevent illegal instruction interrupt
			}
			int proIndex = 0; // Call the first program.
			preemptiveRoundRobinProcessing(proIndex); // Implements Round Robin.  It starts processing preemptively based on the next on the list and the count down timer.
			break;
		}
	}

	/**
	 * Get the total program block count.
	 * @return
	 */
	private int getExeProgsBlockCount(){
		int blkCount = 0;		
		for ( int i = 0; i < Hardware.Disk.blockSize; i++ ){
			int programBlocks = simHW.fetch(Hardware.Address.userBase + i);//Find how many blocks executable programs occupy.
			if (programBlocks != 0){
				// printLine("Index block value: " + programBlocks + " @ index: " + i);
				blkCount += programBlocks;
			}						
		}
		printLine("Program block count: " + blkCount);
		return blkCount;
	}
	
	/**
	 * Operating system calls.
	 * @param sysCall
	 */
	public void operatingSystemCall(int sysCall) {
		int indexAddress;
		int indexBlock;		
		switch (sysCall) {
		case SystemCall.exec:
			printLine("SystemCall: exec");
			
			indexAddress =  this.simHW.fetch(Hardware.Address.systemBase + 1); // Get register 1.
			indexBlock = this.simHW.fetch(indexAddress);
			this.preemptiveRoundRobinProcessing(indexBlock);				
			break;
		case SystemCall.exit:
			printLine("SystemCall: exit");
			
			simHW.store(Hardware.Address.haltRegister, 2);
			break;		
		case SystemCall.getSlot:
			printLine("SystemCall: getSlot");
			
			indexAddress =  this.simHW.fetch(1); // Get register 1.
			indexBlock = this.simHW.fetch(indexAddress);
			
			this.simHW.store(1, indexAddress);
			this.simHW.store(2, indexBlock);
			this.simHW.store(0, Hardware.Status.ok);			
			break;		
		case SystemCall.putSlot:
			printLine("SystemCall: putSlot");
			
			this.simHW.store(0, Hardware.Status.ok);	
			break;		
		case SystemCall.yield:
			printLine("SystemCall: yield");
			
			this.simHW.store(0, Hardware.Status.ok);
		case SystemCall.open:
			printLine("SystemCall: open");
			break;
		case SystemCall.close:
			printLine("SystemCall: close");
			
			break;
		case SystemCall.read:
			printLine("SystemCall: read");
			
			break;
		case SystemCall.write:
			printLine("SystemCall: write");
			
			break;
		}
	}
	
		
	/**
	 * Creates the disk blocks to reference for referencing program block information
	 */
	private void createDiskEntity(){		
		List<WordEntity> wordEnt = new ArrayList<WordEntity>();
		
		for (int i = Hardware.Address.userBase; i < Hardware.Address.userTop - 1; i++) // Create a list of word entities.
		{
			WordEntity wEnt = new WordEntity();				
			wEnt.setWordAddress(i); // Add the word address.
			wordEnt.add(wEnt);			
		}			
		
		List<WordEntity> wordList = Collections.unmodifiableList(wordEnt);
		List<List<WordEntity>> subParts = createBlockList(wordList, 32);
		
		for (int i = 0; i < subParts.size(); i++)
		{
			BlockEntity blkEnt = new BlockEntity();
			blkEnt.setWordEntityList(subParts.get(i));
			dEnt.getBlockEntityList().add(blkEnt);
		}				
	}
	
	/**
	 * Create block list.
	 * @param list
	 * @param L
	 * @return
	 */
	static <T> List<List<T>> createBlockList(List<T> list, final int L) {
		List<List<T>> parts = new ArrayList<List<T>>();
		final int N = list.size();
		for (int i = 0; i < N; i += L) {
			parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
		}
		return parts;
	}

	/**
	 * Shows how to get to the first and last block of each process based on the index block(0).
	 * @param nBlock
	 */
	private void queueProcessExecution(List<WordEntity> iBlock){
		
		int programIndexBlock = 0;
		int currentProcessFirstBlock = 1; // Go past program index block.		
		for (int i = 0; i < iBlock.size(); i++){
			int addr = iBlock.get(i).getWordAddress();
			int processBlockCount = this.simHW.fetch(addr); // Get the block count for the first program.
			if (processBlockCount != 0) {
				int currentProcessLastBlock = processBlockCount + programIndexBlock; // The last block for current program.  One past the index block.
				int processStartBlockAddress = dEnt.getBlockEntityList().get(currentProcessFirstBlock).getWordEntityList().get(0).getWordAddress(); // First block for the process.
				int processEndBlockAddress = dEnt.getBlockEntityList().get(currentProcessLastBlock).getWordEntityList().get(31).getWordAddress(); // Last block for the process.
							
				int pCRegister = processStartBlockAddress;
				int baseRegister = processStartBlockAddress ;
				int topRegister = processEndBlockAddress + 1;
				
				// Set the program block list to allow to iterate using a preemptive round robin scheduling scheme base on a count down.
				this.setProgramBlockList(processStartBlockAddress, processBlockCount, pCRegister, baseRegister, topRegister);
				currentProcessFirstBlock += processBlockCount; // Update the program start to the next block after the current process last block.
			}			
		}				
	}
	
	/**
	 * Creates a list containing all of the blocks within one program.  This allows preemptive round robin execution.
	 * @param programStartBlock
	 * @param processBlockCount
	 * @param pCRegister
	 * @param baseRegister
	 * @param topRegister
	 */
	private void setProgramBlockList(int programStartBlock, int processBlockCount, int pCRegister, int baseRegister, int topRegister){
		BlockEntity bEnt = new BlockEntity();
		for (int proBlockIndex = programStartBlock; proBlockIndex <= processBlockCount; proBlockIndex++){ // Go through the addresses in block range.
			List<WordEntity> iBlock = dEnt.getBlockEntityList().get(proBlockIndex).getWordEntityList(); // Get the block range based on start and end blocks.
			for (int j = 0; j < iBlock.size(); j ++) {
				bEnt.getWordEntityList().add(iBlock.get(j));				
			}			
		}
		bEnt.setBaseRegister(baseRegister); // Set the register per block.
		bEnt.setTopRegister(topRegister); // 
		bEnt.setPCRegister(pCRegister);
		proEnt.getBlockEntityList().add(bEnt);
	}
	
	/**
	 * Process a program based on the range found in a program.
	 * @param index
	 */
	private void preemptiveRoundRobinProcessing(int index) {
		boolean isValidIndex = indexExists(proEnt.getBlockEntityList(), index);
		if (isValidIndex){
			int pCRegister = proEnt.getBlockEntityList().get(index).getPCRegister();
			int baseRegister = proEnt.getBlockEntityList().get(index).getBaseRegister();
			int topRegister = proEnt.getBlockEntityList().get(index).getTopRegister();
			this.simHW.store(Hardware.Address.PCRegister, pCRegister); // Address of the next program to execute.
			this.simHW.store(Hardware.Address.baseRegister, baseRegister); // Lowest legal address accessible to the current running process.
			this.simHW.store(Hardware.Address.topRegister, topRegister); // One more than the highest legal address.

		} else {
			simHW.store(Hardware.Address.haltRegister, 2);
		}			
	}
	
	/**
	 * Checks for valid program index
	 * @param ls
	 * @param index
	 * @return
	 */
	private boolean indexExists(final List<BlockEntity> ls, final int index) {
		boolean isValid = (index >= 0 && index <= ls.size());
		return isValid;
	}
	
	private void printLine(String msg){
		System.out.println(msg);
	}
}
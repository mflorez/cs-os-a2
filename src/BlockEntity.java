import java.util.List;
import java.util.ArrayList;

/**
 * Describes the blocks as identities. 
 * @author Moises Florez and Peter Matano
 *
 */
public class BlockEntity {
	private int BaseRegister, TopRegister, PCRegister;	
	private List<WordEntity> wordEntityList;
	
	private int i;
	
	/**
	 * Block entity
	 */
	public BlockEntity(int i){
		this.i = i;
		wordEntityList = new ArrayList<WordEntity>();		
	}	
	
	/**
	 * Call method
	 * @return
	 */
	public int call() {
		return i;
	}
	
	/**
	 * Gets the word entity list
	 * @return
	 */
	public List<WordEntity> getWordEntityList() {
		return wordEntityList;
	}

	/**
	 * Set word entity list
	 * @param wordEntityList
	 */
	public void setWordEntityList(List<WordEntity> wordEntityList) {
		this.wordEntityList = wordEntityList;
	}		
	
	/**
	 * Gets the base register.
	 * @return
	 */
	public int getBaseRegister() {
		return BaseRegister;
	}

	/**
	 * Set base register
	 * @param baseRegister
	 */
	public void setBaseRegister(int baseRegister) {
		BaseRegister = baseRegister;
	}

	/**
	 * Get top register
	 * @return
	 */
	public int getTopRegister() {
		return TopRegister;
	}

	/**
	 * Set top register
	 * @param topRegister
	 */
	public void setTopRegister(int topRegister) {
		TopRegister = topRegister;
	}

	/*
	 * Get the pc register
	 */
	public int getPCRegister() {
		return PCRegister;
	}

	/*
	 * Set the pcRetister
	 */
	public void setPCRegister(int pCRegister) {
		PCRegister = pCRegister;
	}	
}

import java.util.Iterator;
import java.util.List;

/**
 * 
 * Moises Florez and Peter Matano
 *
 */
public class ProgramEntity extends DiskEntity {	
	private Iterator<BlockEntity> it;
	private List<BlockEntity> list;
		
	/**
	 * Program entity constructor
	 */
	public ProgramEntity(){		
		this.list = this.getBlockEntityList();
		this.it = list.iterator();
	}	
}

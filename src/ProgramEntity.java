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
	int i;
	
	/**
	 * Program entity constructor
	 */
	public ProgramEntity(){
		this.i = 0;
		this.list = this.getBlockEntityList();
		this.it = list.iterator();
	}
	
	/**
	 * Index call
	 * @return
	 */
	public int call(){
		return i;
	}
	
	/**
	 * Calls next
	 * @return
	 */
	public int next() { // Round Robin, if we get to the end start over.
		if (!it.hasNext()) {
			this.it = list.iterator();
		}
		BlockEntity ent = it.next();
		
		return ent.call();
		
//		// if we get to the end, start again
//				if (!it.hasNext()) {
//					it = list.iterator();
//				}
//				Robin robin = it.next();
//
//				return robin.call();
	}
}

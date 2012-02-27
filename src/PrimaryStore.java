// Operating Systems, Spring 2012.

/**
 */


interface 
PrimaryStore {

  /**
   */

  int fetch(int addr);


  /**
   */

  void store(int addr, int word);
  }


// $Log: PrimaryStore.java,v $
// Revision 1.1  2012/02/12 20:37:03  rclayton
// Initial revision
//
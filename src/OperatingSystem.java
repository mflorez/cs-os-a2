// Operating Systems, Spring 2012

/**
   The OperatingSystem interface defines the characteristics of an operating
   system that are accessible to the hardware.

   The interface below describes the operating system in terms of a single
   method, the interrupt-handler method.  However, the hardware also expects
   any class implementing the OperatingSystem interface to also have a public
   constructor accepting a single parameter of type Hardware:

     <blockquote><pre>
       class OS
       implements OperatingSystem {

         // blah blah blah

         public OS(Hardware hw) {
	   // blah blah blah
           }

	 // blah blah blah

         void interrupt(Hardware.Interrupt it) {
	   // blah blah blah
	   }

	 // blah blah blah

         }
     </pre></blockquote>

   It is important that the constructor be public; the simulator can't
   recognize non-public constructors.

   The instance passed in as a parameter to the constructor is the instance of
   the hardware on which the operating system is running.
 */


interface OperatingSystem {

  /**
     The system calls defined by the operating system.
   */

  final static class 
  SystemCall {


    /**
       A process indicate which system call it is making by storing one of
       these values in a register prior to raising the system-call interrupt.
     */

    static final int
      close   =  1,
      exec    =  2,
      exit    =  3,
      getSlot =  4,
      open    =  5,
      putSlot =  6,
      read    =  7,
      write   = 10,
      yield   = 11;


    /**
       Convert a system-call value to a string.

       @param s The status value of interest.

       @returns A string representation of the given status value.
     */

    static String
    toString(int s) {
      switch (s) {
	case close   : return "close";
	case exec    : return "exec";
	case exit    : return "exit";
	case getSlot : return "getSlot";
	case open    : return "open";
	case putSlot : return "putSlot";
	case read    : return "read";
	case write   : return "write";
	case yield   : return "yield";
	default      : SystemSim.panic("Unknown system-call value:  " + s);
        }
      return "";
      }
    }


  /**
     Notify the operating system an interrupt has been raised.

     @param it The raised interrupt's type.
   */

  void interrupt(Hardware.Interrupt it);
  }


// $Log: OperatingSystem.java,v $
// Revision 1.3  2012/02/16 19:35:53  rclayton
// Defined OperatingSystem.SystemCall.toString().
//
// Revision 1.2  2012/02/13 00:45:26  rclayton
// Assign the correct values to system-calls.
//
// Revision 1.1  2012/02/12 20:37:33  rclayton
// Initial revision
//

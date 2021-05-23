
package singleton_patterns;

/**
 * Resolves issue when many thread try to get instance of Singleton class simultaneously.
 * This issue primarily occurs due memory model below Java 1.5
 *
 */
public class BillPughSingleton {

    private BillPughSingleton(){}
    
    private static class SingletonHelper{
    	
    	private static final BillPughSingleton INSTANCE;
    	static {
    		System.out.println("Static Thread Name : " + Thread.currentThread().getName());
    		try {
    			INSTANCE = new BillPughSingleton();	
    		}catch (Exception e) {
				throw new RuntimeException("Exception occured in creating singleton instance");
			}
    	}

//        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance(){
    	
    	System.out.println("ThreadName : " + Thread.currentThread().getName());
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
        return SingletonHelper.INSTANCE;
    }
}


package singleton_patterns;

/**
 * Lazily Initialized 
 * Best Suited for Multi-Threaded environment
 *
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton(){}
    
    public static synchronized ThreadSafeSingleton getInstance() {
        
    	System.out.println("Inside Thread : " + Thread.currentThread().getName());
    	
    	if(instance == null){
            instance = new ThreadSafeSingleton();
        }
    	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
        return instance;
    }
    

    /**
     * Double Checked Locking
     * @return ThreadSafeSingleton Instance
     */
    public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){

    	if(instance == null){

    		synchronized (ThreadSafeSingleton.class) {

    			if(instance == null){
    				instance = new ThreadSafeSingleton();
    			}
    		}
    	}

    	return instance;
    }
    
}


package singleton_patterns;

import java.util.Objects;


/**
 * Instance created at the time of class loading
 * Doesn't provide any option for Exception Handling.
 *
 */
public class EagerInitializedSingleton {
    
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
    
    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){
    	//TODO
    }

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
    
    public void display() {
    	System.out.println("EagerInitializedSingleton.display()");
    }
    
    public static void initialized() {
    	System.out.println("EagerInitializedSingleton.initialized()");
    	
    	if(Objects.nonNull(instance)) {
    		instance.display();
    	}else {
    		System.out.println("Instance Not Created");
    	}
    }
    
}

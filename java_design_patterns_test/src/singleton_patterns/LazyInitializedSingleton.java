
package singleton_patterns;

import java.util.Objects;

/**
 * Creates Instance only when user asks to create.
 * Fails to hold singleton nature in multi-threaded environment.
 */
public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;
    
    private LazyInitializedSingleton(){}
    
    public static LazyInitializedSingleton getInstance(){
        if(instance == null){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
    
    public void display() {
    	System.out.println("LazyInitializedSingleton.display()");
    }
    
    public static void initialized() {

    	System.out.println("LazyInitializedSingleton.initialized()");
    	
    	if(Objects.nonNull(instance)) {
    		instance.display();
    	}else {
    		System.out.println("Instance Not Created");
    	}
    }
}

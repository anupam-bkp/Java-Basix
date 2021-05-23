
package singleton_patterns;

import java.util.Objects;

/**
 * Same as eager initialization 
 * instance created in static block that provide option for exception handling
 *
 */
public class StaticBlockSingleton {

    private static final StaticBlockSingleton instance;
    
    private StaticBlockSingleton(){
    	//TODO
    }
    
    //static block initialization for exception handling
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static StaticBlockSingleton getInstance(){
        return instance;
    }
    
    public void display() {
    	System.out.println("StaticBlockSingleton.display()");
    }
    
    public static void initialized() {

    	System.out.println("StaticBlockSingleton.initialized()");
    	
    	if(Objects.nonNull(instance)) {
    		instance.display();
    	}else {
    		System.out.println("Instance Not Created");
    	}
    }
}

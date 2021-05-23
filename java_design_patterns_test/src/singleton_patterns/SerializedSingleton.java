
package singleton_patterns;

import java.io.Serializable;

/**
 * Supports Serialization i.e. storing state in the filesystem.
 *
 */
public class SerializedSingleton implements Serializable{

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton(){}
    
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    public static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }
    
    /**
     * @return same object while deserialization which was serialized
     */
    protected Object readResolve() {
    	return getInstance();
    }
}


package singleton_patterns;

import java.util.Scanner;

/**
 * To restrict using reflection to break singleton pattern
 * Enum value instantiated only once in Java Program.
 *
 */
public enum EnumSingleton {

    INSTANCE;
    
	private Scanner scanner = null;
	
	private EnumSingleton() {
		scanner = new Scanner(System.in);
	}
	
    public Scanner getScanner() {
    	return scanner;
    }
}

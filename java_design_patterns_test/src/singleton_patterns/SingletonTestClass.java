package singleton_patterns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTestClass {
	
	public static void main(String[] args) {

//		testEagerInitialization();
		
//		testStaticBlockIniitalization();
		
//		testLazyInitialization();
		
//		testLazySingletonWithMultiThread();
		
//		testThreadSafeSingletonWithMultiThread();
		
//		testBillPughSingleton();
		
//		breakSingletonWithReflection();
		
//		enumSingletonTest();
		
		serializedSingletonTest();
		
		System.out.println("End of Main");
		
	}

	
	private static void serializedSingletonTest() {
		
		SerializedSingleton s1 = SerializedSingleton.getInstance();
		
		System.out.println(s1.hashCode());
		
		try {
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("test.ser"));
			out.writeObject(s1);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Deserialize from file to object
		try {
			ObjectInput in = new ObjectInputStream(new FileInputStream("test.ser"));
			SerializedSingleton s2 = (SerializedSingleton)in.readObject();
			in.close();
			
			System.out.println(s2.hashCode());
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void enumSingletonTest() {
		
	}
	
	private static void breakSingletonWithReflection() {
		
		EagerInitializedSingleton s1 = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton s2 = null;
		
//		Constructor<?>[] constructors = EagerInitializedSingleton.class.getConstructors();  //Only Public Constructors
		Constructor<?>[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors(); //All Constructors
		
		for(Constructor<?> constructor : constructors) {
			constructor.setAccessible(true);
			try {
				s2 = (EagerInitializedSingleton)constructor.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}
	
	
	private static void testBillPughSingleton() {
	
		Thread t1 = new Thread(() -> {
			BillPughSingleton s1 = BillPughSingleton.getInstance();
			System.out.println(s1.hashCode());
		}, "Thread1");
		
		Thread t2 = new Thread(() -> {
			BillPughSingleton s2 = BillPughSingleton.getInstance();
			System.out.println(s2.hashCode());
		}, "Thread2");
		
		t1.start();
		t2.start();
	}
	
	private static void testThreadSafeSingletonWithMultiThread() {
		
		Thread t1 = new Thread(() -> {
//			ThreadSafeSingleton ts1 = ThreadSafeSingleton.getInstance();
			ThreadSafeSingleton ts1 = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
			System.out.println(ts1.hashCode());
		}, "Thread1");
		
		Thread t2 = new Thread(() -> {
//			ThreadSafeSingleton ts2 = ThreadSafeSingleton.getInstance();
			ThreadSafeSingleton ts2 = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
			System.out.println(ts2.hashCode());
		}, "Thread2");
		
		t1.start();
		t2.start();
	}

	
	private static void testEagerInitialization() {
		EagerInitializedSingleton.initialized();
	}
	
	private static void testLazySingletonWithMultiThread() {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				LazyInitializedSingleton lazy1 = LazyInitializedSingleton.getInstance();
				System.out.println("Lazy1 : " + lazy1.hashCode());
			}
			
		}, "Thread1");

		Thread t2 = new Thread(() -> {
			LazyInitializedSingleton lazy2 = LazyInitializedSingleton.getInstance();
			System.out.println("Lazy2 : " + lazy2.hashCode());
		}, "Thread2");
		
		t1.start();
		t2.start();
		
	}
	
	private static void testStaticBlockIniitalization() {
		StaticBlockSingleton.initialized();
	}
	
	private static void testLazyInitialization() {
		LazyInitializedSingleton lazy1 = LazyInitializedSingleton.getInstance();
		LazyInitializedSingleton lazy2 = LazyInitializedSingleton.getInstance();
		
		System.out.println(lazy1 == lazy2);
		System.out.println(" :: " + lazy1.hashCode() + " :: " + lazy2.hashCode());
	}
}

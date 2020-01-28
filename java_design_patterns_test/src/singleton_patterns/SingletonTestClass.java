package singleton_patterns;

public class SingletonTestClass {
	
	public static void main(String[] args) {
//		EagerInitializedSingleton.initialized();
//		StaticBlockSingleton.initialized();
		
	/*	LazyInitializedSingleton lazy1 = LazyInitializedSingleton.getInstance();
		LazyInitializedSingleton lazy2 = LazyInitializedSingleton.getInstance();
		
		System.out.println(lazy1 == lazy2);
		System.out.println(" :: " + lazy1.hashCode() + " :: " + lazy2.hashCode());
		*/
		testSingletonWithMultiThread();
				
		System.out.println("End of Main");
		
	}
	
	private static void testSingletonWithMultiThread() {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				LazyInitializedSingleton lazy1 = LazyInitializedSingleton.getInstance();
				System.out.println("Lazy1 : " + lazy1.hashCode());
			}
			
		});

		Thread t2 = new Thread(() -> {
			LazyInitializedSingleton lazy2 = LazyInitializedSingleton.getInstance();
			System.out.println("Lazy2 : " + lazy2.hashCode());
		});
		
		t1.start();
		t2.start();
		
	}

}

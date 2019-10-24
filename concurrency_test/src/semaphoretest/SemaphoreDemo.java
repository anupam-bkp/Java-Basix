package semaphoretest;

import java.util.concurrent.Semaphore;

/**
 * @author Chandan Singh
 *This class demonstrates the use of java.util.concurrent.Semaphore Class 
 */
public class SemaphoreDemo {
	Semaphore semaphore = new Semaphore(10);

	public void printLock() {
		try {
			semaphore.acquire();
			System.out.println("Locks acquired");
			System.out.println("Locks remaining >> " +semaphore.availablePermits());
			System.out.println(Thread.currentThread());
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		finally {
			semaphore.release();
			System.out.println("Locks Released");
		}	    
	}

	public static void main(String[] args) {
		final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

		Thread thread = new Thread(){
			@Override
			public void run() {
				semaphoreDemo.printLock();
			}};
			
			thread.start();
	}
}

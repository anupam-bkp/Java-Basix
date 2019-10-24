package reentrantreadwritelock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {

	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	private static String message = "a";
	
	public static void main(String[] args) throws InterruptedException{
		Thread t1 = new Thread(new Read(), "READ");
		Thread t2 = new Thread(new WriteA(), "WRITE A");
		Thread t3 = new Thread(new WriteB(), "WRITE B");
		
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}
	
	static class Read implements Runnable {

		public void run() {
			for(int i = 0; i<= 10; i ++) {
				if(lock.isWriteLocked()) {
					System.out.println("I'll take the lock from Write");
				}
				lock.readLock().lock();
				System.out.println("ReadThread " + Thread.currentThread().getName() + " ---> Message is " + message );
				lock.readLock().unlock();
				}
			}
		}
	
	static class WriteA implements Runnable {

		public void run() {
			for(int i = 0; i<= 10; i ++) {
				try {
					lock.writeLock().lock();
					System.out.println(Thread.currentThread().getName());
					message = message.concat("a");
				} finally {
					lock.writeLock().unlock();
				}
			}
			}
		}
	
	static class WriteB implements Runnable {

		public void run() {
			for(int i = 0; i<= 10; i ++) {
				try {
					lock.writeLock().lock();
					System.out.println(Thread.currentThread().getName());
					message = message.concat("b");
				} finally {
					lock.writeLock().unlock();
				}
			}
			}
		}
}
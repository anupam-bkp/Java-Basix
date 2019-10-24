package atomiclongarray;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author ashraf
 *
 */
public class DecrementUpdateTask implements Runnable {
	
	private AtomicLongArray atomicLongArray;

	public DecrementUpdateTask(AtomicLongArray atomicLongArray) {
		super();
		this.atomicLongArray = atomicLongArray;
	}

	public void run() {

		try {
			for (int i = 0; i < atomicLongArray.length(); i++) {
				System.out.println("Decrement element" + i +" by 1");
				atomicLongArray.getAndDecrement(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();

		} finally {
			System.out.println("Decrement task was done !!!");
		}
	}

}

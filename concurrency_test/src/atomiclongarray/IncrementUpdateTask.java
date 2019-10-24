package atomiclongarray;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author ashraf
 *
 */
public class IncrementUpdateTask implements Runnable {
	
	private AtomicLongArray atomicLongArray;

	public IncrementUpdateTask(AtomicLongArray atomicLongArray) {
		super();
		this.atomicLongArray = atomicLongArray;
	}

	public void run() {

		try {
			for (int i = 0; i < atomicLongArray.length(); i++) {
				System.out.println("Increment element "+ i +" by 1");
				atomicLongArray.getAndIncrement(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();

		} finally {
			System.out.println("Increment task was done !!!");
		}
	}

}


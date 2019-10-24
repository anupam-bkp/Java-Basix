package countdownlatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author anirudh
 *
 */
public class JavaCountDownLatchExample {

	public static void main(String[] args) {
		// initialize count down latch by 2, as it will wait for 2 threads to
		// finish execution
		final CountDownLatch latch = new CountDownLatch(2);

		// making two threads for 2 services
		Thread serviceOneThread = new Thread(new ServiceOne(latch));
		Thread serviceTwoThread = new Thread(new ServiceTwo(latch));

		serviceOneThread.start();
		serviceTwoThread.start();

		// latch waits till the count becomes 0
		// this way we can make sure that the execution of main thread only
		// finishes ones 2 services have executed
		
		System.out.println("Starting main Thread!!!");
		
		try {
			latch.await();
			System.out.println("Both Thread down the latch !!!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package countdownlatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author anirudh
 *
 */
public class ServiceTwo implements Runnable{
	
	private final CountDownLatch latch;

	public ServiceTwo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("started service Two");
		
		for(int i=0; i<10; i++){
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		latch.countDown();	
	}

}

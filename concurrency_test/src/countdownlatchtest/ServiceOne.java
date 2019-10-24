package countdownlatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author anirudh
 *
 */
public class ServiceOne implements Runnable{
	
	private final CountDownLatch latch;
	
	public ServiceOne(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
//		System.out.println("Started service One");
		//reduce count of Count Down Latch by 1.
		
		System.out.println("started service One");
		
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

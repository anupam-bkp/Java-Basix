package synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author anirudh
 *
 */
public class QueueProducer implements Runnable{

	private SynchronousQueue<String> queue;
	
	public QueueProducer(SynchronousQueue<String> queue) {
		this.queue=queue;
	}
	@Override
	public void run() {

		String event = "SYNCHRONOUS_EVENT";
		String another_event ="ANOTHER_EVENT";
		try {
			queue.put(event);
			System.out.printf("[%s] published event : %s %n", Thread
					.currentThread().getName(), event);
			
			queue.put(another_event);
			System.out.printf("[%s] published event : %s %n", Thread
					.currentThread().getName(), another_event);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
	}

}

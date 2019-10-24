package synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author anirudh
 *
 */
public class QueueConsumer implements Runnable {

	private SynchronousQueue<String> queue;

	public QueueConsumer(SynchronousQueue<String> queue) {
		this.queue=queue;
	}

	@Override
	public void run() {
		try {
			String event = queue.take();
			// thread will block here
			System.out.printf("[%s] consumed event : %s %n", Thread
					.currentThread().getName(), event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
}

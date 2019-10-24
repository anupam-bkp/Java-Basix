package synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author anirudh
 *
 */
public class JavaSynchronousQueueExample {

	public static void main(String args[]) {
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		
		// starting publisher thread
		new Thread(new QueueProducer(queue)).start();
		
		// starting consumer thread
		new Thread(new QueueConsumer(queue)).start();
	
	}

}

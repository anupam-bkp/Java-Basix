package blockingqueuetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

	public static void main(String[] args) throws Exception {

		BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(1000);

		Producer producer = new Producer(bq);
		Consumer consumer = new Consumer(bq);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}

}

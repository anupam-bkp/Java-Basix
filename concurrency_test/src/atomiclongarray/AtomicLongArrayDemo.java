package atomiclongarray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author ashraf
 *
 */
public class AtomicLongArrayDemo {

	private static final int ARRAY_SIZE = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a new long array of 10 element
		long[] longArray = new long[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			longArray[i] = i + 1;
		}

		// Create a new AtomicLongArray with the predefined long array
		AtomicLongArray atomicLongArray = new AtomicLongArray(longArray);

		System.out.println("atomicLongArray before running tasks:\n"
				+ atomicLongArray);

		System.out.println("Start running increment/decrement tasks ...");
		// Create a new ExecutorService with 2 thread to Increment and Decrement
		// AtomicLongArray
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Start AtomicLongArray increment task
		Future<?> futureIncrementTask = executor
				.submit(new IncrementUpdateTask(atomicLongArray));

		// Start AtomicLongArray Decrement task
		Future<?> futureDecrementTask = executor
				.submit(new DecrementUpdateTask(atomicLongArray));

		while (true) {

			if (futureIncrementTask.isDone() && futureDecrementTask.isDone()) {
				System.out
				.println("Finish running increment/decrement tasks !!!");
				System.out.println("atomicLongArray after running tasks:\n"
						+ atomicLongArray);
				executor.shutdown();
				break;
			}
		}

	}

}

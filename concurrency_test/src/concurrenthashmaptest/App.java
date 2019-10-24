//https://examples.javacodegeeks.com/core-java/util/concurrent/concurrenthashmap/java-util-concurrent-concurrenthashmap-example/

package concurrenthashmaptest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	// I tested it with up to 10,000 Trucks (threads) without any problems
	private static final int NUM_OF_TRUCKS = 1_000;
	private static final Truck[] truckList = new Truck[App.NUM_OF_TRUCKS];
	
	private static int random(int m, int n) {
		return (int) (Math.random() * (n - m + 1)) + m;
	}
	
	public static void main(String[] args) {
		// Create NUM_OF_TRUCKS Trucks with randomly generated number plates
		for (int i = 0;i < App.NUM_OF_TRUCKS;i++) {
			App.truckList[i] = new Truck(App.random(1000, 5000));
		}
		
		// Create Threads equals NUM_OF_TRUCKS 
		ExecutorService executorService = Executors.newFixedThreadPool(App.NUM_OF_TRUCKS);
		// Create NUM_OF_TRUCKS Callables with random operations (ARRIVAL or DEPARTURE)
		DistributionCenterTruckQueue[] distributionCenterTruckQueue = new DistributionCenterTruckQueue[App.NUM_OF_TRUCKS];
		for (int i = 0;i < App.NUM_OF_TRUCKS;i++) {
			distributionCenterTruckQueue[i] = new DistributionCenterTruckQueue(App.truckList[i], DistributionCenterTruckQueue.OPERATION.values()[App.random(0, 1)]);
		}
		// Execute the Callables and get the result of each operation
		for (int i = 0;i < App.NUM_OF_TRUCKS;i++) {
			try {
				App.truckList[i] = executorService.submit(distributionCenterTruckQueue[i]).get();
				System.out.println(App.truckList[i]);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace(System.err);
			}
		}
		
		// Those trucks that have not been removed (DEPARTURE), are still "in progress" (INPROGRESS)
		for (int i = 0;i < App.NUM_OF_TRUCKS;i++) {
			try {
				distributionCenterTruckQueue[i] = new DistributionCenterTruckQueue(App.truckList[i], DistributionCenterTruckQueue.OPERATION.INPROGRESS);
				Truck truck = executorService.submit(distributionCenterTruckQueue[i]).get();
				System.out.println(truck.isInprogress() ? truck + ": True" : truck + ": False");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace(System.err);
			}
		}
		
		// Don't forget to shutdown the ExecutionService
		executorService.shutdown();
	}
}

package futuretasktest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author ashraf
 *
 */
public class FutureTaskDemo {
	
	// Maximum number to check
    public static final long MAX_NUMBER = 3000000000l;
    
    // DIVISOR to be used in calculation
    private static final long DIVISOR = 3;

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		
		// Sequential execution
		System.out.println("Starting sequential execution ....");
        long timeStart = System.currentTimeMillis();
        long result = Calculater.calculateNumberOfDivisible(0, MAX_NUMBER, DIVISOR);
        long timeEnd = System.currentTimeMillis();
        long timeNeeded = timeEnd - timeStart;
        System.out.println("Result         : " + result + " calculated in " + timeNeeded + " ms");
        
        
        // Parallel execution
        System.out.println("Starting parallel execution ....");
        long timeStartFuture = System.currentTimeMillis();
        
        long resultFuture = 0;
        
        // Create a new ExecutorService with 2 thread to execute and store the Futures
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<FutureTask<Long>> taskList = new ArrayList<FutureTask<Long>>();
 
        // Start thread for the first half of the numbers
        FutureTask<Long> futureTask_1 = new FutureTask<Long>(new CallableCalculater(0, MAX_NUMBER / 2, DIVISOR));
        taskList.add(futureTask_1);
        executor.execute(futureTask_1);
 
        // Start thread for the second half of the numbers
        FutureTask<Long> futureTask_2 = new FutureTask<Long>(new CallableCalculater(MAX_NUMBER / 2 + 1, MAX_NUMBER, 3));
        taskList.add(futureTask_2);
        executor.execute(futureTask_2);
 
        // Wait until all results are available and combine them at the same time
        for (FutureTask<Long> futureTask : taskList) {
        	try {
				resultFuture += futureTask.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
        
        // Shutdown the ExecutorService 
        executor.shutdown();
        
        long timeEndFuture = System.currentTimeMillis();
        long timeNeededFuture = timeEndFuture - timeStartFuture;
        System.out.println("Result (Future): " + resultFuture + " calculated in " + timeNeededFuture + " ms");

	}

}

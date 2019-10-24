package arrayblockingqueueexample;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    
    private BlockingQueue<String> queue;
    
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        
        // As long as there are empty positions in our array,
        // we want to check what's going on.
        while (queue.remainingCapacity() > 0) {
            System.out.println("Queue size: " + queue.size() +
                    ", remaining capacity: " + queue.remainingCapacity());
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
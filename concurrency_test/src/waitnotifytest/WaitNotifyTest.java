package waitnotifytest;


class Process{
	
	
	public void producer() throws InterruptedException{
		Thread.sleep(2000);
		
		synchronized (this) {
				System.out.println("Producer Sending Notification ");
				notify();
		}
	}
	
	public void consumer() throws InterruptedException{
		synchronized (this) {
			while(true){
				System.out.println("Consumer Going to wait\n");
				wait();
				System.out.println("Consumer Got notification\n");
			}
		}
	}
	
}

public class WaitNotifyTest {

	public static void main(String[] args) {
	
		Process process = new Process();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true){
						process.producer();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Ends :: ");
	}
	
}

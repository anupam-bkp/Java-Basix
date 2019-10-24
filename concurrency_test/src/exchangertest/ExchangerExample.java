package exchangertest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerExample {

	Exchanger<String> exchanger = new Exchanger<>();

	private class Producer implements Runnable {
		private String queue;
		@Override
		public void run() {
			try	{
				//create tasks & fill the queue
				//exchange the full queue for a empty queue with Consumer
				queue = exchanger.exchange("Producer Queue");
				System.out.println(Thread.currentThread().getName()+" now has "+queue);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class Consumer implements Runnable {

		private String queue;
		@Override
		public void run() {
			try	{
				//do procesing & empty the queue
				//exchange the empty queue for a full queue with Producer
				queue = exchanger.exchange("Consumer Queue");
				System.out.println(Thread.currentThread().getName()+" now has "+queue);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class Observer implements Runnable {

		private String queue;
		@Override
		public void run() {
			try	{
				//do procesing & empty the queue
				//exchange the empty queue for a full queue with Producer
//				queue = exchanger.exchange("Observer Queue");
				queue = exchanger.exchange("Observer Queue", 1000, TimeUnit.MILLISECONDS);
				
				System.out.println(Thread.currentThread().getName()+" now has "+queue);
			}
			catch (InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void start() {
		new Thread(new Producer(),"Producer").start();
		new Thread(new Consumer(),"Consumer").start();
		new Thread(new Observer(),"Observer").start();
		
	}

	public static void main(String[] args) {

		new ExchangerExample().start();
	}

}

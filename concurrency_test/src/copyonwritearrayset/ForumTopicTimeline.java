package copyonwritearrayset;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class ForumTopicTimeline implements Runnable {
	public static enum Operation { ADD, REMOVE }

	private Entry entry;
	private Operation operation;
	private static final CopyOnWriteArraySet<Entry> topics = new CopyOnWriteArraySet<>();

	public ForumTopicTimeline() {
	}

	public ForumTopicTimeline(Entry entry, Operation operation) {
		this.entry = entry;
		this.operation = operation;
	}

	public void addEntry(Entry entry) {
		topics.add(entry);
	}

	public void removeEntry(Entry entry) {
		topics.remove(entry);
	}

	public static void printTimeline() {
		try {
			TimeUnit.SECONDS.sleep(1);
			
			System.out.println("Timeline:");
			Iterator<Entry> it = topics.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace(System.err);
		}
	}
	
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public static CopyOnWriteArraySet<Entry> getTopics() {
		return topics;
	}

	@Override
	public void run() {
		switch (this.operation) {
		case ADD:
			this.addEntry(this.entry);
			break;
		case REMOVE:
			this.removeEntry(this.entry);
			break;
		}
	}
}

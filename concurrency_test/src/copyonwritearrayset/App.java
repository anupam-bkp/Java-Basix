package copyonwritearrayset;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		ForumTopicTimeline[] timeline = new ForumTopicTimeline[5];

		// Create five Topics
		// Costly operation - A new copy of the collection is created each time
		timeline[0] = new ForumTopicTimeline(
				new Entry("Topic1", "Description1"),
				ForumTopicTimeline.Operation.ADD);
		timeline[1] = new ForumTopicTimeline(
				new Entry("Topic2", "Description2"),
				ForumTopicTimeline.Operation.ADD);
		timeline[2] = new ForumTopicTimeline(
				new Entry("Topic3", "Description3"),
				ForumTopicTimeline.Operation.ADD);
		timeline[3] = new ForumTopicTimeline(
				new Entry("Topic4", "Description4"),
				ForumTopicTimeline.Operation.ADD);
		timeline[4] = new ForumTopicTimeline(
				new Entry("Topic5", "Description5"),
				ForumTopicTimeline.Operation.ADD);

		for (int i = 0; i < 5; i++) {
			executorService.submit(timeline[i]);
		}

		// Print Timeline
		ForumTopicTimeline.printTimeline();

		// Costly operation - A new copy of the collection is created each time
		timeline[0].setOperation(ForumTopicTimeline.Operation.REMOVE);
		executorService.submit(timeline[0]);

		// Print Timeline
		ForumTopicTimeline.printTimeline();

		// Try to remove an Entry using the iterator
		Iterator<Entry> it = ForumTopicTimeline.getTopics().iterator();
		try {
			it.remove();
		} catch (UnsupportedOperationException uoe) {
			uoe.printStackTrace(System.err);
		}

		executorService.shutdown();
	}
}

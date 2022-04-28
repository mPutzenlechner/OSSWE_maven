package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class QueueTest {
	private Queue queue;
	
	@Before
	public void erzeugeQueue() {
		queue = new Queue(3);
	}
	
	@Test
	public void testQueue1() {
		// test simple enqueueing/dequeueing of 3 objects (fits in queue)
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
		assertEquals(3, queue.dequeue());
	}
	
	@Test
	public void testQueue2() {
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		// 3 entered last, should be overwritten to 6 should be overwritten
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
		assertEquals(6, queue.dequeue());
	}
	
	@Test(expected=IllegalStateException.class, timeout=1000)
	public void testQueueException() {
		queue.dequeue();
	}

}

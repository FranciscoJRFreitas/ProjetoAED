
package dataStructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListTest {

	@Test
	public void test0() {
		List<Integer> list = new DoubleList<Integer>();

		// 1. List should start empty
		assertEquals(true, list.isEmpty());

		// 2. Any attempt to access the first/last element should throw exception
		try {
			list.getFirst();
			fail();
		} catch (EmptyListException e) {
			// expected
		}

		try {
			list.getLast();
			fail();
		} catch (EmptyListException e) {
			// expected
		}

		// 3. Adding 10 elements, then iterating
		int i = 10;
		while (i-- > 0)
			list.addFirst(i);

		assertEquals(false, list.isEmpty());
		assertEquals(10, list.size());

		Iterator<Integer> it = list.iterator();

		int j = 0;
		while (it.hasNext()) {
			assertEquals((Integer) j++, it.next());
		}

	}

	@Test
	public void test1() {
		List<Integer> list = new DoubleList<Integer>();

		list.addFirst(1);
		list.addFirst(2);
		list.addLast(0);
		list.addLast(-1);
		list.add(2, 10);

		assertEquals((Integer) (-1), list.removeLast());
		assertEquals((Integer) 0, list.removeLast());
		assertEquals((Integer) 2, list.removeFirst());
		assertEquals((Integer) 1, list.removeFirst());
		assertEquals((Integer) 10, list.remove(0));
	}

	@Test
	public void test2() {
		List<Integer> list = new DoubleList<Integer>();

		int i = 10;
		while (i > 0)
			list.addLast(i--);

		TwoWayIterator<Integer> it = (TwoWayIterator<Integer>) list.iterator();

		it.fullForward();
		int j = 1;
		while (it.hasPrevious())
			assertEquals((Integer) (j++), it.previous());

		j = 10;
		it.rewind();
		while (it.hasNext())
			assertEquals((Integer) (j--), it.next());

	}

	@Test
	public void testAppend() {
		List<Integer> list1 = new DoubleList<Integer>();
		List<Integer> list2 = new DoubleList<Integer>();
		int i = 1;
		while (i <= 10)
			list1.addLast(i++);
		i = 20;
		while (i > 10)
			list2.addFirst(i--);

		DoubleList<Integer> l1 = (DoubleList<Integer>) list1;
		DoubleList<Integer> l2 = (DoubleList<Integer>) list2;
		l1.append(l2);
		list1 = l1;
		assert (!list1.isEmpty());
		assertEquals(20, list1.size());
		TwoWayIterator<Integer> it = (TwoWayIterator<Integer>) list1.iterator();
		it.fullForward();
		int j = 20;
		while (it.hasPrevious())
			assertEquals((Integer) (j--), it.previous());

	}

	@Test
	public void testAdd() {
		List<Integer> list = new DoubleList<Integer>();
		assert (list.isEmpty());
		list.addFirst(1);
		assert (!list.isEmpty());
		assertEquals(list.size(), 1);
		try {
			list.add(5, 5);
			fail();
		} catch (InvalidPositionException e) {
			// expected
		}
		list.add(0, 0);
		list.add(2, 2);
		list.addLast(3);
		Iterator<Integer> it = list.iterator();
		int i = 0;
		while (it.hasNext())
			assertEquals(it.next(), (Integer) i++);

	}

	@Test
	public void testAppendGet() {
		List<Integer> list1 = new DoubleList<Integer>();
		List<Integer> list2 = new DoubleList<Integer>();
		int i = 1;
		while (i <= 10)
			list1.addLast(i++);
		i = 20;
		while (i > 10)
			list2.addFirst(i--);

		DoubleList<Integer> l1 = (DoubleList<Integer>) list1;
		DoubleList<Integer> l2 = (DoubleList<Integer>) list2;
		l1.append(l2);
		list1 = l1;
		assert (!list1.isEmpty());
		assertEquals(20, list1.size());
		TwoWayIterator<Integer> it = (TwoWayIterator<Integer>) list1.iterator();
		it.fullForward();
		int j = 20;
		while (it.hasPrevious())
			assertEquals((Integer) (j--), it.previous());
		Integer seis = list1.get(5);
		assertEquals(seis, (Integer) 6);
		Integer um = list1.getFirst();
		assertEquals(um, (Integer) 1);
		Integer vinte = list1.getLast();
		assertEquals(vinte, (Integer) 20);
	}
	// TODO more tests.
}
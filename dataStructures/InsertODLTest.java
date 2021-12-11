package dataStructures;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class InsertODLTest {

	@Test
	public void insertSimpleTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<Integer, Integer>();

		od.insert(5, 5); // insercao a cabeca
		od.insert(10, 10); // insercao a cauda
		od.insert(-3, -3); // insercao a cabeca
		od.insert(4, 4); // insercao do segundo
		od.insert(9, 9); // insercao do penultimo
		od.insert(7, 77); // insercao no meio
		od.insert(7, 7); // substituição do valor

		assertEquals(od.size(),6);

		TwoWayIterator<SetEntry<Integer, Integer>> it = (TwoWayIterator<SetEntry<Integer, Integer>>) od.iterator();

		assertTrue(it.hasNext());
		assertTrue(it.next().getKey() == -3);
		assertTrue(it.next().getKey() == 4);
		assertTrue(it.next().getKey() == 5);
		assertTrue(it.next().getKey() == 7);
		assertTrue(it.next().getKey() == 9);
		assertTrue(it.next().getKey() == 10);

		it.fullForward();
		assertTrue(it.hasPrevious());
		assertTrue(it.previous().getKey() == 10);
		assertTrue(it.previous().getKey() == 9);
		assertTrue(it.previous().getKey() == 7);
		assertTrue(it.previous().getKey() == 5);
		assertTrue(it.previous().getKey() == 4);
		assertTrue(it.previous().getKey() == -3);
		assertFalse(it.hasPrevious());	
	}
	
	@Test
	public void insertinsertRandomTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<Integer, Integer>();
		
		insertRandomElems(od, 100);
		
		Iterator<SetEntry<Integer, Integer>> it = od.iterator();
		Entry<Integer, Integer> previous = it.next();
		Entry<Integer, Integer> current;
		while (it.hasNext()) { // lista esta ordenada
			current = it.next();
			assertTrue(previous.getKey().compareTo(current.getKey()) < 0); 
			previous = current;
		}
		
		
		it = od.iterator();
		Entry<Integer, Integer> max = od.maxEntry();
		Entry<Integer, Integer> min = od.minEntry();
		while (it.hasNext()) { 
			current = it.next();
			// maximo 'e maior ou igual a todos os elementos
			assertTrue(current.getKey().compareTo(max.getKey()) <= 0);  
			// minimo 'e menor ou igual a todos os elementos
			assertTrue(current.getKey().compareTo(min.getKey()) >= 0); 
		}
		
	}

	private static void insertRandomElems(OrderedDictionary<Integer, Integer> od, int elems) {
		Random rand = new Random();
		for (int i = 0; i < elems; i++) {
			int e = rand.nextInt();
			od.insert(e, e);
		}
	}
}

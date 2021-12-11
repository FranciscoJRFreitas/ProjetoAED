package dataStructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveODLTest {

	@Test
	public void removeSimpleTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<Integer, Integer>();

		assertEquals(od.remove(4),null);
		od.insert(5, 5);
		od.insert(10, 10);
		od.insert(-3, -3);
		od.insert(4, 4);
		od.insert(9, 9);
		od.insert(7, 7);

		assertEquals(od.size(),6);
		
		assertTrue(od.remove(19) == null);
		assertTrue(od.remove(4) == 4); //remover no meio
		assertTrue(od.remove(-3) == -3); //remover no inicio
		assertTrue(od.remove(10) == 10); //remover no fim
		
		assertEquals(od.size(),3);

		Iterator<SetEntry<Integer, Integer>> it = od.iterator();

		assertTrue(it.hasNext());
		assertTrue(it.next().getKey() == 5);
		assertTrue(it.next().getKey() == 7);
		assertTrue(it.next().getKey() == 9);	
		
		assertTrue(od.remove(5) == 5);
		assertTrue(od.remove(9) == 9);
		assertTrue(od.remove(7) == 7);
		
		assertTrue(od.remove(7) == null);
	}
}

package dataStructures;

import java.io.Serializable;

public interface SortedList<E> extends Serializable {

	boolean isEmpty();

	int size();

	boolean hasElement(E element);

	E insertSort(E element);

	E remove(E element);

	E getHead() throws EmptyDictionaryException;

	E getTail() throws EmptyDictionaryException;

	Iterator<E> iterator();

}

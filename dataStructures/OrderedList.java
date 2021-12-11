package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

public class OrderedList<E extends Comparable<E>> implements SortedList<E> {
	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	private DoubleListNode<E> head;

	private DoubleListNode<E> tail;

	private int size;

	private Comparator<E> comparator;

	public OrderedList() {
		head = null;
		tail = null;
		size = 0;
		comparator = null;
	}

	public OrderedList(Comparator<E> comparator) {
		head = null;
		tail = null;
		size = 0;
		this.comparator = comparator;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean hasElement(E element) {
		DoubleListNode<E> node = head;
		while (node != null && !node.getElement().equals(element)) {
			node = node.getNext();
		}
		if (node == null)
			return false;
		else {
			return true;
		}
	}

	@Override
	public E insertSort(E element) {
		DoubleListNode<E> node = new DoubleListNode<E>(element, null, null);
		if (!hasElement(element)) {
			if (isEmpty()) {
				head = node;
				tail = node;
			} else {
				DoubleListNode<E> aux = head;
				int pos = 0;
				while (aux != null && compare(element,aux.getElement()) > 0) {
					pos++;
					aux = aux.getNext();
				}
				add(pos, node, aux);
			}
			size++;
			return null;
		} else {
			return element;
		}
	}

	private void add(int pos, DoubleListNode<E> node, DoubleListNode<E> aux) {
		if (pos == 0)
			this.addFirst(node);
		else if (pos == size)
			this.addLast(node);
		else
			this.addMiddle(node, aux);
	}

	private void addFirst(DoubleListNode<E> node) {
		head.setPrevious(node);
		node.setNext(head);
		head = node;
	}

	private void addMiddle(DoubleListNode<E> node, DoubleListNode<E> aux) {
		DoubleListNode<E> previousAux = aux.getPrevious();
		previousAux.setNext(node);
		node.setPrevious(previousAux);
		node.setNext(aux);
		aux.setPrevious(node);

	}

	private void addLast(DoubleListNode<E> node) {
		tail.setNext(node);
		node.setPrevious(tail);
		tail = node;
	}

	@Override
	public E remove(E element) {
		if (!isEmpty() && !hasElement(element)) {
			DoubleListNode<E> aux = head;
			int pos = 0;
			while (aux != null && compare(element,aux.getElement()) != 0) {
				pos++;
				aux = aux.getNext();
			}
			remove(pos, aux);
			size--;
			return aux.getElement();
		} else
			return null;
	}

	private void remove(int pos, DoubleListNode<E> nodeToRemove) {
		if (size == 1) {
			head = null;
			tail = null;
		} else if (pos == 0)
			this.removeFirst();
		else if (pos == size - 1)
			this.removeLast();
		else
			this.removeMiddle(nodeToRemove);
	}

	private void removeFirst() {
		DoubleListNode<E> nextHead = head.getNext();
		nextHead.setPrevious(null);
		head = nextHead;
	}

	private void removeMiddle(DoubleListNode<E> nodeToRemove) {
		DoubleListNode<E> previousAux = nodeToRemove.getPrevious();
		DoubleListNode<E> nextAux = nodeToRemove.getNext();
		previousAux.setNext(nextAux);
		nextAux.setPrevious(previousAux);
	}

	private void removeLast() {
		DoubleListNode<E> previousTail = tail.getPrevious();
		previousTail.setNext(null);
		tail = previousTail;
	}

	@Override
	public E getHead() throws EmptyDictionaryException {
		return head.getElement();
	}

	@Override
	public E getTail() throws EmptyDictionaryException {
		return tail.getElement();
	}

	@Override
	public Iterator<E> iterator() {
		return new DoubleListIterator<E>(head, tail);
	}

	private int compare(E e1, E e2) {
		if (comparator == null)
			return e1.compareTo(e2);
		else
			return compare(e1, e2);
	}
}
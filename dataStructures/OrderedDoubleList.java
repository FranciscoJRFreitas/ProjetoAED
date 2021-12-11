package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 1L;

	private DoubleListNode<SetEntry<K, V>> head;

	private DoubleListNode<SetEntry<K, V>> tail;

	private SetEntry<K, V> currentElement;

	private int size;

	public OrderedDoubleList() {
		head = null;
		tail = null;
		size = 0;
		currentElement = null;
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
	public V find(K key) {
		DoubleListNode<SetEntry<K, V>> node = head;
		while (node != null && !node.getElement().getKey().equals(key)) {
			node = node.getNext();
		}
		if (node == null)
			return null;
		else {
			currentElement = node.getElement();
			return node.getElement().getValue();
		}
	}

	@Override
	public V insert(K key, V value) {
		SetEntry<K, V> entry = new EntryClass<K, V>(key, value);
		DoubleListNode<SetEntry<K, V>> node = new DoubleListNode<SetEntry<K, V>>(entry, null, null);
		V oldVal = find(key);
		if (oldVal == null) {
			if (isEmpty()) {
				head = node;
				tail = node;
			} else {
				DoubleListNode<SetEntry<K, V>> aux = head;
				int pos = 0;
				while (aux != null && key.compareTo(aux.getElement().getKey()) > 0) {
					pos++;
					aux = aux.getNext();
				}
				add(pos, node, aux);
			}
			size++;
			return null;
		} else {
			currentElement.setValue(value);
			return oldVal;
		}
	}

	private void add(int pos, DoubleListNode<SetEntry<K, V>> node, DoubleListNode<SetEntry<K, V>> aux) {
		if (pos == 0)
			this.addFirst(node);
		else if (pos == size)
			this.addLast(node);
		else
			this.addMiddle(node, aux);
	}

	private void addFirst(DoubleListNode<SetEntry<K, V>> node) {
		head.setPrevious(node);
		node.setNext(head);
		head = node;
	}

	private void addMiddle(DoubleListNode<SetEntry<K, V>> node, DoubleListNode<SetEntry<K, V>> aux) {
		DoubleListNode<SetEntry<K, V>> previousAux = aux.getPrevious();
		previousAux.setNext(node);
		node.setPrevious(previousAux);
		node.setNext(aux);
		aux.setPrevious(node);

	}

	private void addLast(DoubleListNode<SetEntry<K, V>> node) {
		tail.setNext(node);
		node.setPrevious(tail);
		tail = node;
	}

	@Override
	public V remove(K key) {
		if (!isEmpty() && find(key) != null) {
			DoubleListNode<SetEntry<K, V>> aux = head;
			int pos = 0;
			while (aux != null && key.compareTo(aux.getElement().getKey()) != 0) {
				pos++;
				aux = aux.getNext();
			}
			remove(pos, aux);
			size--;
			return aux.getElement().getValue();
		} else
			return null;
	}

	private void remove(int pos, DoubleListNode<SetEntry<K, V>> nodeToRemove) {
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
		DoubleListNode<SetEntry<K, V>> nextHead = head.getNext();
		nextHead.setPrevious(null);
		head = nextHead;
	}

	private void removeMiddle(DoubleListNode<SetEntry<K, V>> nodeToRemove) {
		DoubleListNode<SetEntry<K, V>> previousAux = nodeToRemove.getPrevious();
		DoubleListNode<SetEntry<K, V>> nextAux = nodeToRemove.getNext();
		previousAux.setNext(nextAux);
		nextAux.setPrevious(previousAux);
	}

	private void removeLast() {
		DoubleListNode<SetEntry<K, V>> previousTail = tail.getPrevious();
		previousTail.setNext(null);
		tail = previousTail;
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if(isEmpty()) 
			 throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if(isEmpty()) 
			 throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Iterator<SetEntry<K, V>> iterator() {
		return new DoubleListIterator<SetEntry<K, V>>(head,tail);
	}

}

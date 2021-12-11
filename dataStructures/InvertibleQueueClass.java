package dataStructures;

public class InvertibleQueueClass<E> implements InvertibleQueue<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Memory of the queue: a list.
	 */
	protected List<E> list;

	/**
	 * Variable that represents if the list is inverted or not.
	 */
	protected boolean isInverted;

	/**
	 * Constructor create an empty Doubly Linked List.
	 */
	public InvertibleQueueClass() {
		list = new DoubleList<E>();
		isInverted = false;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void enqueue(E element) {
		if (isInverted)
			list.addFirst(element);
		else
			list.addLast(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (list.isEmpty())
			throw new EmptyQueueException();
		E object = null;
		if (isInverted)
			object = list.removeLast();
		else
			object = list.removeFirst();
		return object;
	}

	@Override
	public void invert() {
		isInverted = !isInverted;
	}

}

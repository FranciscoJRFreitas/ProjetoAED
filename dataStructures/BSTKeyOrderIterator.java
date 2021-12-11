package dataStructures;

/**
 * BSTKeyOrderIterator implementation
 * 
 * @author Danny Fernandes / Francisco Freitas
 * @version 1.0
 *
 * @param <K> Generic type Key
 * @param <V> Generic type Value
 */
public class BSTKeyOrderIterator<K, V> implements Iterator<SetEntry<K, V>> {

	private static final long serialVersionUID = 0L;
	private BSTNode<K, V> current;
	private BSTNode<K, V> maxRight;
	private BSTNode<K, V> root;
	private boolean hasNext;

	private Stack<BSTNode<K, V>> stack;

	public BSTKeyOrderIterator(BSTNode<K, V> node) {
		root = node;
		maxRight = node;
		hasNext = true;
		rewind();
		while (maxRight.getRight() != null)
			maxRight = maxRight.getRight();
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public EntryClass<K, V> next() throws NoSuchElementException {
		current = stack.pop();
		if (maxRight == current) {
			hasNext = false;
			return current.getEntry();
		}
		if (current.getRight() != null) {
			BSTNode<K, V> temp;
			temp = current.getRight();
			pushLeft(temp);
		}
		if (maxRight == current)
			hasNext = false;
		return current.getEntry();
	}

	@Override
	public void rewind() {
		stack = new StackInList<BSTNode<K, V>>();
		current = root;
		pushLeft(current);
	}

	private void pushLeft(BSTNode<K, V> node) {
		while (node != null) {
			stack.push(node);
			node = node.getLeft();
		}
	}
}

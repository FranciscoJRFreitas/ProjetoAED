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

	/**
	 * Serial Version UID of the Class.
	 */
	private static final long serialVersionUID = 0L;
	
	/**
	 * Number of entries in the tree.
	 * 
	 */
	private BSTNode<K, V> current;
	
	/**
	 * The rightmost element.
	 * 
	 */
	private BSTNode<K, V> maxRight;
	
	/**
	 * The root of the tree.
	 * 
	 */
	private BSTNode<K, V> root;
	
	/**
	 * The current element has a next element to iterate.
	 * 
	 */
	private boolean hasNext;

	/**
	 * A stack structure to help iterate the three.
	 * 
	 */
	private Stack<BSTNode<K, V>> stack;

	/**
	 * Constructor for BTSKeyOrderIterator - creates an empty tree.
	 * 
	 * @param node - The starting node (root) to the three.
	 */
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

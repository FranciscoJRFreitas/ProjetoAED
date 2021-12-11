package dataStructures;

/**
 * Separate Chaining Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 */

public class SepChainHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	private static final int GROWTH_FACTOR = 2;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty separate chaining hash table, with the specified
	 * initial capacity. Each position of the array is initialized to a new ordered
	 * list maxSize is initialized to the capacity.
	 * 
	 * @param capacity defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public SepChainHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++)
			table[i] = new OrderedDoubleList<K, V>();
		maxSize = capacity;
		currentSize = 0;
	}

	public SepChainHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		if (this.isFull())
			this.rehash();
		V val = table[this.hash(key)].insert(key, value);
		if (val == null)
			currentSize++;
		return val;
	}

	private void rehash() {
		SepChainHashTable<K, V> newHashTable = new SepChainHashTable<K, V>(table.length * GROWTH_FACTOR);
		Iterator<SetEntry<K, V>> it = this.iterator();
		while (it.hasNext()) {
			SetEntry<K, V> entry = it.next();
			newHashTable.insert(entry.getKey(), entry.getValue());
		}
		maxSize = newHashTable.maxSize;
		table = newHashTable.table;
	}

	@Override
	public V remove(K key) {
		V val = table[this.hash(key)].remove(key);
		if (val != null)
			currentSize--;
		return val;
	}

	@Override
	public Iterator<SetEntry<K, V>> iterator() {
		return new SepChainIterator<K, V>(table);
	}
}

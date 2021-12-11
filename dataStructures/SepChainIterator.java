package dataStructures;

public class SepChainIterator<K, V> implements Iterator<SetEntry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dictionary<K, V>[] table;

	private Iterator<SetEntry<K, V>> it;

	private int counter;

	public SepChainIterator(Dictionary<K, V>[] table) {
		this.table = table;
		rewind();
	}

	public boolean hasNext() {
		//Find the next iteratable position in the table.
		while (!it.hasNext()) {
			if (counter < table.length - 1)
				it = table[++counter].iterator();
			else
				return it.hasNext();
		}
		return true;
	}
	
	public SetEntry<K, V> next() {
		return it.next();
	}

	@Override
	public void rewind() {
		counter = 0;
		it = table[counter].iterator();
	}

}

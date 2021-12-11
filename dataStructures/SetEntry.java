package dataStructures;

/**
 * Entry Abstract Data Type Includes description of general methods to be
 * implemented by an entry.
 * 
 * @author AED Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */

public interface SetEntry<K, V> extends Entry<K, V> {
	/**
	 * Sets the key.
	 */
	void setKey(K key);

	/**
	 * Sets the value.
	 */
	void setValue(V value);

}

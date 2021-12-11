package dataStructures;

import java.io.Serializable;

public interface Comparator<E> extends Serializable {
	
	int compare(E e1, E e2);

}

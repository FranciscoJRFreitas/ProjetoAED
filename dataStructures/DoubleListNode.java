package dataStructures;

import java.io.Serializable;

/**
 * Double List Node Implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
class DoubleListNode<E> implements Serializable {
	
		/**
		 * Serial Version UID of the Class
		 */
		static final long serialVersionUID = 0L;

		/**
		 * Element stored in the node.
		 */
		private E element;

		/**
		 * (Pointer to) the previous node.
		 */
		private DoubleListNode<E> previous;

		/**
		 * (Pointer to) the next node.
		 */
		private DoubleListNode<E> next;

		/**
		 * 
		 * @param theElement  - The element to be contained in the node
		 * @param thePrevious - the previous node
		 * @param theNext     - the next node
		 */
		public DoubleListNode(E theElement, DoubleListNode<E> thePrevious, DoubleListNode<E> theNext) {
			element = theElement;
			previous = thePrevious;
			next = theNext;
		}

		/**
		 * 
		 * @param theElement to be contained in the node
		 */
		public DoubleListNode(E theElement) {
			this(theElement, null, null);
		}

		/**
		 * 
		 * @return the element contained in the node
		 */
		public E getElement() {
			return element;
		}

		/**
		 * 
		 * @return the previous node
		 */
		public DoubleListNode<E> getPrevious() {
			return previous;
		}

		/**
		 * 
		 * @return the next node
		 */
		public DoubleListNode<E> getNext() {
			return next;
		}

		/**
		 * 
		 * @param newElement - New element to replace the current element
		 */
		public void setElement(E newElement) {
			element = newElement;
		}

		/**
		 * 
		 * @param newPrevious - node to replace the current previous node
		 */
		public void setPrevious(DoubleListNode<E> newPrevious) {
			previous = newPrevious;
		}

		/**
		 * 
		 * @param newNext - node to replace the next node
		 */
		public void setNext(DoubleListNode<E> newNext) {
			next = newNext;
		}

	}

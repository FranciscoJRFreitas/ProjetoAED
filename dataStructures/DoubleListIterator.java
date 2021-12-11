package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

/**
 * Implementation of Two Way Iterator for DLList 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
class DoubleListIterator<E> implements TwoWayIterator<E>
{

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /** 
     * Node with the first element in the iteration.
     */
    protected DoubleListNode<E> firstNode;

    /**
     * Node with the last element in the iteration.
     */
    protected DoubleListNode<E> lastNode;

    /**
     * Node with the next element in the iteration.
     */
    protected DoubleListNode<E> nextToReturn;

    /**
     * Node with the previous element in the iteration.
     */
    protected DoubleListNode<E> prevToReturn;


    /**
     * DoublyLLIterator constructor
     * @param first - Node with the first element of the iteration
     * @param last - Node with the last element of the iteration
     */
    public DoubleListIterator( DoubleListNode<E> first, DoubleListNode<E> last )
    {
        firstNode = first;
        lastNode = last;
        this.rewind();
    }      

	@Override
    public void rewind( )
    {
        nextToReturn = firstNode;
        prevToReturn = null;
    }


    @Override
    public void fullForward( )
    {
        prevToReturn = lastNode;
        nextToReturn = null;
    }


    @Override
    public boolean hasNext( )
    {
        return nextToReturn != null;
    }


    @Override
    public boolean hasPrevious( )
    {
        return prevToReturn != null;
    }


    @Override
    public E next( ) throws NoSuchElementException
    {
        if ( !this.hasNext() )
            throw new NoSuchElementException();

        E element = nextToReturn.getElement();
        prevToReturn = nextToReturn.getPrevious();
        nextToReturn = nextToReturn.getNext();
        return element;
    }


    @Override
    public E previous( ) throws NoSuchElementException
    {
        if ( !this.hasPrevious() )
            throw new NoSuchElementException();

        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn.getNext();
        prevToReturn = prevToReturn.getPrevious();
        return element;
    }


}

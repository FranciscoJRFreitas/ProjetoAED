package dataStructures;

public class ConcatenableQueueInList<E> extends QueueInList<E> implements ConcatenableQueue<E> {

	
	public ConcatenableQueueInList() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void append(ConcatenableQueue<E> queue) {
				if (queue instanceof QueueInList) {
					QueueInList<E> o = (QueueInList<E>) queue;
					QueueInList<E> t = (QueueInList<E>) list;

				} else {
					while (!queue.isEmpty()) {
						this.enqueue(queue.dequeue());
					}
				}
	}

}

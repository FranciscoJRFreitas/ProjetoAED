package domain.tags;

import dataStructures.BinarySearchTree;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.SetEntry;
import domain.shows.Show;

public class TagClass implements SetTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*The name of the tag.
	*/
	private String tagName;
	/**
	*A dictionary that links the title of the show to a show object.
	*/
	private OrderedDictionary<String, Show> shows;

	public TagClass(String tagName) {
		this.tagName = tagName;
		shows = new BinarySearchTree<String, Show>();
	}

	@Override
	public String getTag() {
		return tagName;
	}
	
	@Override
	public boolean hasShow() {
		return !shows.isEmpty();
	}

	@Override
	public void addShowWithTag(Show show) {
		shows.insert(show.getShowTitle(), show);
	}

	@Override
	public void removeShow(String showTitle) {
		shows.remove(showTitle);
	}

	@Override
	public Iterator<SetEntry<String, Show>> showsIterator() {
		return shows.iterator();
	}

	@Override
	public boolean hasTaggedShows() {
		return !shows.isEmpty();
	}

}

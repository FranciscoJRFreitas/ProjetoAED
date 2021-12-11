package domain.tags;

import dataStructures.BinarySearchTree;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.SetEntry;
import domain.shows.Show;
/**
*Class that represents a tag in database.
*
* @author Danny Fernandes / Francisco Freita
*/
public class TagClass implements SetTag {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	// Class variables
	
	/**
	*The name of the tag.
	*/
	private String tagName;
	/**
	*A dictionary that links the title of the show to a show object.
	*/
	private OrderedDictionary<String, Show> shows;
	
	
	/**
	 * TagClass constructor.
	 * 
	 * @param tagName - tag identifier.
	 */
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

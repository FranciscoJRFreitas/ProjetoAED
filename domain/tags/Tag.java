package domain.tags;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.SetEntry;
import domain.shows.Show;

public interface Tag extends Serializable {
	/**
	*Gives the tag name.
	*
	*@return - tag name.
	*/
	String getTag();
	
	/**
	 * Returns an iterator of all entries of shows associated with this tag.
	 * 
	 * @return - Show iterator with all tag s shows.
	 */
	Iterator<SetEntry<String, Show>> showsIterator();
	/**
	*
	*
	*
	*/
	boolean hasTaggedShows();

	boolean hasShow();

}

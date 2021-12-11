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
	*
	*
	*/
	Iterator<SetEntry<String, Show>> showsIterator();

	boolean hasTaggedShows();

	boolean hasShow();

}

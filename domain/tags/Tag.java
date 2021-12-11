package domain.tags;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.SetEntry;
import domain.shows.Show;
/**
 * Interface that represents the tag object and is responsible to comunicate
 * to the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
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
	 * Checks if the tag has any shows associated.
	 * 
	 * @return - <code>true</code>, if the tag has associated shows;
	 *         <code>false</code>, otherwise.
	 */
	boolean hasTaggedShows();

}

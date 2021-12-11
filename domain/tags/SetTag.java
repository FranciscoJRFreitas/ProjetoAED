package domain.tags;

import domain.shows.Show;
/**
 * Interface that sets the tag information, restricted to the user.
 * 
 */
public interface SetTag extends Tag {
	/**
	 * Adds a show associated with this tag.
	 * 
	 * @param - show to add.
	 */
	void addShowWithTag(Show show);
	
	/**
	*Removes the show that will be removed from the database.
	*
	*@param - show title to remove.
	*/
	void removeShow(String showTitle);
}

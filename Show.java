package domain.shows;

import java.io.Serializable;

import dataStructures.Iterator;
import domain.participations.Participation;
import domain.persons.SetPerson;

/**
 * Interface that represents the show object and is responsible to comunicate to
 * the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface Show extends Serializable {

	/**
	 * Returns the show identifier.
	 * 
	 * @return - idShow.
	 */
	String getIdShow();

	/**
	 * Returns the show s title.
	 * 
	 * @return - showTitle.
	 */
	String getShowTitle();

	/**
	 * Returns the show s production year.
	 * 
	 * @return - productionYear.
	 */
	int getProductionYear();

	/**
	 * Returns the show average rating.
	 * 
	 * @return - showAverage.
	 */
	int getShowAverage();

	/**
	 * Checks if the show is in production or not.
	 * 
	 * @param productionYear - show s production year.
	 * @return - <code>true</code>, if the show is in production;
	 *         <code>false</code>, otherwise.
	 */
	boolean isInProduction();

	/**
	 * Returns an iterator of all the tags in the show.
	 * 
	 * @return - Tag iterator with all show s tags.
	 */
	Iterator<String> tagIterator();

	/**
	 * Returns an iterator of all participations of a show.
	 * 
	 * @return - Participation iterator with all show s participations.
	 */
	Iterator<Participation> participationsIterator();

	/**
	 * Checks if the show has any tags associated.
	 * 
	 * @return - <code>true</code>, if the show has associated tags;
	 *         <code>false</code>, if not.
	 */
	boolean hasAnyTag();

	/**
	 * Verifies if the show has any participations associated.
	 * 
	 * @return - <code>true</code>, if the show has participations;
	 *         <code>false</code>, otherwise.
	 */
	boolean hasAnyParticipation();

	/**
	 * Checks if the show has been rated.
	 * 
	 * @return - <code>true</code>, if the show has been rated; <code>false</code>,
	 *         if not.
	 */
	boolean isRated();

	Iterator<SetPerson> personIterator();

	
}

package domain.shows;

import domain.participations.Participation;

/**
 * Interface that sets the show information, restricted to the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface SetShow extends Show {

	/**
	 * Terminates the production of the given show. When this command is applied
	 * successfully, the show will be no longer in production.
	 */
	void premiere();

	/**
	 * Adds a new participation to the show.
	 * 
	 * @param oneParticipation - a participation.
	 */
	void addParticipation(Participation oneParticipation);

	/**
	 * Rates the show with the given classification.
	 * 
	 * @pre: classification is between 0 and 10.
	 * @param stars - the rating given to the show (between 0 and 10).
	 */
	void rate(int stars);

	/**
	 * Adds a tag to the show.
	 * 
	 * @param tag - String with the tag name.
	 */
	void addTag(String tag);



}

package domain.persons;

import domain.shows.Show;

/**
 * Interface that sets the person information, restricted to the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface SetPerson extends Person {

	/**
	 * Adds a show that the person has participated in.
	 * 
	 * @param oneShow - the show in the system.
	 */
	void addParticipatedShow(Show oneShow);
	
	/**
	 * Removes a show that the person has participated in.
	 * 
	 * @param idShow - the show identifier.
	 */
	void removeShow(String idShow);

}

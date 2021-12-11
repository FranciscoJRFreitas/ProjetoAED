package domain.participations;

import java.io.Serializable;

import domain.persons.Person;

/**
 * Interface that represents the participation object and it s responsible to
 * communicate to the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface Participation extends Serializable {

	/**
	 * Returns the person in the participation.
	 * 
	 * @return person.
	 */
	Person getPerson();

	/**
	 * Returns the participation s description.
	 * 
	 * @return - description.
	 */
	String getDescription();

	/**
	 * Returns the show identifier.
	 * 
	 * @return - showId.
	 */
	String getShowID();
}

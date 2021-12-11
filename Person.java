package domain.persons;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.SetEntry;
import domain.shows.Show;

/**
 * Interface that represents the person object and is responsible to comunicate
 * to the user.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface Person extends Serializable {

	/**
	 * Returns the person id.
	 * 
	 * @return the idPerson.
	 */
	String getIdPerson();

	/**
	 * Returns the person name.
	 *
	 * @return the name.
	 */
	String getName();

	/**
	 * Returns the person year of birth.
	 * 
	 * @return the birthYear.
	 */
	int getBirthYear();

	/**
	 * Returns the person email.
	 * 
	 * @return the mail.
	 */
	String getMail();

	/**
	 * Returns the person phone number.
	 * 
	 * @return the phoneNum.
	 */
	String getPhoneNumber();

	/**
	 * Returns the person gender.
	 * 
	 * @return the gender.
	 */
	String getGender();

	/**
	 * Returns the shows that the person has participated in.
	 * 
	 * @return - shows collection.
	 */
	Iterator<SetEntry<String, Show>> showsIterator();

	/**
	 * Checks if the person has participated in any shows.
	 * 
	 * @return - <code>true</code>, if the person has already in participated in any
	 *         show; <code>false</code>, otherwise.
	 */
	boolean hasShows();

}

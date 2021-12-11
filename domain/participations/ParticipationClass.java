package domain.participations;

import domain.persons.*;

/**
 * Class that represents the participation object.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public class ParticipationClass implements Participation {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	// Class variables

	/**
	 * person with the person object.
	 */
	private Person person;

	/**
	 * String with the showId.
	 */
	private String showID;

	/**
	 * String with the participation s description.
	 */
	private String description;

	/**
	 * ParticipationClass constructor.
	 * 
	 * @param person      - person.
	 * @param showID      - show identifier.
	 * @param description - participation s description.
	 */
	public ParticipationClass(Person person, String showID, String description) {
		this.person = person;
		this.showID = showID;
		this.description = description;
	}

	@Override
	public Person getPerson() {
		return person;
	}

	@Override
	public String getShowID() {
		return showID;
	}

	@Override
	public String getDescription() {
		return description;
	}

}

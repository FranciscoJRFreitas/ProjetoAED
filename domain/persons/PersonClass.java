package domain.persons;


import dataStructures.BinarySearchTree;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.SetEntry;
import domain.shows.Show;

/**
 * Class that represents the person (Professional) object and stores his
 * information.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public class PersonClass implements SetPerson {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	// Class variables

	/**
	 * String with the person identifier.
	 */
	private String idPerson;

	/**
	 * String with the person s name.
	 */
	private String name;

	/**
	 * int with the person s year of birth.
	 */
	private int birthYear;

	/**
	 * String with the person s email.
	 */
	private String mail;

	/**
	 * String with the person s phone number.
	 */
	private String phoneNumber;

	/**
	 * String with the person s gender.
	 */
	private String gender;

	/**
	 * A set of shows that the person participates in.
	 */
	private OrderedDictionary<String, Show> shows;

	/**
	 * ProfessionalClass constructor.
	 * 
	 * @param idPerson  - person s identifier.
	 * @param birthYear - person s year of birth.
	 * @param mail      - person s email.
	 * @param phoneNum  - person s phone number.
	 * @param gender    - person s gender.
	 * @param name      - person s name.
	 */
	public PersonClass(String idPerson, int birthYear, String mail, String phoneNum, String gender, String name) {
		this.idPerson = idPerson;
		this.name = name;
		this.birthYear = birthYear;
		this.mail = mail;
		this.phoneNumber = phoneNum;
		this.gender = gender;
		shows = new BinarySearchTree<String, Show>();
	}

	@Override
	public String getIdPerson() {
		return idPerson;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getBirthYear() {
		return birthYear;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getGender() {
		return gender;
	}

	@Override
	public Iterator<SetEntry<String, Show>> showsIterator() {
		return shows.iterator();
	}

	@Override
	public void addParticipatedShow(Show oneShow) {
		shows.insert(oneShow.getIdShow(), oneShow);
	}

	@Override
	public boolean hasShows() {
		return !shows.isEmpty();
	}

	@Override
	public void removeShow(String idShow) {
		shows.remove(idShow);
	}

	
	

}

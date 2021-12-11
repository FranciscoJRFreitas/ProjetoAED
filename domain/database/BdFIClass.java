package domain.database;

import dataStructures.*;
import domain.persons.*;
import domain.enums.Gender;
import domain.shows.*;
import domain.tags.*;
import domain.exceptions.*;
import domain.participations.*;

/**
 * Class that represents the system class.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public class BdFIClass implements BdFI {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * int with the minimum valid show rating.
	 */
	private static final int MIN_VALID_RATING = 0;

	/**
	 * int with the maximum valid show rating.
	 */
	private static final int MAX_VALID_RATING = 10;

	private static final int RATING_OPTIONS = 11;

	// Class variables

	/**
	 * Dictionary that links an idPerson to the specific person object in the
	 * system.
	 */
	private Dictionary<String, SetPerson> persons;

	/**
	 * Dictionary that links an idShow to the specific show object in the system.
	 */
	private Dictionary<String, SetShow> shows;

	/**
	 * 
	 */
	private OrderedDictionary<String, Show>[] showsByRating;

	private Dictionary<String, SetTag> tags;

	private int finishedShowsCounter;

	private int ratedShowsCounter;

	/**
	 * System class constructor.
	 */
	@SuppressWarnings("unchecked")
	public BdFIClass() {
		persons = new SepChainHashTable<String, SetPerson>();
		shows = new SepChainHashTable<String, SetShow>();
		tags = new SepChainHashTable<String, SetTag>();
		showsByRating = new OrderedDictionary[RATING_OPTIONS];
		initialize();
		finishedShowsCounter = 0;
		ratedShowsCounter = 0;
	}

	private void initialize() {
		for (int i = 0; i < showsByRating.length; i++) {
			showsByRating[i] = new BinarySearchTree<String, Show>();
		}
	}

	private boolean hasRatedShows() {
		return ratedShowsCounter > 0;
	}

	private boolean hasShowsInProduction() {
		return finishedShowsCounter > 0;
	}

	@Override
	public Person getPerson(String idPerson) throws IdPersonDoesNotExistException {
		Person onePerson = persons.find(idPerson.toUpperCase());
		if (onePerson == null)
			throw new IdPersonDoesNotExistException();
		return onePerson;
	}

	@Override
	public Show getShow(String idShow) throws IdShowDoesNotExistException {
		Show oneShow = shows.find(idShow.toUpperCase());
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		return oneShow;
	}

	@Override
	public Tag getTag(String tag) throws NoShowsWithTagException {
		Tag oneTag = tags.find(tag.toUpperCase());
		if (oneTag == null)
			throw new NoShowsWithTagException();
		return oneTag;
	}

	@Override
	public void addPerson(String idPerson, int birthYear, String mail, String phoneNum, String gender, String name)
			throws InvalidYearException, InvalidGenderInformationException, IdPersonAlreadyExistException {
		if (birthYear <= 0)
			throw new InvalidYearException();
		if (gender.equals(Gender.INVALID.getGenderInfo()))
			throw new InvalidGenderInformationException();
		try {
			if (getPerson(idPerson) != null)
				throw new IdPersonAlreadyExistException();
		} catch (IdPersonDoesNotExistException e) {
			persons.insert(idPerson.toUpperCase(), new PersonClass(idPerson, birthYear, mail, phoneNum, gender, name));
		}
	}

	@Override
	public void addShow(String idShow, int productionYear, String title)
			throws InvalidYearException, IdShowAlreadyExistException {
		if (productionYear <= 0)
			throw new InvalidYearException();
		try {
			if (getShow(idShow) != null)
				throw new IdShowAlreadyExistException();
		} catch (IdShowDoesNotExistException e) {
			SetShow oneShow = new ShowClass(idShow, title, productionYear);
			shows.insert(idShow.toUpperCase(), oneShow);
			if (!oneShow.isInProduction())
				finishedShowsCounter++;
		}

	}

	@Override
	public void addParticipation(String idPerson, String idShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
		SetPerson onePerson = (SetPerson) getPerson(idPerson);
		if (onePerson == null)
			throw new IdPersonDoesNotExistException();
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();

		oneShow.addParticipation(new ParticipationClass(onePerson, idShow, description));
		onePerson.addParticipatedShow(oneShow);
	}

	@Override
	public void premiere(String idShow) throws IdShowDoesNotExistException, ShowHasCompletedProductionException {
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();

		if (!oneShow.isInProduction())
			throw new ShowHasCompletedProductionException();
		oneShow.premiere();
		finishedShowsCounter++;
	}

	@Override
	public void removeShow(String idShow) throws IdShowDoesNotExistException, ShowHasCompletedProductionException {
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		if (!oneShow.isInProduction())
			throw new ShowHasCompletedProductionException();

		if (oneShow.isRated()) {
			showsByRating[oneShow.getShowAverage()].remove(idShow);
			ratedShowsCounter--;
		}
		// Removes the show from each tag associated with it
		Iterator<String> itTag = oneShow.tagIterator();
		while (itTag.hasNext()) {
			String tag = itTag.next();
			SetTag oneTag;
			try {
				oneTag = (SetTag) getTag(tag);
			} catch (NoShowsWithTagException e) {
				break;
			}
			oneTag.removeShow(oneShow.getShowTitle());
			// If the show was the only one asociated with the tag, removes it from the
			// dataBase
			if (!oneTag.hasShow())
				tags.remove(oneTag.getTag().toUpperCase());
		}
		Iterator<SetPerson> itPerson = oneShow.personIterator();
		while (itPerson.hasNext()) {
			itPerson.next().removeShow(oneShow);
		}
		shows.remove(idShow.toUpperCase());
	}

	@Override
	public void tagShow(String idShow, String tag) throws IdShowDoesNotExistException {
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		SetTag oneTag;
		try {
			oneTag = (SetTag) getTag(tag);
		} catch (NoShowsWithTagException e) {
			oneTag = new TagClass(tag);
			tags.insert(tag.toUpperCase(), oneTag);
		}
		oneShow.addTag(tag);
		oneTag.addShowWithTag(oneShow);
	}

	@Override
	public Iterator<SetEntry<String, Show>> getBestShows()
			throws NoShowsException, NoFinishedShowsException, NoRatedShowsException {
		if (shows.isEmpty())
			throw new NoShowsException();
		if (!hasShowsInProduction())
			throw new NoFinishedShowsException();
		if (!hasRatedShows())
			throw new NoRatedShowsException();
		for (int i = showsByRating.length - 1; i >= 0; i--) {
			if (!showsByRating[i].isEmpty())
				return showsByRating[i].iterator();
		}
		return null;

	}

	@Override
	public Iterator<SetEntry<String, Show>> getRatedShows(int rate) throws InvalidRatingException, NoShowsException,
			NoFinishedShowsException, NoRatedShowsException, NoRequestedRatingException {
		if (rate < MIN_VALID_RATING || rate > MAX_VALID_RATING)
			throw new InvalidRatingException();
		if (shows.isEmpty())
			throw new NoShowsException();
		if (!hasShowsInProduction())
			throw new NoFinishedShowsException();
		if (!hasRatedShows())
			throw new NoRatedShowsException();
		if (showsByRating[rate].isEmpty())
			throw new NoRequestedRatingException();
		return showsByRating[rate].iterator();
	}

	@Override
	public void rateShow(int stars, String idShow)
			throws InvalidRatingException, IdShowDoesNotExistException, ShowInProductionException {
		if (stars < MIN_VALID_RATING || stars > MAX_VALID_RATING)
			throw new InvalidRatingException();
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		if (oneShow.isInProduction())
			throw new ShowInProductionException();
		int oldRate = oneShow.getShowAverage();
		oneShow.rate(stars);
		int newRate = oneShow.getShowAverage();
		
		if (oldRate == newRate && newRate == 0)
			showsByRating[newRate].insert(idShow, oneShow);
		
		if (oldRate != newRate) {
			showsByRating[oldRate].remove(idShow);
			showsByRating[newRate].insert(idShow, oneShow);
		}
		ratedShowsCounter++;
	}

	@Override
	public Iterator<SetEntry<String, Show>> getPersonShows(String idPerson)
			throws IdPersonDoesNotExistException, PersonHasNoShowsException {
		Person onePerson = getPerson(idPerson);
		if (onePerson == null)
			throw new IdPersonDoesNotExistException();
		if (!onePerson.hasShows())
			throw new PersonHasNoShowsException();
		return onePerson.showsIterator();
	}

	@Override
	public Iterator<String> tagsIterator(String idShow) throws IdShowDoesNotExistException {
		SetShow oneShow = (SetShow) getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		return oneShow.tagIterator();
	}

	@Override
	public Iterator<Participation> participationsIterator(String idShow)
			throws IdShowDoesNotExistException, ShowHasNoParticipationsException {
		Show oneShow = getShow(idShow);
		if (oneShow == null)
			throw new IdShowDoesNotExistException();
		if (!oneShow.hasAnyParticipation())
			throw new ShowHasNoParticipationsException();

		return oneShow.participationsIterator();
	}

	@Override
	public Iterator<SetEntry<String, Show>> listShowsWithTag(String tag)
			throws NoShowsException, NoTaggedShowsException, NoShowsWithTagException {
		if (shows.isEmpty())
			throw new NoShowsException();
		if (tags.isEmpty())
			throw new NoTaggedShowsException();
		Tag oneTag = getTag(tag);
		if (!oneTag.hasTaggedShows())
			throw new NoShowsWithTagException();

		return oneTag.showsIterator();

	}

}

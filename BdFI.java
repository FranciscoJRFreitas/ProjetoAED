package domain.database;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.SetEntry;
import domain.exceptions.*;
import domain.participations.Participation;
import domain.persons.Person;
import domain.shows.*;
import domain.tags.SetTag;
import domain.tags.Tag;

/**
 * Interface that represents the database system.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public interface BdFI extends Serializable {

	/**
	 * Adds a new person to the system.
	 * 
	 * @param idPerson - Person identifier.
	 * @param year     - Person s year of birth.
	 * @param mail     - Person s mail.
	 * @param phoneNum - Person s phone number.
	 * @param gender   - Person s gender.
	 * @param name     - Person s name.
	 * @throws InvalidYearException              - Exception thrown when the person
	 *                                           registration year is invalid.
	 * @throws InvalidGenderInformationException - Exception thrown when the person
	 *                                           registration gender is invalid.
	 * @throws IdPersonAlreadyExistException     - Exception thrown when the person
	 *                                           already exists in the system.
	 */
	void addPerson(String idPerson, int year, String mail, String phoneNum, String gender, String name)
			throws InvalidYearException, InvalidGenderInformationException, IdPersonAlreadyExistException;

	/**
	 * Adds a new show to the system.
	 * 
	 * @param idShow         - Show identifier.
	 * @param productionYear - Show s production year.
	 * @param title          - Show s title.
	 * @throws InvalidYearException        - Exception thrown when the show
	 *                                     registration year is invalid.
	 * @throws IdShowAlreadyExistException - Exception thrown when the show already
	 *                                     exists in the system.
	 */
	void addShow(String idShow, int productionYear, String title)
			throws InvalidYearException, IdShowAlreadyExistException;

	/**
	 * Adds a new participation to the system. It is required that a show and a
	 * person exist in the system to add a new participation.
	 * 
	 * @param idPerson    - Person identifier.
	 * @param idShow      - Show identifier.
	 * @param description - Participation s description.
	 * @throws IdPersonDoesNotExistException - Exception thrown when the person does
	 *                                       not exist in the system.
	 * @throws IdShowDoesNotExistException   - Exception thrown when the show does
	 *                                       not exist in the system.
	 */
	void addParticipation(String idPerson, String idShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException;

	/**
	 * Terminates the production of the given show. When this command is applied
	 * successfully, the show will be no longer in production.
	 * 
	 * @param idShow - Show identifier.
	 * @throws IdShowDoesNotExistException         - Exception thrown when the
	 *                                             person does not exist in the
	 *                                             system.
	 * @throws ShowHasCompletedProductionException - Exception thrown when the show
	 *                                             has already completed its
	 *                                             production.
	 */
	void premiere(String idShow) throws IdShowDoesNotExistException, ShowHasCompletedProductionException;

	/**
	 * Removes an existing show from the system.
	 * 
	 * @param idShow - Show identifier.
	 * @throws IdShowDoesNotExistException         - Exception thrown when the show
	 *                                             does not exist in the system.
	 * @throws ShowHasCompletedProductionException - Exception thrown when the show
	 *                                             has already completed its
	 *                                             production.
	 */
	void removeShow(String idShow) throws IdShowDoesNotExistException, ShowHasCompletedProductionException;

	/**
	 * Adds a tag to a show. The show can have more than one different tag.
	 * 
	 * @param idShow - Show identifier.
	 * @param tag    - Tag name.
	 * @throws IdShowDoesNotExistException - Exception thrown when the show does not
	 *                                     exist in the system.
	 */
	void tagShow(String idShow, String tag) throws IdShowDoesNotExistException;

	/**
	 * Returns the show by the given identifier.
	 * 
	 * @param idShow - Show identifier.
	 * @return - Show object by the given id.
	 * @throws IdShowDoesNotExistException - Exception thrown when the show does not
	 *                                     exist in the system.
	 * 
	 */
	Show getShow(String idShow) throws IdShowDoesNotExistException;

	/**
	 * Returns the person by the given identifier.
	 * 
	 * @param idPerson - Person identifier.
	 * @return - Person object by the given id.
	 * @throws IdPersonDoesNotExistException - Exception thrown when the person does
	 *                                       not exist in the system.
	 * 
	 */
	Person getPerson(String idPerson) throws IdPersonDoesNotExistException;

	/**
	 * Returns the tag by the given tag name.
	 * 
	 * @param tag - tag Name.
	 * @return - tag by the given tag name.
	 * @throws NoShowsWithTagException - Exception thrown when there are no tagged
	 *                                 shows with the given tag.
	 */
	Tag getTag(String tag) throws NoShowsWithTagException;

	/**
	 * 
	 * Applies a rating to the given show.
	 * 
	 * @param stars  - 0 to 10 classification given to the show.
	 * @param idShow - Show identifier.
	 * @throws InvalidRatingException      - Exception thrown when the rating is not
	 *                                     valid.
	 * @throws IdShowDoesNotExistException - Exception thrown when the person does
	 *                                     not exist in the system.
	 * @throws ShowInProductionException   - Exception thrown when the show is still
	 *                                     in production.
	 */
	void rateShow(int stars, String idShow)
			throws InvalidRatingException, IdShowDoesNotExistException, ShowInProductionException;

	/**
	 * Lists all tags of a show by its given id.
	 * 
	 * @param idShow - Show identifier.
	 * @return - String iterator with all the show tags.
	 * @throws IdShowDoesNotExistException - Exception thrown when the person does
	 *                                     not exist in the system.
	 */
	Iterator<String> tagsIterator(String idShow) throws IdShowDoesNotExistException;

	/**
	 * Returns the person s shows that the person with the given id has participated
	 * in.
	 * 
	 * @param idPerson - Show identifier.
	 * @return - The shows the person has participated in.
	 * @throws IdPersonDoesNotExistException - Exception thrown when the person does
	 *                                       not exist in the system.
	 * @throws PersonHasNoShowsException     - Exception thrown when the person has
	 *                                       not participated in any shows.
	 */
	Iterator<SetEntry<String, Show>> getPersonShows(String idPerson) throws IdPersonDoesNotExistException, PersonHasNoShowsException;

	/**
	 * Lists all participations of a show by its given id.
	 * 
	 * @param idShow - Show identifier.
	 * @return - Participation iterator with all the participations.
	 * @throws IdShowDoesNotExistException      - Exception thrown when the person
	 *                                          does not exist in the system.
	 * @throws ShowHasNoParticipationsException - Exception thrown when the show has
	 *                                          no participations.
	 */
	Iterator<Participation> participationsIterator(String idShow)
			throws IdShowDoesNotExistException, ShowHasNoParticipationsException;

	/**
	 * Returns the show with better average rating in the system.
	 * 
	 * @return - the best show in the system.
	 * @throws NoShowsException         - Exception thrown when there are no shows
	 *                                  in the system.
	 * @throws NoFinishedShowsException - Exception thrown when every show is in
	 *                                  production.
	 * @throws NoRatedShowsException    - Exception thrown when there are no rated
	 *                                  shows.
	 */
	Iterator<SetEntry<String, Show>> getBestShows()
			throws NoShowsException, NoFinishedShowsException, NoRatedShowsException;

	/**
	 * Returns the shows with the given rating.
	 * 
	 * @param rate - 0 to 10 classification.
	 * @return - show with the requested rating.
	 * @throws InvalidRatingException     - Exception thrown when the rating is not
	 *                                    valid.
	 * @throws NoShowsException           - Exception thrown when there are no shows
	 *                                    in the system.
	 * @throws NoFinishedShowsException   - Exception thrown when every show is in
	 *                                    production.
	 * @throws NoRatedShowsException      - Exception thrown when there are no rated
	 *                                    shows.
	 * @throws NoRequestedRatingException - Exception thrown when there are no shows
	 *                                    with the requested average rating.
	 */
	Iterator<SetEntry<String, Show>> getRatedShows(int rate) throws InvalidRatingException, NoShowsException,
			NoFinishedShowsException, NoRatedShowsException, NoRequestedRatingException;

	/**
	 * Lists all shows with the requested tag.
	 * 
	 * @param tag - Tag name.
	 * @return - show with the given tag.
	 * @throws NoShowsException        - Exception thrown when there are no shows in
	 *                                 the system.
	 * @throws NoTaggedShowsException  - Exception thrown when there are no tagged
	 *                                 shows.
	 * @throws NoShowsWithTagException - Exception thrown when there are no tagged
	 *                                 shows with the given tag.
	 */
	Iterator<SetEntry<String, Show>> listShowsWithTag(String tag)
			throws NoShowsException, NoTaggedShowsException, NoShowsWithTagException;

}

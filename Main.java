import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dataStructures.*;
import domain.database.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.participations.*;
import domain.persons.*;
import domain.shows.*;
import domain.tags.SetTag;
import domain.tags.Tag;

/**
 ** 
 * Main class for the application. Containing all the communication between the
 * user and the application.
 * 
 * AED 2021 - First Project - BdFI. Estou a alterar o projeto
 * 
 * @author Danny Fernandes 61162
 * @author Francisco Freitas 60313
 */
public class Main {

	// General system messages of performed operations
	// Person related messages
	private static final String PERSON_ADDED = "Person added.";
	private static final String PERSON_INFO = "%s %s %d %s %s %s\n";
	// Show related messages
	private static final String SHOW_ADDED = "Show added.";
	private static final String SUCCESSFUL_PRODUCTION = "Successful production.";
	private static final String SHOW_REMOVED = "Show removed.";
	private static final String SHOW_INFO = "%s %s %d %d\n";
	// Participation related messasges
	private static final String PARTICIPATION_ADDED = "Participation added.";
	private static final String PARTICIPATIONS_INFO = "%s %s %d %s %s %s %s\n";
	// Tag related messages
	private static final String TAG_ADDED = "Tag added.";
	// Rating related messages
	private static final String RATING_APPLIED = "Rating applied.";
	// Quitting related messages
	private static final String QUIT = "Serializing and quitting...\n";

	// General system error messages
	// Person related messages
	private static final String INVALID_YEAR = "Invalid year.";
	private static final String IDP_EXISTS = "idPerson exists.";
	private static final String IDP_NOT_EXIST = "idPerson does not exist.";
	private static final String PERSON_HAS_NO_SHOWS = "idPerson has no shows.";
	private static final String INVALID_GENDER = "Invalid gender information.";
	// Show related messages
	private static final String IDS_EXISTS = "idShow exists.";
	private static final String IDS_NOT_EXISTS = "idShow does not exist.";
	private static final String SHOW_IN_PRODUCTION = "idShow is in production.";
	private static final String NO_RATED_SHOWS = "No rated productions.";
	private static final String NO_SHOWS = "No shows.";
	private static final String SHOW_HAS_NO_PARTICIPATIONS = "idShow has no participations.";
	private static final String IDS_HAS_COMPLETED_PRODUCTIONS = "idShow has already completed production.";
	private static final String NO_FINISHED_PRODUCTIONS = "No finished productions.";
	// Tag related messages
	private static final String NO_TAGGED_SHOWS = "No tagged productions.";
	private static final String NO_SHOWS_WITH_TAG = "No shows with tag.";

	// Rating related messages
	private static final String INVALID_RATING = "Invalid Rating.";
	private static final String NO_SHOWS_WITH_RATING = "No productions with rating.";

	// File related constants
	private static final String DATA_FILE = "storedBD.dat";
	private static final String FILE_ERROR = "Error: %s\n";

	/**
	 * Main method that initiates the <code>program</code>.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BdFI iMDb = load();
		Main.commands(in, iMDb);
	}

	/**
	 * Returns the user input Command with the chosen command. The commands are case
	 * insensitive in this application, so in the next method is necessary an
	 * equalsIgnoreCase to ignore the case.
	 * 
	 * @param in - Scanner object.
	 * @return - Command with the chosen command.
	 */
	private static Command getCommand(Scanner in) {
		String input = in.next().toLowerCase().trim();
		for (Command c : Command.values())
			if (c.getCommandInfo().equalsIgnoreCase(input))
				return c;
		return Command.NULL;
	}

	/**
	 * Returns the user input String with the chosen gender. The gender inputs are
	 * case insensitive in this application, so in the next method is necessary an
	 * equalsIgnoreCase to ignore the case.
	 * 
	 * @param in - Scanner object.
	 * @return - Gender with the selected gender.
	 */
	private static String getGender(Scanner in) {
		String input = in.next().trim();
		for (Gender g : Gender.values())
			if (g.getGenderInfo().equalsIgnoreCase(input.toLowerCase()))
				return input;
		return Gender.INVALID.getGenderInfo();
	}

	/**
	 * Selects which Command to use and which methods are executed. The while keeps
	 * the cycle running with a switch (to select the option), until the user choses
	 * to quit.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void commands(Scanner in, BdFI iMDb) {
		Command comm = getCommand(in);
		while (!comm.equals(Command.QUIT)) {
			switch (comm) {
			case ADD_PERSON:
				addPerson(in, iMDb);
				break;
			case ADD_SHOW:
				addShow(in, iMDb);
				break;
			case ADD_PARTICIPATION:
				addParticipation(in, iMDb);
				break;
			case PREMIERE:
				premiere(in, iMDb);
				break;
			case REMOVE_SHOW:
				removeShow(in, iMDb);
				break;
			case TAG_SHOW:
				tagShow(in, iMDb);
				break;
			case SHOW_INFO:
				showInfo(in, iMDb);
				break;
			case RATE_SHOW:
				rateShow(in, iMDb);
				break;
			case PERSON_INFO:
				personInfo(in, iMDb);
				break;
			case LIST_PERSON_SHOWS:
				listShowsOfPerson(in, iMDb);
				break;
			case LIST_PARTICIPATIONS:
				listParticipations(in, iMDb);
				break;
			case BEST_SHOWS:
				listBestShows(in, iMDb);
				break;
			case LIST_SHOWS:
				listShowsWithTheRating(in, iMDb);
				break;
			case LIST_TAGGED_SHOWS:
				listShowsWithTheTag(in, iMDb);
				break;
			default:
				break;
			}

			System.out.println();
			comm = getCommand(in);
		}
		quit(iMDb);
		in.close();
	}

	/**
	 * Adds a new person to the BdFI system.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void addPerson(Scanner in, BdFI iMDb) {
		try {
			String idPerson = in.next();
			int birthYear = in.nextInt();
			String mail = in.next();
			String phoneNum = in.next();
			String gender = getGender(in);
			String name = in.nextLine().trim();
			iMDb.addPerson(idPerson, birthYear, mail, phoneNum, gender, name);
			System.out.println(PERSON_ADDED);
		} catch (InvalidYearException e) {
			System.out.println(INVALID_YEAR);
		} catch (InvalidGenderInformationException e) {
			System.out.println(INVALID_GENDER);
		} catch (IdPersonAlreadyExistException e) {
			System.out.println(IDP_EXISTS);
		}

	}

	/**
	 * Adds a new show to the BdFI system.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void addShow(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.next();
			int productionYear = in.nextInt();
			String title = in.nextLine().trim();
			iMDb.addShow(idShow, productionYear, title);
			System.out.println(SHOW_ADDED);
		} catch (InvalidYearException e) {
			System.out.println(INVALID_YEAR);
		} catch (IdShowAlreadyExistException e) {
			System.out.println(IDS_EXISTS);
		}

	}

	/**
	 * Adds a new participation to the BdFI system.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void addParticipation(Scanner in, BdFI iMDb) {
		try {
			String idPerson = in.next();
			String idShow = in.next();
			String description = in.nextLine().trim();
			iMDb.addParticipation(idPerson, idShow, description);
			System.out.println(PARTICIPATION_ADDED);
		} catch (IdPersonDoesNotExistException e) {
			System.out.println(IDP_NOT_EXIST);
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		}
	}

	/**
	 * Terminates the production of the given show. When this command is applied
	 * successfully, the show will be no longer in production.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void premiere(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.nextLine().trim();
			iMDb.premiere(idShow);
			System.out.println(SUCCESSFUL_PRODUCTION);
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		} catch (ShowHasCompletedProductionException e) {
			System.out.println(IDS_HAS_COMPLETED_PRODUCTIONS);
		}
	}

	/**
	 * Removes an existing show from the BdFI system.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void removeShow(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.nextLine().trim();
			iMDb.removeShow(idShow);
			System.out.println(SHOW_REMOVED);
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		} catch (ShowHasCompletedProductionException e) {
			System.out.println(IDS_HAS_COMPLETED_PRODUCTIONS);
		}
	}

	/**
	 * Adds a tag to a show. The show can have many different tags.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void tagShow(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.next();
			String tag = in.nextLine().trim();
			iMDb.tagShow(idShow, tag);
			System.out.println(TAG_ADDED);
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		}
	}

	/**
	 * Lists the given show information. It also shows every tag associated to the
	 * show.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void showInfo(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.next().trim();
			Show s = iMDb.getShow(idShow);
			System.out.printf(SHOW_INFO, s.getIdShow(), s.getShowTitle(), s.getProductionYear(), s.getShowAverage());

			Iterator<String> it = iMDb.tagsIterator(idShow);
			while (it.hasNext()) {
				String tag = it.next();
				System.out.println(tag);
			}
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		}

	}

	/**
	 * Applies a rating to the given show.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void rateShow(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.next();
			int stars = in.nextInt();
			in.nextLine();
			iMDb.rateShow(stars, idShow);
			System.out.println(RATING_APPLIED);
		} catch (InvalidRatingException e) {
			System.out.println(INVALID_RATING);
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		} catch (ShowInProductionException e) {
			System.out.println(SHOW_IN_PRODUCTION);
		}
	}

	/**
	 * Lists the given person information.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void personInfo(Scanner in, BdFI iMDb) {
		try {
			String idPerson = in.nextLine().trim();
			Person p = iMDb.getPerson(idPerson);
			System.out.printf(PERSON_INFO, p.getIdPerson(), p.getName(), p.getBirthYear(), p.getMail(),
					p.getPhoneNumber(), p.getGender());
		} catch (IdPersonDoesNotExistException e) {
			System.out.println(IDP_NOT_EXIST);
		}
	}

	/**
	 * Lists every show the person has participated in.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void listShowsOfPerson(Scanner in, BdFI iMDb) {
		try {
			String idPerson = in.nextLine().trim();
			Iterator<SetEntry<String, Show>> it = iMDb.getPersonShows(idPerson);
			while (it.hasNext()) {
				Show s = it.next().getValue();
				System.out.printf(SHOW_INFO, s.getIdShow(), s.getShowTitle(), s.getProductionYear(),
						s.getShowAverage());
			}
		} catch (IdPersonDoesNotExistException e) {
			System.out.println(IDP_NOT_EXIST);
		} catch (PersonHasNoShowsException e) {
			System.out.println(PERSON_HAS_NO_SHOWS);
		}
	}

	/**
	 * Lists every participation of a show.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void listParticipations(Scanner in, BdFI iMDb) {
		try {
			String idShow = in.nextLine().trim();
			Iterator<Participation> it = iMDb.participationsIterator(idShow);
			while (it.hasNext()) {
				Participation theParticipation = it.next();
				Person theProfessional = theParticipation.getPerson();
				System.out.printf(PARTICIPATIONS_INFO, theProfessional.getIdPerson(), theProfessional.getName(),
						theProfessional.getBirthYear(), theProfessional.getMail(), theProfessional.getPhoneNumber(),
						theProfessional.getGender(), theParticipation.getDescription());
			}
		} catch (IdShowDoesNotExistException e) {
			System.out.println(IDS_NOT_EXISTS);
		} catch (ShowHasNoParticipationsException e) {
			System.out.println(SHOW_HAS_NO_PARTICIPATIONS);
		}
	}

	/**
	 * Lists the best shows in the system, based on their rating.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void listBestShows(Scanner in, BdFI iMDb) {
		try {
			Iterator<SetEntry<String, Show>> it = iMDb.getBestShows();
			while (it.hasNext()) {
				SetEntry<String, Show> entry = it.next();
				Show s = entry.getValue();
				System.out.printf(SHOW_INFO, s.getIdShow(), s.getShowTitle(), s.getProductionYear(),
						s.getShowAverage());
			}
		} catch (NoShowsException e) {
			System.out.println(NO_SHOWS);
		} catch (NoFinishedShowsException e) {
			System.out.println(NO_FINISHED_PRODUCTIONS);
		} catch (NoRatedShowsException e) {
			System.out.println(NO_RATED_SHOWS);
		}

	}

	/**
	 * Lists every show with the given average rating.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void listShowsWithTheRating(Scanner in, BdFI iMDb) {
		try {
			int rating = in.nextInt();
			in.nextLine();
			Iterator<SetEntry<String, Show>> it = iMDb.getRatedShows(rating);
			while (it.hasNext()) {
				SetEntry<String, Show> entry = it.next();
				Show s = entry.getValue();
				System.out.printf(SHOW_INFO, s.getIdShow(), s.getShowTitle(), s.getProductionYear(),
						s.getShowAverage());
			}
		} catch (InvalidRatingException e) {
			System.out.println(INVALID_RATING);
		} catch (NoShowsException e) {
			System.out.println(NO_SHOWS);
		} catch (NoFinishedShowsException e) {
			System.out.println(NO_FINISHED_PRODUCTIONS);
		} catch (NoRatedShowsException e) {
			System.out.println(NO_RATED_SHOWS);
		} catch (NoRequestedRatingException e) {
			System.out.println(NO_SHOWS_WITH_RATING);
		}

	}

	/**
	 * Lists every show with the given tag.
	 * 
	 * @param in   - Scanner object.
	 * @param iMDb - BdFI object.
	 */
	private static void listShowsWithTheTag(Scanner in, BdFI iMDb) {
		try {
			String tag = in.nextLine().trim();
			Iterator<SetEntry<String, Show>> it = iMDb.listShowsWithTag(tag);
			while (it.hasNext()) {
				Show taggedShow = it.next().getValue();
				System.out.printf(SHOW_INFO, taggedShow.getIdShow(), taggedShow.getShowTitle(),
						taggedShow.getProductionYear(), taggedShow.getShowAverage());
			}
		} catch (NoShowsException e) {
			System.out.println(NO_SHOWS);
		} catch (NoTaggedShowsException e) {
			System.out.println(NO_TAGGED_SHOWS);
		} catch (NoShowsWithTagException e) {
			System.out.println(NO_SHOWS_WITH_TAG);
		}
	}

	/**
	 * Saves the object and its information in a file and exits the program.
	 * 
	 * @param iMDb - BdFI object.
	 */
	private static void quit(BdFI iMDb) {
		System.out.println(QUIT);
		save(iMDb);
	}

	/**
	 * Loads the program saved beforehand or creates a new one, in case that is has
	 * not been saved or it does not exist in the right folder.
	 * 
	 * @return - The BdFI object.
	 */
	private static BdFI load() {
		BdFI object = null;
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(DATA_FILE));
			object = (BdFI) file.readObject();
			file.close();
		} catch (FileNotFoundException e) {
			return new BdFIClass();
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf(FILE_ERROR, e.getMessage());
		}
		return (BdFI) object;
	}

	/**
	 * Saves the object in a file.
	 * 
	 * @param iMDb - BdFI object.
	 */
	private static void save(BdFI iMDb) {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
			file.writeObject(iMDb);
			file.flush();
			file.close();

		} catch (IOException e) {
			System.out.printf(FILE_ERROR, e.getMessage());
		}
	}
}

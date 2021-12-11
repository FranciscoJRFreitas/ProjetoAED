package domain.enums;



/**
 * Enum with all the user s commands in the application.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public enum Command {

	ADD_PERSON("addperson"), ADD_SHOW("addshow"), ADD_PARTICIPATION("addparticipation"), PREMIERE("premiere"),
	REMOVE_SHOW("removeshow"), TAG_SHOW("tagshow"), SHOW_INFO("infoshow"), RATE_SHOW("rateshow"),
	PERSON_INFO("infoperson"), LIST_PERSON_SHOWS("listshowsperson"), LIST_PARTICIPATIONS("listParticipations"),
	BEST_SHOWS("listbestshows"), LIST_SHOWS("listshows"), LIST_TAGGED_SHOWS("listtaggedshows"), QUIT("quit"), NULL("");

	private final String commandInfo;

	private Command(String commandInfo) {
		this.commandInfo = commandInfo;
	}

	public String getCommandInfo() {
		return commandInfo;
	}

}

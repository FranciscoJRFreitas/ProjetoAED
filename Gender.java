package domain.enums;


/**
 * Enum that represents the different gender options the person has
 * for registration.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public enum Gender {

	FEMALE("female"), MALE("male"), OTHER("other"), NOT_PROVIDED("not-provided"), INVALID("invalid");

	private final String gender;

	private Gender(String gender) {
		this.gender = gender;
	}

	public String getGenderInfo() {
		return gender;
	}
}

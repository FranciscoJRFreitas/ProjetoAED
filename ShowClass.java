package domain.shows;

import dataStructures.Iterator;
import domain.participations.*;
import domain.persons.SetPerson;

import java.time.Year;

import dataStructures.*;

/**
 * Class that represents the object show and its information.
 * 
 * @author Danny Fernandes / Francisco Freitas
 *
 */
public class ShowClass implements SetShow {

	// Class constants

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	// Class variables

	/**
	 * String with the show identifier.
	 */
	private String idShow;

	/**
	 * String with the show title.
	 */
	private String showTitle;

	/**
	 * int with the show s production year.
	 */
	private int productionYear;

	/**
	 * int with the average show rating.
	 */
	private int showAverage;

	/**
	 * int with the show rating counter (how many times the show has been rated).
	 */
	private int rateCounter;

	/**
	 * boolean with the show production status (in production or not).
	 */
	private boolean isInProduction;

	/**
	 * List with all the tags associated to the show.
	 */
	private List<String> tags;

	/**
	 * List with all current participations.
	 */
	private List<Participation> participations;
	
	private List<SetPerson> persons;

	/**
	 * ShowClass constructor.
	 * 
	 * @param idShow         - show identifier.
	 * @param showTitle      - show s title.
	 * @param productionYear - show s production year.
	 */
	public ShowClass(String idShow, String showTitle, int productionYear) {
		this.idShow = idShow;
		this.showTitle = showTitle;
		this.productionYear = productionYear;
		showAverage = 0;
		isInProduction = isInProduction(productionYear);
		tags = new DoubleList<String>();
		participations = new DoubleList<Participation>();
		persons=new DoubleList<SetPerson>();
		rateCounter = 0;
	}

	/**
	 * Defines the variable isInProduction. The current year has to be the same as
	 * the show s production year.
	 * 
	 * @param productionYear - show s production year.
	 * @return -<code>true</code>, if the show is in production; <code>false</code>,
	 *         otherwise.
	 */
	private boolean isInProduction(int productionYear) {
		return productionYear == Year.now().getValue();
	}
	
	private boolean hasTag(String tag) {
		return tags.find(tag) == -1;
	}

	@Override
	public String getIdShow() {
		return idShow;
	}

	@Override
	public String getShowTitle() {
		return showTitle;
	}

	@Override
	public int getProductionYear() {
		return productionYear;
	}

	@Override
	public int getShowAverage() {
		return showAverage;
	}

	@Override
	public boolean isInProduction() {
		return isInProduction;
	}

	@Override
	public void premiere() {
		isInProduction = false;
	}

	@Override
	public void rate(int stars) {
		showAverage = Math.round((float) ((stars + rateCounter * showAverage) / ((float) (rateCounter + 1))));
		rateCounter++;
	}

	@Override
	public boolean isRated() {
		return rateCounter > 0;
	}

	@Override
	public void addTag(String tag) {
		if (hasTag(tag))
			tags.addLast(tag);
	}

	@Override
	public boolean hasAnyTag() {
		return !(tags.isEmpty());
	}

	@Override
	public Iterator<String> tagIterator() {
		return tags.iterator();
	}
	
	@Override
	public Iterator<SetPerson> personIterator() {
		return persons.iterator();
	}

	@Override
	public void addParticipation(Participation oneParticipation) {
		participations.addLast(oneParticipation);
		persons.addLast((SetPerson) oneParticipation.getPerson());
	}
	

	@Override
	public boolean hasAnyParticipation() {
		return !(participations.isEmpty());
	}

	@Override
	public Iterator<Participation> participationsIterator() {
		return participations.iterator();
	}

	
	
	
	


}

package domain.tags;

import dataStructures.Comparator;
import domain.shows.Show;

public class CompareByTitle implements Comparator<Show> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Show e1, Show e2) {
		return e1.getShowTitle().compareTo(e2.getShowTitle());
	}

}

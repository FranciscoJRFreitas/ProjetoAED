package domain.tags;

import domain.shows.Show;

public interface SetTag extends Tag {

	void addShowWithTag(Show show);

	void removeShow(String showTitle);
}

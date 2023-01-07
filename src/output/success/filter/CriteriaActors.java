package output.success.filter;

import input.movie.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class CriteriaActors implements Criteria {
    private final ArrayList<String> actors;

    public CriteriaActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    @Override
    public ArrayList<Movie> meetCriteria(final ArrayList<Movie> movies) {
        return (ArrayList<Movie>) movies.stream().filter(this::containsActors)
                                     .collect(Collectors.toList());
    }

    private boolean containsActors(final Movie movie) {
        return movie.getActors().containsAll(actors);
    }
}

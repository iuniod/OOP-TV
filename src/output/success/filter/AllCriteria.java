package output.success.filter;

import input.movie.Movie;

import java.util.ArrayList;

public final class AllCriteria implements Criteria {
    private final ArrayList<Criteria> criteria;

    public AllCriteria(final ArrayList<Criteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public ArrayList<Movie> meetCriteria(final ArrayList<Movie> movies) {
        ArrayList<Movie> filteredMovies = movies;

        for (Criteria criterion : criteria) {
            filteredMovies = criterion.meetCriteria(filteredMovies);
        }

        return filteredMovies;
    }
}

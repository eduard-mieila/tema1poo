package databases;

import fileio.MovieInputData;

import java.util.HashMap;


public final class MovieData extends VideoData {
    private int duration;
    private HashMap<String, Double> ratings;

    public MovieData(final MovieInputData movie) {
        super(movie);
        this.duration = movie.getDuration();
        this.ratings = new HashMap<String, Double>();
    }

    public int getDuration() {
        return duration;
    }

    public HashMap<String, Double> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "MovieData{" + super.toString()
                + "duration=" + duration + "} ";
    }
}

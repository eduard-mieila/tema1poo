package databases;

import fileio.ShowInput;

import java.util.ArrayList;

public class VideoData {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private int addedToFavorite;
    private double overallRating;

    public VideoData(final ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
        this.addedToFavorite = 0;
        this.overallRating = 0;
    }

    /**
     *
     * @return overallRating
     */
    public double getOverallRating() {
        return overallRating;
    }

    /**
     *
     * @param overallRating value of overallRating
     */
    public void setOverallRating(final double overallRating) {
        this.overallRating = overallRating;
    }

    /**
     *
     * @return title of this video
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return year of the video
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @return list of cast for the video
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     *
     * @return list of genres for the video
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     *
     * @return value of addedToFavorite
     */
    public int getAddedToFavorite() {
        return addedToFavorite;
    }


    /**
     *
     * @param addedToFavorite value of addedToFavorite
     */
    public void setAddedToFavorite(final int addedToFavorite) {
        this.addedToFavorite = addedToFavorite;
    }


    /**
     *
     * @return VideoData details
     */
    @Override
    public String toString() {
        return "VideoData{"
                + "title='" + title + '\''
                + ", year=" + year
                + ", cast=" + cast
                + ", genres=" + genres
                + ", addedToFavorite = " + addedToFavorite
                + '}';
    }
}

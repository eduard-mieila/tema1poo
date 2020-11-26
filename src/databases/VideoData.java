package databases;

import fileio.ShowInput;

import java.util.ArrayList;

public class VideoData {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public VideoData(final ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
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
     * @return VideoData details
     */
    @Override
    public String toString() {
        return "VideoData{"
                + "title='" + title + '\''
                + ", year=" + year
                + ", cast=" + cast
                + ", genres=" + genres
                + '}';
    }
}

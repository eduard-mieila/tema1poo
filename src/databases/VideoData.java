package databases;

import java.util.ArrayList;

public class VideoData {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}

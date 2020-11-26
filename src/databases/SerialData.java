package databases;

import entertainment.Season;
import fileio.SerialInputData;
import fileio.ShowInput;

import java.util.ArrayList;

public class SerialData extends VideoData {
    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    private double rating;
    private int numberOfRatings;

    public SerialData(SerialInputData serial) {
        super(serial);
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    @Override
    public String toString() {
        return "SerialData{" + super.toString() +
                "numberOfSeasons=" + numberOfSeasons +
                ", seasons=" + seasons +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                "} ";
    }
}
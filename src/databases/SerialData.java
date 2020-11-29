package databases;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public final class SerialData extends VideoData {
    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    private int viewed;
    private int duration;

    public SerialData(final SerialInputData serial) {
        super(serial);
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
        this.viewed = 0;
        this.duration = 0;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(final int viewed) {
        this.viewed = viewed;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "SerialData{" + super.toString()
                + "numberOfSeasons=" + numberOfSeasons
                + ", seasons=" + seasons
                + "} ";
    }
}

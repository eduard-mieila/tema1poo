package databases;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public final class SerialData extends VideoData {
    private int numberOfSeasons;
    private ArrayList<Season> seasons;

    public SerialData(final SerialInputData serial) {
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

    @Override
    public String toString() {
        return "SerialData{" + super.toString()
                + "numberOfSeasons=" + numberOfSeasons
                + ", seasons=" + seasons
                + "} ";
    }
}

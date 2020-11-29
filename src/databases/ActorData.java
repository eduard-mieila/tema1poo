package databases;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public final class ActorData {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private double rating;
    private int numberOfAwards;

    public ActorData(final ActorInputData data) {
        this.name = data.getName();
        this.careerDescription = data.getCareerDescription();
        this.filmography = data.getFilmography();
        this.awards = data.getAwards();
        this.rating = 0;
        this.numberOfAwards = 0;
    }

    public int getNumberOfAwards() {
        return numberOfAwards;
    }

    public void setNumberOfAwards(final int numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    @Override
    public String toString() {
        return "ActorData{" + "name='" + name + '\''
                + ", careerDescription='" + careerDescription + '\''
                + ", filmography=" + filmography
                + ", awards=" + awards + "}";
    }
}

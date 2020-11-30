package databases;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

public final class UserData {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favoriteMovies;
    private int numberOfReviews;

    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public UserData(final UserInputData user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
        this.numberOfReviews = 0;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(final int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return "UserData{"
                + "username='" + username + '\''
                + ", subscriptionType='" + subscriptionType + '\''
                + ", history=" + history
                + ", favoriteMovies=" + favoriteMovies
                + '}';
    }
}

package databases;


import entertainment.Season;

import java.util.ArrayList;

public final class MyDatabase {
    private ArrayList<ActorData> actors = new ArrayList<ActorData>();
    private ArrayList<MovieData> movies = new ArrayList<MovieData>();
    private ArrayList<SerialData> serials = new ArrayList<SerialData>();
    private ArrayList<UserData> users = new ArrayList<UserData>();

    /**
     *
     * @param actor to be added to db
     */
    public void add(final ActorData actor) {
        actors.add(actor);
    }

    /**
     *
     * @param movie to be added to db
     */
    public void add(final MovieData movie) {
        movies.add(movie);
    }

    /**
     *
     * @param serial to be added to db
     */
    public void add(final SerialData serial) {
        serials.add(serial);
    }

    /**
     *
     * @param user to be added to db
     */
    public void add(final UserData user) {
        users.add(user);
    }

    /**
     *
     * @param userName user to be searched in the db
     * @param db the database where we search
     * @return user if found, else null
     */
    public UserData getUser(final String userName, final MyDatabase db) {
        for (UserData user : db.getUsers()) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param title Title of the movie we are searching
     * @param db the database where we search
     * @return movie if found, else null
     */
    public MovieData searchMovie(final String title, final MyDatabase db) {
        for (MovieData movie : db.getMovies()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     *
     * @param title Title of the serial we are searching
     * @param db the database where we search
     * @return serial if found, else null
     */
    public SerialData searchSerial(final String title, final MyDatabase db) {
        for (SerialData serial : db.getSerials()) {
            if (serial.getTitle().equals(title)) {
                return serial;
            }
        }
        return null;
    }

    /**
     *
     * @param user to which we update favorite list
     * @param title Video that we will add to list
     * @return message success/fail to be written in JSONArray at output
     */
    public String view(final UserData user, final String title) {
        if (user != null) {
            if (user.getHistory().containsKey(title)) {
                user.getHistory().put(title, user.getHistory().get(title) + 1);
                return "success -> " + title + " was viewed with total views of "
                        + user.getHistory().get(title);
            } else {
                user.getHistory().put(title, 1);
                return "success -> " + title + " was viewed with total views of "
                        + user.getHistory().get(title);
            }
        }
        return "FATAL ERROR!";
    }

    /**
     *
     * @param user to which we update favorite list
     * @param title Video that we will add to list
     * @return message success/fail to be written in JSONArray at output
     */
    public String addFavorite(final UserData user, final String title) {
        if (user != null) {
            if (user.getHistory().containsKey(title)) {
                if (user.getFavoriteMovies().contains(title)) {
                    return "error -> " + title + " is already in favourite list";
                } else {
                    user.getFavoriteMovies().add(title);
                    return "success -> " + title + " was added as favourite";
                }
            } else {
                return "error -> " + title + " is not seen";
            }
        }
        return "FATAL ERROR!";
    }

    /**
     * Applies to Movies only
     * @param user user that gives a grade
     * @param movie movie that is graded
     * @param grade value that is applied
     * @return message success/fail to be written in JSONArray at output
     */
    public String rate(final UserData user, final MovieData movie, final double grade) {
        if (movie.getRatings().containsKey(user.getUsername())) {
            return "error -> " + movie.getTitle() + " has been already rated";
        } else {
            movie.getRatings().put(user.getUsername(), grade);
            return "success -> " + movie.getTitle() + " was rated with " + grade + " by "
                    + user.getUsername();
        }
    }

    /**
     * Applies to Serials only
     * @param user user that gives a grade
     * @param serial serial that is graded
     * @param seasonNumber season of the serial that is graded
     * @param grade value that is applied
     * @return message success/fail to be written in JSONArray at output
     */
    public String rate(final UserData user, final SerialData serial, final int seasonNumber,
                       final double grade) {
        Season season = serial.getSeasons().get(seasonNumber - 1);
        if (season.getRatings().containsKey(user.getUsername())) {
            return "error -> " + serial.getTitle() + " has been already rated";
        } else {
            season.getRatings().put(user.getUsername(), grade);
            return "success -> " + serial.getTitle() + " was rated with " + grade + " by "
                    + user.getUsername();
        }

    }

    public ArrayList<ActorData> getActors() {
        return actors;
    }

    public ArrayList<MovieData> getMovies() {
        return movies;
    }

    public ArrayList<SerialData> getSerials() {
        return serials;
    }

    public ArrayList<UserData> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "myDatabase{"
                + "actors=" + actors
                + ", movies=" + movies
                + ", serials=" + serials
                + ", users=" + users + '}';
    }
}

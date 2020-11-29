package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import databases.ActorData;
import databases.MovieData;
import databases.MyDatabase;
import databases.SerialData;
import databases.UserData;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        MyDatabase db = new MyDatabase();
        for (ActorInputData actor : input.getActors()) {
            ActorData newActor = new ActorData(actor);
            db.add(newActor);
        }

        for (MovieInputData movie : input.getMovies()) {
            MovieData newMovie = new MovieData(movie);
            db.add(newMovie);
        }

        for (SerialInputData serial : input.getSerials()) {
            SerialData newSerial = new SerialData(serial);
            db.add(newSerial);
        }

        for (UserInputData user : input.getUsers()) {
            UserData newUser = new UserData(user);
            db.add(newUser);
        }

        for (ActionInputData currentAction : input.getCommands()) {
            if (currentAction.getType() != null) {
                if (currentAction.getActionType().equals("command")) {
                    String result = executeCommand(db, currentAction);
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, result));
                }
            }

            if (currentAction.getActionType() != null) {
                if (currentAction.getActionType().equals("query")) {
                    StringBuilder result = executeQuery(db, currentAction);
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, "Query result: [" + result + "]"));
                } else if (currentAction.getActionType().equals("recommendation")) {
                    String result = executeRecommendation(db, currentAction);
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, result));
                }
            }
        }
        fileWriter.closeJSON(arrayResult);
    }

    /**
     * Executes commands according to parameters in currentAction
     * @param db working Database
     * @param currentAction command parameters
     * @return output text
     */
    private static String executeCommand(final MyDatabase db, final ActionInputData currentAction) {
        String result = new String();
        UserData user = db.getUser(currentAction.getUsername());
        if (currentAction.getType().equals("favorite")) {
            result = db.addFavorite(user, currentAction.getTitle());
        }
        if (currentAction.getType().equals("view")) {
            result = db.view(user, currentAction.getTitle());
        }
        if (currentAction.getType().equals("rating")) {
            MovieData movie = db.searchMovie(currentAction.getTitle());
            if (movie != null) {
                result = db.rate(user, movie, currentAction.getGrade());
            } else {
                SerialData serial = db.searchSerial(currentAction.getTitle());
                result = db.rate(user, serial, currentAction.getSeasonNumber(),
                        currentAction.getGrade());
            }
        }
        return result;
    }

    /**
     * Executes queries according to parameters in currentAction
     * @param db working Database
     * @param currentAction query parameters
     * @return output text
     */
    private static StringBuilder executeQuery(final MyDatabase db,
                                                final ActionInputData currentAction) {
        StringBuilder result = new StringBuilder();
        if (currentAction.getCriteria().equals("favorite")) {
            if (currentAction.getObjectType().equals("movies")) {
                result = db.queryFavoriteMovies(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());

            } else if (currentAction.getObjectType().equals("shows")) {
                result = db.queryFavoriteSerials(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            }
        }

        if (currentAction.getCriteria().equals("ratings")) {
            if (currentAction.getObjectType().equals("movies")) {
                result = db.queryRatingMovies(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            } else if (currentAction.getObjectType().equals("shows")) {
                result = db.queryRatingSerials(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            }
        }

        if (currentAction.getCriteria().equals("longest")) {
            if (currentAction.getObjectType().equals("movies")) {
                result = db.queryLongestMovies(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            } else if (currentAction.getObjectType().equals("shows")) {
                result = db.queryLongestSerials(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            }
        }

        if (currentAction.getCriteria().equals("most_viewed")) {
            if (currentAction.getObjectType().equals("movies")) {
                result = db.queryMostViewedMovies(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            } else if (currentAction.getObjectType().equals("shows")) {
                result = db.queryMostViewedSerials(
                        currentAction.getFilters(), currentAction.getSortType(),
                        currentAction.getNumber());
            }
        }

        if (currentAction.getCriteria().equals("filter_description")) {
            result = db.queryDescriptionActors(
                    currentAction.getFilters(), currentAction.getSortType());
        }

        if (currentAction.getCriteria().equals("awards")) {
            result = db.queryAwardsActors(
                    currentAction.getFilters(), currentAction.getSortType());
        }

        if (currentAction.getCriteria().equals("num_ratings")) {
            result = db.queryUsers(currentAction.getSortType(),
                    currentAction.getNumber());
        }

        if (currentAction.getCriteria().equals("average")) {
            result = db.queryAverageActors(currentAction.getSortType(),
                    currentAction.getNumber());
        }
        return result;
    }

    /**
     * Executes recommendations according to parameters in currentAction
     * @param db working Database
     * @param currentAction recommendation parameters
     * @return output text
     */
    private static String executeRecommendation(final MyDatabase db,
                                                final ActionInputData currentAction) {
        StringBuilder result = new StringBuilder();
        if (currentAction.getType().equals("standard")) {
            UserData user = db.getUser(currentAction.getUsername());
            result.append(db.getStandardRecommendation(user));

        }

        if (currentAction.getType().equals("best_unseen")) {
            UserData user = db.getUser(currentAction.getUsername());
            result.append(db.getBestUnseen(user));
        }

        if (currentAction.getType().equals("favorite")) {
            UserData user = db.getUser(currentAction.getUsername());
            result.append(db.getMostFavorite(user));
        }

        if (currentAction.getType().equals("search")) {
            UserData user = db.getUser(currentAction.getUsername());
            result = db.getSearch(user, currentAction.getGenre());
        }

        if (currentAction.getType().equals("popular")) {
            UserData user = db.getUser(currentAction.getUsername());
            result = db.getPopularRecommendation(user);
        }
        return result.toString();
    }





}

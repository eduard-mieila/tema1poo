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

        //TODO add here the entry point to your implementation
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
                if (currentAction.getType().equals("favorite")) {
                    UserData user = db.getUser(currentAction.getUsername(), db);
                    String result = db.addFavorite(user, currentAction.getTitle());
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, result));
                }
                if (currentAction.getType().equals("view")) {
                    UserData user = db.getUser(currentAction.getUsername(), db);
                    String result = db.view(user, currentAction.getTitle());
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, result));
                }
                if (currentAction.getType().equals("rating")) {
                    String result;
                    UserData user = db.getUser(currentAction.getUsername(), db);
                    MovieData movie = db.searchMovie(currentAction.getTitle(), db);
                    if (movie != null) {
                        result = db.rate(user, movie, currentAction.getGrade());
                    } else {
                        SerialData serial = db.searchSerial(currentAction.getTitle(), db);
                        result = db.rate(user, serial, currentAction.getSeasonNumber(),
                                                currentAction.getGrade());
                    }
                    arrayResult.add(fileWriter.writeFile(currentAction.getActionId(),
                            null, result));
                }

            }

        }

        fileWriter.closeJSON(arrayResult);
    }







}

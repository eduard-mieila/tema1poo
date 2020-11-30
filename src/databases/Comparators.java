package databases;

import java.util.Comparator;
import java.util.Map;

final class Comparators {
    public final static class SortByTitle implements Comparator<VideoData> {
        /**
         * Used for sorting by Title
         * @param a video A
         * @param b video B
         * @return Compares two strings lexicographically.
         */
        public int compare(final VideoData a, final VideoData b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

    public final static class SortByName implements Comparator<ActorData> {
        /**
         * Used for sorting Actors by Name
         * @param a video A
         * @param b video B
         * @return Compares two strings lexicographically.
         */
        public int compare(final ActorData a, final ActorData b) {
            return a.getName().compareTo(b.getName());
        }
    }

    public final static class SortByNumberOfAwards implements Comparator<ActorData> {
        /**
         * Used for sorting Actors by number of awards
         * @param a video A
         * @param b video B
         * @return Compares number of awards
         */
        public int compare(final ActorData a, final ActorData b) {
            return a.getNumberOfAwards() - b.getNumberOfAwards();
        }
    }

    public final static class SortByRatingActor implements Comparator<ActorData> {
        /**
         * Used for sorting by Actor's rating
         * @param a video A
         * @param b video B
         * @return Compares ratings
         */
        public int compare(final ActorData a, final ActorData b) {
            return Double.compare(a.getRating(), b.getRating());
        }
    }

    public final static class SortByFavorite implements Comparator<VideoData> {
        /**
         * Used for sorting by addedToFavorite
         * @param a video A
         * @param b video B
         * @return Compares addedToFavorite
         */
        public int compare(final VideoData a, final VideoData b) {
            return a.getAddedToFavorite() - b.getAddedToFavorite();
        }
    }

    public final static class SortByFavoriteDesc implements Comparator<VideoData> {
        /**
         * Used for sorting by addedToFavorite
         * @param a video A
         * @param b video B
         * @return Compares addedToFavorite
         */
        public int compare(final VideoData a, final VideoData b) {
            return b.getAddedToFavorite() - a.getAddedToFavorite();
        }
    }

    public final static class SortByFinalRating implements Comparator<VideoData> {
        /**
         * Used for sorting by addedToFavorite
         * @param a video A
         * @param b video B
         * @return Compares addedToFavorite
         */
        public int compare(final VideoData a, final VideoData b) {
            return Double.compare(a.getOverallRating(), b.getOverallRating());
        }
    }

    public final static class SortByFinalRatingDesc implements Comparator<VideoData> {
        /**
         * Used for sorting by addedToFavorite
         * @param a video A
         * @param b video B
         * @return Compares addedToFavorite
         */
        public int compare(final VideoData a, final VideoData b) {
            return Double.compare(b.getOverallRating(), a.getOverallRating());
        }
    }

    public final static class SortByDurationMovie implements Comparator<MovieData> {
        /**
         * Used for sorting movies by duration
         * @param a video A
         * @param b video B
         * @return Compares duration
         */
        public int compare(final MovieData a, final MovieData b) {
            return a.getDuration() - b.getDuration();
        }
    }

    public final static class SortByDurationSerial implements Comparator<SerialData> {
        /**
         * Used for sorting movies by duration
         * @param a video A
         * @param b video B
         * @return Compares duration
         */
        public int compare(final SerialData a, final SerialData b) {
            return a.getDuration() - b.getDuration();
        }
    }

    public final static class SortByViewedMovie implements Comparator<MovieData> {
        /**
         * Used for sorting movies by times viewed
         * @param a video A
         * @param b video B
         * @return Compares viewed field
         */
        public int compare(final MovieData a, final MovieData b) {
            return a.getViewed() - b.getViewed();
        }
    }

    public final static class SortByViewedSerial implements Comparator<SerialData> {
        /**
         * Used for sorting serials by times viewed
         * @param a video A
         * @param b video B
         * @return compares viewed field
         */
        public int compare(final SerialData a, final SerialData b) {
            return a.getViewed() - b.getViewed();
        }
    }

    public final static class SortByNumberOfReviewsActors implements Comparator<UserData> {
        /**
         * Used for sorting movies by duration
         * @param a video A
         * @param b video B
         * @return compares numberOfReviews
         */
        public int compare(final UserData a, final UserData b) {
            return a.getNumberOfReviews() - b.getNumberOfReviews();
        }
    }

    public final static class SortByUsername implements Comparator<UserData> {
        /**
         * Used for sorting movies by duration
         * @param a video A
         * @param b video B
         * @return Compares two strings lexicographically.
         */
        public int compare(final UserData a, final UserData b) {
            return a.getUsername().compareTo(b.getUsername());
        }
    }

    public final static class SortGenreByViews implements Comparator<Map.Entry<String, Integer>> {
        /**
         * Used for sorting movies by duration
         * @param a video A
         * @param b video B
         * @return Compares two strings lexicographically.
         */
        public int compare(final Map.Entry<String, Integer> a, final Map.Entry<String, Integer> b) {
            return a.getValue().compareTo(b.getValue());
        }
    }
}

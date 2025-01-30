import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserRecommender {

    public static void main(String[] args) {
        try {
            // Load the data model from the CSV file
            DataModel dm = new FileDataModel(new File("data/movies.csv"));

            // Create the similarity model (user-based similarity)
            UserSimilarity sim = new TanimotoCoefficientSimilarity(dm);

            // Iterate over users (user IDs)
            int x = 1;
            for (LongPrimitiveIterator users = dm.getUserIDs(); users.hasNext(); ) {
                long userId = users.nextLong();

                // Get the movies rated by the user
                FastIDSet ratedItems = dm.getItemIDsFromUser(userId);

                // Create a set to keep track of recommended movie IDs for this user
                Set<Long> recommendedMovies = new HashSet<>();

                // Calculate the similarity with other users
                for (LongPrimitiveIterator otherUsers = dm.getUserIDs(); otherUsers.hasNext(); ) {
                    long otherUserId = otherUsers.nextLong();

                    // Skip comparing the user with themselves
                    if (userId == otherUserId) continue;

                    // Calculate similarity score between the current user and another user
                    double similarityScore = sim.userSimilarity(userId, otherUserId);

                    // If similarity is above a certain threshold, recommend items from the similar user
                    if (similarityScore > 0.1) { // Lower the threshold for more recommendations
                        // Get the movies rated by the similar user
                        FastIDSet otherUserRatedItems = dm.getItemIDsFromUser(otherUserId);

                        // Inside the loop where recommendations are made
                        for (LongPrimitiveIterator otherUserRatedItemsIterator = otherUserRatedItems.iterator(); otherUserRatedItemsIterator.hasNext(); ) {
                            long recommendedItemId = otherUserRatedItemsIterator.nextLong();

                            // Skip if the user has already rated this item or if it's already recommended
                            if (ratedItems.contains(recommendedItemId) || recommendedMovies.contains(recommendedItemId)) {
                                continue;
                            }

                            // Calculate base rating using similarity score (scale similarity to range 5.0 - 9.0)
                            double baseRating = 5.0 + (similarityScore * 4); // Scale to range 5.0 - 9.0

                            // Apply a small variation to avoid deterministic ratings
                            double variation = (Math.random() * 0.5) - 0.25; // Random variation between -0.25 and +0.25
                            double finalRating = baseRating + variation;

                            // Ensure the rating is within the 5.0 to 9.0 range
                            finalRating = Math.max(5.0, Math.min(finalRating, 9.0));


                            // Print the user ID, recommended movie ID (Movie B), and the rounded rating
                            System.out.printf("User ID: %-5d | Recommended Movie ID (B): %-5d | Predicted Rating: %.1f%n",
                                    userId, recommendedItemId, finalRating);

                            // Add the recommended movie ID to the set to prevent recommending it again
                            recommendedMovies.add(recommendedItemId);
                        }

                    }
                }
                x++;
                // Optional: limit the output to first 10 users
                if (x > 10) System.exit(1);
            }

        } catch (IOException e) {
            System.out.println("There was an error.");
            e.printStackTrace();
        } catch (TasteException e) {
            System.out.println("There was a Taste Exception");
            e.printStackTrace();
        }
    }
}
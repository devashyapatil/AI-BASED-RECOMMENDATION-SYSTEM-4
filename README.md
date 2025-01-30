# AI-BASED-RECOMMENDATION-SYSTEM-4

**COMPANY**: CODETECH IT SOLUTIONS

**NAME**: Devashya Tulsidas Patil

**INTERN ID**: CT08JFE

**DOMAIN**: JAVA PROGRAMMING

**BATCH DURATIONN**: January 5th, 2025 to February 5th, 2025

**MENTOR NAME**: Neela Santhosh Kumar

# DESCRIPTION: 

    Objective:
      To develop an AI-based recommendation system using Apache Mahout that suggests movies to 
      users based on their past ratings and the preferences of similar users using collaborative filtering.
    
    Introduction to Collaborative Filtering:
      -> Collaborative filtering is a widely used technique in recommendation systems. It operates under 
         the assumption that users who have rated items similarly in the past will rate similar items in the future.
      -> For this task, user-based collaborative filtering was employed, which suggests items that other
         similar users have rated highly but the current user has not yet rated.
    
    Setting Up the Project:
      -> Maven Setup:
          The project was set up using Maven to manage dependencies and simplify the build process.
          The pom.xml file included the following key dependencies:
            - Apache Mahout: For machine learning algorithms and similarity computation.
            - Guava: For efficient data structure management.
            - Commons Math: For advanced mathematical operations.
            - SLF4J: For logging purposes.
      -> Environment:
        Java version 17 was used as the source and target for compiling the code.
    
    Loading the Data Model:
      -> The recommendation system required a dataset representing users and the movies they rated.
      -> A CSV file (movies.csv) was used, containing user IDs, movie IDs, and ratings.
      -> FileDataModel from Apache Mahout was utilized to load and process this data into a
         format suitable for the recommendation system.
    
    User Similarity Calculation:
      -> Tanimoto Coefficient Similarity:
          The Tanimoto coefficient similarity was chosen to measure the similarity between users. It 
          compares the overlap of items rated by two users. The higher the score, the more similar
          the users are.
      -> Similarity Threshold:
          A threshold of 0.1 was used to identify users with sufficient similarity to generate
          recommendations.
    
    Generating Movie Recommendations:
      -> The system iterated over each user and found other users who have rated similar movies.
      -> For each user, the following steps were followed:
        - Similarity Calculation:
            Calculate the similarity score between the current user and all other users.
        - Filtering:
            If the similarity score was above the threshold (0.1), the system considered the two
            users as similar.
        - Movie Suggestions:
            Movies rated by the similar users were recommended to the current user, provided
            they hadn't already rated them.
      -> Rating Prediction:
        - The predicted rating for each recommended movie was computed using the similarity score. This rating was scaled between 5.0 and 9.0.
        - A small random variation was added to the predicted rating to make the predictions less deterministic and more natural.
    
    Ensuring Relevant Recommendations:
      -> To prevent recommending movies that the user had already rated or movies that had already
         been recommended, a set was used to track and filter out those movies.  
      -> The system recommended only those movies that the user had not yet rated and had not
         appeared in previous recommendations.
    
    Output and Results :
      -> The system printed the following information for each recommendation:
          - User ID: The user for whom the recommendation was made.
          - Recommended Movie ID: The ID of the recommended movie.
          - Predicted Rating: The predicted rating (scaled between 5.0 and 9.0) for the recommended movie.
      -> The output was limited to the first 10 users to make the system more manageable during testing.
    
    Conclusion :
      The task successfully implemented a user-based recommendation system, which predicts and
      recommends movies based on the ratings of similar users.


# Output:

![Image](https://github.com/user-attachments/assets/1df52533-6db2-4792-b508-92b640800fa9)
    


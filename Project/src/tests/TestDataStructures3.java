package tests;

import org.junit.Test;
import ratings.DegreesOfSeparation;
import ratings.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ratings.FileReader.readMovies;

public class TestDataStructures3 {
    public boolean compareMovie(Movie one, Movie two){
        return one.getTitle().equals(two.getTitle());
    }

    public Movie findMovieObj(Movie find,ArrayList<Movie> movieList){
        for(Movie movie : movieList){
            if(compareMovie(find,movie)){
                return movie;
            }
        }
        return null;
    }

    public void compareCast(Movie one, Movie two){
        ArrayList<String> castOne = one.getCast();
        ArrayList<String> castTwo = two.getCast();

        for(int i = 0; i<castOne.size();i++){
            assertEquals(castOne.get(i),castTwo.get(i));
        }
    }
    @Test
    public void testReadMovies(){
        //----------------- N/a csv file -----------------
        ArrayList<Movie> expected = new ArrayList<>();
        assertEquals(expected,readMovies("data/apple.csv"));

        //----------------- Cast In Order -----------------

        //Powder,Mary Steenburgen,Sean Patrick Flanery,Lance Henriksen,Jeff Goldblum,Brandon Smith
        Movie expectedMovie = new Movie("Powder",new ArrayList<>(Arrays.asList("Mary Steenburgen","Sean Patrick Flanery","Lance Henriksen","Jeff Goldblum","Brandon Smith")));
        ArrayList<Movie> actualMovieList = readMovies("data/movies.csv");
        Movie actualMovie = findMovieObj(expectedMovie,actualMovieList);
        if(actualMovie == null){
            assertEquals(0,1);
        }

        assert actualMovie != null;
        compareCast(expectedMovie,actualMovie);
    }
    @Test
    public void testDegreeOfSeperation(){
        //------------------------- Actor to Themselves -------------------------
        DegreesOfSeparation actorToThemselves = new DegreesOfSeparation(
                new ArrayList<>(Arrays.asList(
                        new Movie("One",new ArrayList<>(List.of("Actor"))),
                        new Movie("Two",new ArrayList<>(List.of("Actor")))))
        );
        assertEquals(0,actorToThemselves.degreesOfSeparation("Actor","Actor"));
        //------------------------- No Connection -------------------------
        DegreesOfSeparation noConnection = new DegreesOfSeparation(
                new ArrayList<>(Arrays.asList(
                        new Movie("One",new ArrayList<>(List.of("Actor","MrActor","Apple"))),
                        new Movie("Between One and Two",new ArrayList(List.of("Apple","Lemon","Orange"))),
                        new Movie("Two",new ArrayList<>(List.of("Actress","MsActress")))))
        );
        assertEquals(-1,noConnection.degreesOfSeparation("Actor","Actress"));
        //------------------------- Non Existant Actors -------------------------
        DegreesOfSeparation nonExistantActors = new DegreesOfSeparation(
                new ArrayList<>(Arrays.asList(
                        new Movie("One",new ArrayList<>(List.of("Actor"))),
                        new Movie("Two",new ArrayList<>(List.of("Actress")))))
        );
        assertEquals(-1,nonExistantActors.degreesOfSeparation("Mr","Ms"));
        //------------------------- One Degree -------------------------
        DegreesOfSeparation oneDegree = new DegreesOfSeparation(
                new ArrayList<>(Arrays.asList(
                        new Movie("One",new ArrayList<>(Arrays.asList("Chris Pratt","Brad Pitt","Jonah Hill","Philip Seymour Hoffman"))),
                        new Movie("Two",new ArrayList<>(Arrays.asList("Robert De Nero","Brad Pitt","Kevin Bacon","Jason Patric")))))
        );
        assertEquals(2,oneDegree.degreesOfSeparation("Chris Pratt","Kevin Bacon"));
        //------------------------- Multi Degree -------------------------
        DegreesOfSeparation multiDegree = new DegreesOfSeparation(
                new ArrayList<>(Arrays.asList(
                        new Movie("One",new ArrayList<>(Arrays.asList("Chris Pratt","Brad Pitt","Jonah Hill","Philip Seymour Hoffman"))),
                        new Movie("Three",new ArrayList<>(Arrays.asList("Ryan Reynolds","Kevin Bacon"))),
                        new Movie("Two",new ArrayList<>(Arrays.asList("Robert De Nero","Brad Pitt","Kevin Bacon","Jason Patric"))),
                        new Movie("Four",new ArrayList<>(Arrays.asList("Jonah Hill","Miley Cyrus"))),
                        new Movie("Five",new ArrayList<>(Arrays.asList("Bane Herc","Miley Cyrus")))
                )
                )
        );
        assertEquals(3,multiDegree.degreesOfSeparation("Chris Pratt","Ryan Reynolds"));
    }

    @Test
    public void testMethodsLimits(){
        //--------------------- Movie.csv ---------------------
        ArrayList<Movie> movieCSV = readMovies("data/movies.csv");
        DegreesOfSeparation degreeMoviesCSV = new DegreesOfSeparation(movieCSV);
        //--------------------- Movie.csv 2 Degrees ---------------------
        assertEquals(2,degreeMoviesCSV.degreesOfSeparation("Chris Pratt","Kevin Bacon"));
        //--------------------- Movie.csv -1 ---------------------
        assertEquals(-1,degreeMoviesCSV.degreesOfSeparation("Kevin Bacon","This Does Not Exist"));
        //--------------------- Movie.csv -1 Part 2 ---------------------
        assertEquals(-1,degreeMoviesCSV.degreesOfSeparation("This Does Not Exist","Kevin Bacon"));
        //--------------------- Movie.csv compare itself ---------------------
        assertEquals(0,degreeMoviesCSV.degreesOfSeparation("Kevin Bacon","Kevin Bacon"));
    }

}


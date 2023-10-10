package ratings;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;



public class FileReader{

    public static ArrayList<String> readFile(String filename){
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e){
            return new ArrayList<>();
        }
    }

    public static ArrayList<Movie> readMovies(String filename){
        ArrayList<Movie> out = new ArrayList<>();
        ArrayList<String> lines = readFile(filename);
        for(String line : lines){
            ArrayList<String> split = new ArrayList<>(Arrays.asList(line.split(",")));
            ArrayList<String> tempCast = new ArrayList<>();
            for(int i = 1; i < split.size();i++){
                tempCast.add(split.get(i));
            }
            Movie tempMovie = new Movie(split.get(0),tempCast);
            out.add(tempMovie);
        }
        return out;
    }


    public static Movie getMovieObjInArray(String movieToLookFor, ArrayList<Movie> list){
        for(Movie movie : list){
            if(movie.getTitle().equals(movieToLookFor)){
                return movie;
            }
        }
        return null;
    }

}

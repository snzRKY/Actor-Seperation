package ratings;

import ratings.datastructures.Queue;

import java.util.ArrayList;
import java.util.HashMap;

public class DegreesOfSeparation {
    private final ArrayList<Movie> movieList;
    public DegreesOfSeparation(ArrayList<Movie> movieList){
        this.movieList = movieList;
    }
    public ArrayList<String> oneAwayList(String actor){
        ArrayList<String> out = new ArrayList<>();
        for(Movie movie : this.movieList){
            if(movie.getCast().contains(actor)){
                out.addAll(movie.getCast());
                out.remove(actor);
            }
        }
        return out;
    }
    public int degreesOfSeparation(String start,String end){
        Queue<String> queue = new Queue<>();
        HashMap<String,Integer> distanceFromStart = new HashMap<>();

        queue.enqueue(start);
        distanceFromStart.put(start,0);
        ArrayList<String> oneAwayList;

        while(queue.getFront() != null){
            oneAwayList = oneAwayList(queue.getFront());
            for(String cast : oneAwayList){
                if(distanceFromStart.get(cast) == null){
                    queue.enqueue(cast);
                    if(cast.equals(end)){
                        return (distanceFromStart.get(queue.getFront())+1);
                    }
                    distanceFromStart.put(cast,distanceFromStart.get(queue.getFront())+1);
                }
            }
            queue.dequeue();
        }
        if(start.equals(end)){
            return 0;
        }
        return -1;
    }


}

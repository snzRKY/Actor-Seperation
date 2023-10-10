package ratings;

import java.util.ArrayList;

public class Movie extends Ratable{
    private String title;
    private ArrayList cast;

    public Movie(String title, ArrayList cast){
        super(title);
        this.cast = cast;
    }

    public ArrayList<String> getCast(){
        return this.cast;
    }


}

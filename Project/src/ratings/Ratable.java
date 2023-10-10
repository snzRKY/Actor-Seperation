package ratings;


public class Ratable {
    protected String title;

    public Ratable(String title) {
        setTitle(title);
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return  title;
    }
}

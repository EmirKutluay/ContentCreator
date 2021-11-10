import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ContentCreator {

    public static void main(String args[]){
        ContentCreator contentCreator = new ContentCreator();
        String[] movieCategories = {"action", "adventure", "animation", "biography", "comedy", "crime", "drama", "fantasy", "history", "horror", "romance", "sci-fi"};
        String[] musicCategories = {"hiphop", "pop", "rock", "indie"};
        contentCreator.types.put("movie", movieCategories);
        contentCreator.types.put("music", musicCategories);

        contentCreator.createFiles();
        contentCreator.movies();
    }

    public HashMap<String, ArrayList<String>> movies = new HashMap<>();
    public HashMap<String, String[]> types = new HashMap<>();


    public void movies(){
        Movie movieClass = new Movie(this);
        for (String category : types.get("movie")){
            movieClass.getMovieList(category);
        }
        //movieClass.getMovie(movies.get("action").get(0)) GET THE NAME OF THE #1 MOVIE IN ACTION CATEGORY
        System.out.println(movieClass.getMovie(movies.get("sci-fi").get(0)));
    }

    public void intro(String type, String category){
        Intro introClass = new Intro(this);
        if (type == "movie"){
            introClass.movieIntro(category);
        }
    }

    public void createFiles(){
        for (String type : types.keySet()){
            File file = new File("D:\\ContentCreator\\" + type);
            if (!file.exists()){
                file.mkdir();
            }
            File downloads = new File("D:\\ContentCreator\\downloads");
            if (!downloads.exists()){
                downloads.mkdir();
            }
            for (String category : types.get(type)){
                File categoryFile = new File("D:\\ContentCreator\\" + type + "\\" + category);
                if (!categoryFile.exists()){
                    categoryFile.mkdir();
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime now = LocalDateTime.now();
                File dateFile = new File("D:\\ContentCreator\\" + type + "\\" + category + "\\" + dtf.format(now));
                if (!dateFile.exists()){
                    dateFile.mkdir();
                }
            }
        }
    }

}

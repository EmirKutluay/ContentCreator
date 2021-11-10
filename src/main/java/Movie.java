import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;

public class Movie {

    ContentCreator main;
    public Movie(ContentCreator instance) {
        main = instance;
    }

    public void getMovieList(String category){
        ArrayList<String> movieList = new ArrayList<>();
        HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-popular-movies-by-genre?genre=%2Fchart%2Fpopular%2Fgenre%2F" + category)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "2ee9edf7cdmsh9fef533ee484a2fp1b5759jsn15dc37d0d1da")
                .asString();
        String[] list = response.getBody().substring(1, response.getBody().length()-1).split(",");
        for (int i = 0; i < 10; i++){
            String movie = list[i];
            String movieID = movie.substring(8, movie.length()-2);
            movieList.add(movieID);
        }
        main.movies.put(category, movieList);
    }

    public String getMovie(String movieID){
        HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-details?tconst=" + movieID)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "2ee9edf7cdmsh9fef533ee484a2fp1b5759jsn15dc37d0d1da")
                .asString();
        String[] movieInfo = response.getBody().substring(1, response.getBody().length()-1).split(",");
        String name = "";
        Integer year = 0;
        for (String info : movieInfo){
            if (info.contains("\"title\"")){
                name = info.substring(9,info.length()-1);
            } else if (info.contains("\"year\"")){
                year = Integer.valueOf(info.substring(7));
            }
        }
        return name + " (" + year + ")";
    }

}

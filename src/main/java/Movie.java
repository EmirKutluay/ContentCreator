import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Movie {

    public void getMovieList(String type){
        System.out.println("Most Popular " + type.substring(0,1).toUpperCase() + type.substring(1) + " Movies");
        System.out.println();
        HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-popular-movies-by-genre?genre=%2Fchart%2Fpopular%2Fgenre%2F" + type)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "2ee9edf7cdmsh9fef533ee484a2fp1b5759jsn15dc37d0d1da")
                .asString();
        String[] list = response.getBody().substring(1, response.getBody().length()-1).split(",");
        for (int i = 0; i < 10; i++){
            String movie = list[i];
            String movieID = movie.substring(8, movie.length()-2);
            getMovie(movieID);
        }
        System.out.println();
    }

    public void getMovie(String movieID){
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
        System.out.println(name + " (" + year + ")");
    }

}

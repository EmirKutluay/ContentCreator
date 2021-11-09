public class ContentCreator {

    public static void main(String args[]){
        movies();
    }

    public static void movies(){
        Movie movieClass = new Movie();
        String[] movieTypes = {"action", "adventure", "animation", "biography", "comedy", "crime", "drama"};
        for (String type : movieTypes){
            movieClass.getMovieList(type);
        }
    }

}

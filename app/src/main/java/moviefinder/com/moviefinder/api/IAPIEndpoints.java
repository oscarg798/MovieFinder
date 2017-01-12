package moviefinder.com.moviefinder.api;

import moviefinder.com.moviefinder.model.dto.SearchResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oscargallon on 1/11/17.
 * This interface represents all the API endpoints calls
 */
public interface IAPIEndpoints {

    /**
     * The GET annotation  will tell to the library that this endpoint will be a GET call,
     * the string that follows this annotation is the url to the endpoint, for example if our
     * API have a method to find dogs his base url will be like www.petfinder.com
     * and the endpoint to search for dogs will be something like www.petfinder.com/find?name=dexter
     * so the Base URL is defined on {@link ApiProvider} class, and the other part should be
     * defined in the @GET annotation such us   @GET("/find")
     * <p>
     * <p>
     * Note that the variables passed to the method searchMoviesOrSeries in the parenthesis
     * begins with a Query annotation, that will tell to the library that this are query parameters
     * For more info about query or path parameters
     * http://stackoverflow.com/questions/10415755/java-restful-services-what-is-the-difference-between-queryparam-and-pathparam
     * Continue with the pets example, the endpoint to search for pets (www.petfinder.com/find?name=dexter)
     * receives the name of the pet so after the method name we must specify this Query parameter
     * for example
     * Call<Object> getPets(@Query("name") String name)
     * <p>
     * Note also that as this class is an interface, we only need to define the method, and
     * we do not specify if the method is public, private or protected because the library will define
     * that for us
     *
     * Note also that after the work Call we define inside <> the Java object that the endpoint will return,
     * we have create this class
     *
     * @param textToSearch the text to search, could be the movie or series name, or
     *                     and actor/director name, or whatever related with the one that
     *                     the user want to search
     * @param type         if we want to search for movies or series, its an optional parameter, can be null
     * @param year         if we want to add the year
     * @param resultType   the format for the result on the api json or xml
     * @return An {@link SearchResponseDTO} object with the response from the API
     * note that we do not have to parse the response the Retrofit library will do this for us
     * For more information please read https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
     */
    @GET("/?")
    Call<SearchResponseDTO> searchMoviesOrSeries(@Query("s") String textToSearch,
                                                 @Query("type") String type,
                                                 @Query("y") String year,
                                                 @Query("r") String resultType);
}

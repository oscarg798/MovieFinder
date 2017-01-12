package moviefinder.com.moviefinder.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oscargallon on 1/11/17.
 * This is the class that will create the object that will handle the calls to the API
 * It is a singleton class, so only will be a instance of this class on memory while the app is
 * running
 */
public class ApiProvider {

    /**
     * We define the base url, it is the API entry point
     */
    private static final String BASE_URL = "http://omdbapi.com";

    /**
     * We define the object that will have the api enpoints mapped
     */
    private IAPIEndpoints APIEndpointsService;

    /**
     * The Singleton class instance
     */
    private static ApiProvider ourInstance = new ApiProvider();

    /**
     * A method to get the class instance
     * @return the {@link ApiProvider} instance
     */
    public static ApiProvider getInstance() {
        return ourInstance;
    }

    /**
     * Contructor
     */
    private ApiProvider() {
        initAPI();
    }



    private void initAPI() {
        /**
         * We need a library to parse the data returned by the API into our
         * Java Models (DTOs)
         */
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        /**
         * We create an object to define the connection parameters
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        /**
         * We specify the time outs for the connections to the API
         */
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        /**
         * We create an Object to log the services calls
         */
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        /**
         * We specify the level of the logs that we want
         */
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * We add the logs to our client
         */
        builder.networkInterceptors().add(httpLoggingInterceptor);


        /**
         * We create the object that will point to our base url,
         * we pass the object that have the connection properties
         * in the client method, also we pass the Gson library that will parse
         * the json data into java models
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIEndpointsService = retrofit.create(IAPIEndpoints.class);
    }

    /**
     * This method will return a object with all the endpoints mapped
     * @return IAPIEndpoints object
     */
    public IAPIEndpoints getAPIEndpointsService() {
        return APIEndpointsService;
    }
}

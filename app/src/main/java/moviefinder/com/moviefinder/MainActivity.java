package moviefinder.com.moviefinder;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import moviefinder.com.moviefinder.api.ApiProvider;
import moviefinder.com.moviefinder.model.dto.MovieOrSeriesSearchResultDTO;
import moviefinder.com.moviefinder.model.dto.SearchResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //appbar = (Toolbar)findViewById(R.id.appbar);
        //setSupportActionBar(appbar);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_favorite);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
       // mTextView = (TextView) findViewById(R.id.tv_1);
        searchExample();
    }

    /**
     * This method shows an example about how to call the API using the Library and the endpoints
     * defined
     */
    private void searchExample() {
        /**
         * The first thing is obtain the object instance from the {@link ApiProvider} class, we
         * use the getInstance() method because the {@link ApiProvider} class is a singleton,
         * then
         *
         */
        ApiProvider.getInstance()
                /**
                 * we get the {@link moviefinder.com.moviefinder.api.IAPIEndpoints} object that is
                 * the object that have the methods to call the API endpoints
                 */
                .getAPIEndpointsService()
                /**
                 * We use the method to search for movies or series that we define in
                 * {@link moviefinder.com.moviefinder.api.IAPIEndpoints} interface
                 */
                .searchMoviesOrSeries("suits", "series", null, "json")
                /**
                 * Then we use the .enqueue() method to make the call to the API,
                 * this method receives an implementation of a callback,
                 * we just have to press the keys  control + space bar, and
                 * android studio will write the implementation for us,
                 * the implementation have two principals methods,
                 * onResponse() that is a method called by the library when the API was
                 * successful reached
                 * onFailure() when something goes wrong
                 */
                .enqueue(new Callback<SearchResponseDTO>() {

                    /**
                     * As we told before this method is the success callback
                     * @param call This object represents the call that we send to the API, we do not use it
                     * @param response and this one is the response object that contains the API response,
                     *                 calling response.Body we will get the Java Object with the api result.
                     *                 in this case it is a {@link SearchResponseDTO} object
                     */
                    @Override
                    public void onResponse(Call<SearchResponseDTO> call, Response<SearchResponseDTO> response) {

                        SearchResponseDTO searchResponseDTO = response.body();

                        if (searchResponseDTO != null && searchResponseDTO.isResponse()) {
                            List<MovieOrSeriesSearchResultDTO> movieOrSeriesSearchResultDTOList =
                                    searchResponseDTO.getMovieOrSeriesSearchResultDTOList();
                            for (MovieOrSeriesSearchResultDTO movieOrSeriesSearchResultDTO : movieOrSeriesSearchResultDTOList) {
                                String title = movieOrSeriesSearchResultDTO.getTitle() + "\n";
                                //mTextView.append(title);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponseDTO> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
    }
}

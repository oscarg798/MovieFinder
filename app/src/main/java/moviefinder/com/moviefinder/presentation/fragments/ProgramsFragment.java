package moviefinder.com.moviefinder.presentation.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import moviefinder.com.moviefinder.R;
import moviefinder.com.moviefinder.api.ApiProvider;
import moviefinder.com.moviefinder.model.dto.MovieOrSeriesSearchResultDTO;
import moviefinder.com.moviefinder.model.dto.ProgramDTO;
import moviefinder.com.moviefinder.model.dto.SearchResponseDTO;
import moviefinder.com.moviefinder.presentation.adapters.ProgramListRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ProgramsFragment extends Fragment {

    private List<ProgramDTO> programDTOList;
    private RecyclerView rvProgramList;
    private ProgramListRecyclerViewAdapter programListRecyclerViewAdapter;
    private FrameLayout flContent;
    //private Callbacks.IFragmentReadyCallback IFragmentReadyCallback;
    //private ProgressBar progressBar;
    private GridLayoutManager gridLayoutManager;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefreshing;
    private boolean isFiltering = false;
    private List<MovieOrSeriesSearchResultDTO> movieOrSeriesSearchResultDTO;

    public ProgramsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment programs.
     */
    public static ProgramsFragment newInstance() {
        ProgramsFragment fragment = new ProgramsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    private void getData() {
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
                            movieOrSeriesSearchResultDTO = searchResponseDTO.getMovieOrSeriesSearchResultDTOList();
                            populateViews();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponseDTO> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
    }

    private void populateViews() {

        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        rvProgramList.setLayoutManager(gridLayoutManager);
        programListRecyclerViewAdapter = new ProgramListRecyclerViewAdapter(movieOrSeriesSearchResultDTO, getContext());
        rvProgramList.setAdapter(programListRecyclerViewAdapter);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);
        initViewComponents(view);
        return view;
    }

    public void initViewComponents(View view){
        rvProgramList = (RecyclerView) view.findViewById(R.id.rv_programs);
        //flContent = (LinearLayout) view.findViewById(R.id.fl_content);
        getData();
    }

}

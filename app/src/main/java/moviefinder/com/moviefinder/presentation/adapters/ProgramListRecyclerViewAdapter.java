package moviefinder.com.moviefinder.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import moviefinder.com.moviefinder.R;
import moviefinder.com.moviefinder.model.dto.MovieOrSeriesSearchResultDTO;
import moviefinder.com.moviefinder.presentation.holders.ProgramListItemViewHolder;

/**
 * Created by oscar.gallon on 27/01/17.
 */

public class ProgramListRecyclerViewAdapter extends RecyclerView.Adapter<ProgramListItemViewHolder> {

    private final Context context;
    private List<MovieOrSeriesSearchResultDTO> movieOrSeriesSearchResultDTOList;
    //private Callbacks.RecyclerViewAdapterOnItemClickListener

    public ProgramListRecyclerViewAdapter(List<MovieOrSeriesSearchResultDTO> movieOrSeriesSearchResultDTOList, Context context) {
        this.movieOrSeriesSearchResultDTOList = movieOrSeriesSearchResultDTOList;
        this.context = context;
        //this.recyclerViewAdapterOnItemClickListener = recyclerViewAdapterOnItemClickListener;

    }

    @Override
    public ProgramListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.program_item, parent, false);

        return new ProgramListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProgramListItemViewHolder holder, int position) {
        final MovieOrSeriesSearchResultDTO movieOrSeriesSearchResultDTO = movieOrSeriesSearchResultDTOList.get(position);
        holder.getTvProgramTitle().setText(movieOrSeriesSearchResultDTO.getTitle());
        holder.getTvProgramType().setText(movieOrSeriesSearchResultDTO.getType());
        holder.getTvProgramYear().setText(movieOrSeriesSearchResultDTO.getYear());
        if(!movieOrSeriesSearchResultDTO.getPoster().equals("N/A")) {
            Picasso.with(context).load(movieOrSeriesSearchResultDTO.getPoster()).into(holder.getIvProgramPoster());
        }
        holder.setMovieOrSeriesSearchResultDTO(movieOrSeriesSearchResultDTO);

    }

    @Override
    public int getItemCount() {
        return this.movieOrSeriesSearchResultDTOList.size();
    }

    public List<MovieOrSeriesSearchResultDTO> getMovieOrSeriesSearchResultDTOList() {
        return movieOrSeriesSearchResultDTOList;
    }

    public void setMovieOrSeriesSearchResultDTOList(List<MovieOrSeriesSearchResultDTO>
                                                            movieOrSeriesSearchResultDTOList) {
        this.movieOrSeriesSearchResultDTOList = movieOrSeriesSearchResultDTOList;
    }
}

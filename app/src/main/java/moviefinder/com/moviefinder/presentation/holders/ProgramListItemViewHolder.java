package moviefinder.com.moviefinder.presentation.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import moviefinder.com.moviefinder.R;
import moviefinder.com.moviefinder.model.dto.MovieOrSeriesSearchResultDTO;

/**
 * Created by stephany.berrio on 27/01/17.
 */

public class ProgramListItemViewHolder extends RecyclerView.ViewHolder{

    private final TextView tvProgramTitle;
    private final TextView tvProgramGenre;
    private final TextView tvProgramLanguage;
    private final ImageView ivProgramPoster;
    private MovieOrSeriesSearchResultDTO movieOrSeriesSearchResultDTO;

    public ProgramListItemViewHolder(View itemView){
            super(itemView);
        tvProgramTitle = (TextView) itemView.findViewById(R.id.tv_program_title);
        tvProgramGenre = (TextView) itemView.findViewById(R.id.tv_program_genre);
        tvProgramLanguage = (TextView) itemView.findViewById(R.id.tv_program_language);
        ivProgramPoster = (ImageView) itemView.findViewById(R.id.iv_program_poster);

    }

    public TextView getTvProgramTitle() {
        return tvProgramTitle;
    }

    public TextView getTvProgramType() {
        return tvProgramGenre;
    }

    public TextView getTvProgramYear() {
        return tvProgramLanguage;
    }

    public ImageView getIvProgramPoster() {
        return ivProgramPoster;
    }

    public MovieOrSeriesSearchResultDTO getMovieOrSeriesSearchResultDTO() {
        return movieOrSeriesSearchResultDTO;
    }

    public void setMovieOrSeriesSearchResultDTO(MovieOrSeriesSearchResultDTO movieOrSeriesSearchResultDTO) {
        this.movieOrSeriesSearchResultDTO = movieOrSeriesSearchResultDTO;
    }
}

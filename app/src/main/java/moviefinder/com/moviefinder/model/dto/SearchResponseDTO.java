package moviefinder.com.moviefinder.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oscargallon on 1/11/17.
 * This class represents the object that is returned by the API when we search for a movie or
 * series. Please try this in your browser http://omdbapi.com/?&s=suits&type=series&r=json
 */
public class SearchResponseDTO  implements Parcelable{

    private final int totalResults;

    @SerializedName("Response")
    private final boolean response;

    @SerializedName("Search")
    private final List<MovieOrSeriesSearchResultDTO> movieOrSeriesSearchResultDTOList;

    public SearchResponseDTO(int totalResults, boolean response, List<MovieOrSeriesSearchResultDTO> searchResultDTOList) {
        this.totalResults = totalResults;
        this.response = response;
        this.movieOrSeriesSearchResultDTOList = searchResultDTOList;
    }

    protected SearchResponseDTO(Parcel in) {
        totalResults = in.readInt();
        response = in.readByte() != 0;
        movieOrSeriesSearchResultDTOList = in.createTypedArrayList(MovieOrSeriesSearchResultDTO.CREATOR);
    }

    public static final Creator<SearchResponseDTO> CREATOR = new Creator<SearchResponseDTO>() {
        @Override
        public SearchResponseDTO createFromParcel(Parcel in) {
            return new SearchResponseDTO(in);
        }

        @Override
        public SearchResponseDTO[] newArray(int size) {
            return new SearchResponseDTO[size];
        }
    };

    public int getTotalResults() {
        return totalResults;
    }

    public boolean isResponse() {
        return response;
    }

    public List<MovieOrSeriesSearchResultDTO> getMovieOrSeriesSearchResultDTOList() {
        return movieOrSeriesSearchResultDTOList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(totalResults);
        parcel.writeByte((byte) (response ? 1 : 0));
        parcel.writeTypedList(movieOrSeriesSearchResultDTOList);
    }
}

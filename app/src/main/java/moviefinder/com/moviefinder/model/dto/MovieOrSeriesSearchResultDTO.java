package moviefinder.com.moviefinder.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oscargallon on 1/11/17.
 * This class represents a movie or series result returned in a  search
 * Example of the data returned
 * http://www.omdbapi.com/?s=suits&y=&plot=short&type=series&r=json
 * For information about the API http://www.omdbapi.com/ please look for 'By Search' title below
 * 'Parameters' title
 */
public class MovieOrSeriesSearchResultDTO implements Parcelable {

    /**
     * So the serialized name is an annotation that will let the GSON
     * library that the variable title in java will come in a json with the name JSON
     * {Title: 'some title'}--> title = "some title"
     */
    @SerializedName("Title")
    private final String title;

    @SerializedName("Year")
    private final String year;

    private final String imdbID;

    @SerializedName("Type")
    private final String type;

    @SerializedName("Poster")
    private final String poster;

    public MovieOrSeriesSearchResultDTO(String title, String year, String imdbID, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
    }

    protected MovieOrSeriesSearchResultDTO(Parcel in) {
        title = in.readString();
        year = in.readString();
        imdbID = in.readString();
        type = in.readString();
        poster = in.readString();
    }

    public static final Creator<MovieOrSeriesSearchResultDTO> CREATOR = new Creator<MovieOrSeriesSearchResultDTO>() {
        @Override
        public MovieOrSeriesSearchResultDTO createFromParcel(Parcel in) {
            return new MovieOrSeriesSearchResultDTO(in);
        }

        @Override
        public MovieOrSeriesSearchResultDTO[] newArray(int size) {
            return new MovieOrSeriesSearchResultDTO[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(imdbID);
        parcel.writeString(type);
        parcel.writeString(poster);
    }
}

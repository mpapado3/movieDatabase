/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

import java.util.Date;

/**
 *
 * @author michael_papado
 */
public class MoviePOJO {
    
    private int id;
    private String title;
    private Date releaseDate;
    private Double rating;
    private String overview;
    private GenrePOJO genre;
    private FavoriteListPOJO favList;

    public MoviePOJO() {
    }

    public MoviePOJO(int id, String title, Date releaseDate, Double rating, String overview) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public GenrePOJO getGenre() {
        return genre;
    }

    public void setGenre(GenrePOJO genre) {
        this.genre = genre;
    }

    public FavoriteListPOJO getFavList() {
        return favList;
    }

    public void setFavList(FavoriteListPOJO favList) {
        this.favList = favList;
    }

    @Override
    public String toString() {
        return "MoviePOJO{" + "id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", rating=" + rating + ", overview=" + overview + ", genre=" + genre + ", favList=" + favList + '}';
    }
    
    
    
}

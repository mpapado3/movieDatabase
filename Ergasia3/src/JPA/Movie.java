/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nick
 */
@Entity
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
    , @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title")
    , @NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movie.findByRating", query = "SELECT m FROM Movie m WHERE m.rating = :rating")
    , @NamedQuery(name = "Movie.findByOverview", query = "SELECT m FROM Movie m WHERE m.overview = :overview")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Basic(optional = false)
    @Column(name = "RATING")
    private float rating;
    @Basic(optional = false)
    @Column(name = "OVERVIEW")
    private String overview;
    @JoinColumn(name = "FAVORITE_LISTID", referencedColumnName = "ID")
    @ManyToOne
    private FavoriteList favoriteListid;
    @JoinColumn(name = "GENREID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Genre genreid;

    public Movie() {
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public Movie(Integer id, String title, Date releaseDate, float rating, String overview) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public FavoriteList getFavoriteListid() {
        return favoriteListid;
    }

    public void setFavoriteListid(FavoriteList favoriteListid) {
        this.favoriteListid = favoriteListid;
    }

    public Genre getGenreid() {
        return genreid;
    }

    public void setGenreid(Genre genreid) {
        this.genreid = genreid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Movie[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_movie", catalog = "MovieDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id"),
    @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
    @NamedQuery(name = "Movie.findByImage", query = "SELECT m FROM Movie m WHERE m.image = :image"),
    @NamedQuery(name = "Movie.findByLink", query = "SELECT m FROM Movie m WHERE m.link = :link"),
    @NamedQuery(name = "Movie.findByCategory", query = "SELECT m FROM Movie m WHERE m.category = :category")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic(optional = false)
    @Column(name = "image", nullable = false, length = 255)
    private String image;
    @Basic(optional = false)
    @Column(name = "link", nullable = false, length = 255)
    private String link;
    @Basic(optional = false)
    @Column(name = "category", nullable = false, length = 255)
    private String category;

    public Movie() {
    }

    public Movie(Long id) {
        this.id = id;
    }

    public Movie(Long id, String title, String image, String link, String category) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.category = category;
    }

    public Movie(String title, String image, String link, String category) {

        this.title = title;
        this.image = image;
        this.link = link;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return "entities.Movie[ id=" + id + " ]";
    }

}

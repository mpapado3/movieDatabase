/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.awt.List;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */

@Entity
@Table(name = "FAVORITE_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoriteList.findAll", query = "SELECT f FROM FavoriteList f")
    , @NamedQuery(name = "FavoriteList.findById", query = "SELECT f FROM FavoriteList f WHERE f.id = :id")
    , @NamedQuery(name = "FavoriteList.findByName", query = "SELECT f FROM FavoriteList f WHERE f.name = :name")
    , @NamedQuery(name = "FavoriteList.deleteAll", query = "Delete From FavoriteList f")})

public class CreateMovieList implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
 
    @Basic(optional = false)
    @Column(name = "NAME")
    @Id
    private String name;
  
    
    public CreateMovieList() {
    }

    public CreateMovieList(int id, String name) {
      
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
 
    
}

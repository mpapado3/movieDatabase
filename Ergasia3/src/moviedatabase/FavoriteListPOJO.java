/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

/**
 *
 * @author michael_papado
 */
public class FavoriteListPOJO {
    
    private int id;
    private String name;

    public FavoriteListPOJO() {
    }

    public FavoriteListPOJO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FavoriteListPOJO{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}

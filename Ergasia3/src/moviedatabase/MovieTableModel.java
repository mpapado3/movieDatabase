/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import moviedatabase.entities.Movie;

/**
 *
 * @author michael_papado
 */
public class MovieTableModel extends AbstractTableModel {
    
    private List<Movie> movies;
    
    private String[] columnNames;

    public MovieTableModel(List<Movie> movies, String[] columnNames) {
        this.movies = new ArrayList<Movie>(movies);
        this.columnNames = columnNames;
    }   

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        
        switch(columnIndex){
            case 0: return movie.getTitle();
            case 1: return movie.getRating();
            case 2: return movie.getOverview();
            default: return "ERROR";
        }
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Nick
 */
public class MovieJpaController implements Serializable {

    public MovieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movie movie) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FavoriteList favoriteListid = movie.getFavoriteListid();
            if (favoriteListid != null) {
                favoriteListid = em.getReference(favoriteListid.getClass(), favoriteListid.getId());
                movie.setFavoriteListid(favoriteListid);
            }
            Genre genreid = movie.getGenreid();
            if (genreid != null) {
                genreid = em.getReference(genreid.getClass(), genreid.getId());
                movie.setGenreid(genreid);
            }
            em.persist(movie);
            if (favoriteListid != null) {
                favoriteListid.getMovieList().add(movie);
                favoriteListid = em.merge(favoriteListid);
            }
            if (genreid != null) {
                genreid.getMovieList().add(movie);
                genreid = em.merge(genreid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movie movie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie persistentMovie = em.find(Movie.class, movie.getId());
            FavoriteList favoriteListidOld = persistentMovie.getFavoriteListid();
            FavoriteList favoriteListidNew = movie.getFavoriteListid();
            Genre genreidOld = persistentMovie.getGenreid();
            Genre genreidNew = movie.getGenreid();
            if (favoriteListidNew != null) {
                favoriteListidNew = em.getReference(favoriteListidNew.getClass(), favoriteListidNew.getId());
                movie.setFavoriteListid(favoriteListidNew);
            }
            if (genreidNew != null) {
                genreidNew = em.getReference(genreidNew.getClass(), genreidNew.getId());
                movie.setGenreid(genreidNew);
            }
            movie = em.merge(movie);
            if (favoriteListidOld != null && !favoriteListidOld.equals(favoriteListidNew)) {
                favoriteListidOld.getMovieList().remove(movie);
                favoriteListidOld = em.merge(favoriteListidOld);
            }
            if (favoriteListidNew != null && !favoriteListidNew.equals(favoriteListidOld)) {
                favoriteListidNew.getMovieList().add(movie);
                favoriteListidNew = em.merge(favoriteListidNew);
            }
            if (genreidOld != null && !genreidOld.equals(genreidNew)) {
                genreidOld.getMovieList().remove(movie);
                genreidOld = em.merge(genreidOld);
            }
            if (genreidNew != null && !genreidNew.equals(genreidOld)) {
                genreidNew.getMovieList().add(movie);
                genreidNew = em.merge(genreidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movie.getId();
                if (findMovie(id) == null) {
                    throw new NonexistentEntityException("The movie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movie;
            try {
                movie = em.getReference(Movie.class, id);
                movie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movie with id " + id + " no longer exists.", enfe);
            }
            FavoriteList favoriteListid = movie.getFavoriteListid();
            if (favoriteListid != null) {
                favoriteListid.getMovieList().remove(movie);
                favoriteListid = em.merge(favoriteListid);
            }
            Genre genreid = movie.getGenreid();
            if (genreid != null) {
                genreid.getMovieList().remove(movie);
                genreid = em.merge(genreid);
            }
            em.remove(movie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movie> findMovieEntities() {
        return findMovieEntities(true, -1, -1);
    }

    public List<Movie> findMovieEntities(int maxResults, int firstResult) {
        return findMovieEntities(false, maxResults, firstResult);
    }

    private List<Movie> findMovieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movie.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movie findMovie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movie> rt = cq.from(Movie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

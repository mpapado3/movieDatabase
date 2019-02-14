/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nick
 */
public class FavoriteListJpaController implements Serializable {

    public FavoriteListJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FavoriteList favoriteList) {
        if (favoriteList.getMovieList() == null) {
            favoriteList.setMovieList(new ArrayList<Movie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movie> attachedMovieList = new ArrayList<Movie>();
            for (Movie movieListMovieToAttach : favoriteList.getMovieList()) {
                movieListMovieToAttach = em.getReference(movieListMovieToAttach.getClass(), movieListMovieToAttach.getId());
                attachedMovieList.add(movieListMovieToAttach);
            }
            favoriteList.setMovieList(attachedMovieList);
            em.persist(favoriteList);
            for (Movie movieListMovie : favoriteList.getMovieList()) {
                FavoriteList oldFavoriteListidOfMovieListMovie = movieListMovie.getFavoriteListid();
                movieListMovie.setFavoriteListid(favoriteList);
                movieListMovie = em.merge(movieListMovie);
                if (oldFavoriteListidOfMovieListMovie != null) {
                    oldFavoriteListidOfMovieListMovie.getMovieList().remove(movieListMovie);
                    oldFavoriteListidOfMovieListMovie = em.merge(oldFavoriteListidOfMovieListMovie);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FavoriteList favoriteList) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FavoriteList persistentFavoriteList = em.find(FavoriteList.class, favoriteList.getId());
            List<Movie> movieListOld = persistentFavoriteList.getMovieList();
            List<Movie> movieListNew = favoriteList.getMovieList();
            List<Movie> attachedMovieListNew = new ArrayList<Movie>();
            for (Movie movieListNewMovieToAttach : movieListNew) {
                movieListNewMovieToAttach = em.getReference(movieListNewMovieToAttach.getClass(), movieListNewMovieToAttach.getId());
                attachedMovieListNew.add(movieListNewMovieToAttach);
            }
            movieListNew = attachedMovieListNew;
            favoriteList.setMovieList(movieListNew);
            favoriteList = em.merge(favoriteList);
            for (Movie movieListOldMovie : movieListOld) {
                if (!movieListNew.contains(movieListOldMovie)) {
                    movieListOldMovie.setFavoriteListid(null);
                    movieListOldMovie = em.merge(movieListOldMovie);
                }
            }
            for (Movie movieListNewMovie : movieListNew) {
                if (!movieListOld.contains(movieListNewMovie)) {
                    FavoriteList oldFavoriteListidOfMovieListNewMovie = movieListNewMovie.getFavoriteListid();
                    movieListNewMovie.setFavoriteListid(favoriteList);
                    movieListNewMovie = em.merge(movieListNewMovie);
                    if (oldFavoriteListidOfMovieListNewMovie != null && !oldFavoriteListidOfMovieListNewMovie.equals(favoriteList)) {
                        oldFavoriteListidOfMovieListNewMovie.getMovieList().remove(movieListNewMovie);
                        oldFavoriteListidOfMovieListNewMovie = em.merge(oldFavoriteListidOfMovieListNewMovie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = favoriteList.getId();
                if (findFavoriteList(id) == null) {
                    throw new NonexistentEntityException("The favoriteList with id " + id + " no longer exists.");
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
            FavoriteList favoriteList;
            try {
                favoriteList = em.getReference(FavoriteList.class, id);
                favoriteList.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The favoriteList with id " + id + " no longer exists.", enfe);
            }
            List<Movie> movieList = favoriteList.getMovieList();
            for (Movie movieListMovie : movieList) {
                movieListMovie.setFavoriteListid(null);
                movieListMovie = em.merge(movieListMovie);
            }
            em.remove(favoriteList);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FavoriteList> findFavoriteListEntities() {
        return findFavoriteListEntities(true, -1, -1);
    }

    public List<FavoriteList> findFavoriteListEntities(int maxResults, int firstResult) {
        return findFavoriteListEntities(false, maxResults, firstResult);
    }

    private List<FavoriteList> findFavoriteListEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FavoriteList.class));
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

    public FavoriteList findFavoriteList(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FavoriteList.class, id);
        } finally {
            em.close();
        }
    }

    public int getFavoriteListCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FavoriteList> rt = cq.from(FavoriteList.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

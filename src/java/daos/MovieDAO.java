/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Movie;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tuannnh
 */
public class MovieDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mini-cinemaPU");

    public List<Movie> listMovie() throws Exception {
        List<Movie> result;
        EntityManager em = emf.createEntityManager();
        result = em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
        return result;
    }

    public List<Movie> searchByLikeName(String search) throws Exception {
        List<Movie> result;
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT mv FROM Movie mv WHERE mv.title LIKE :search";
        Query qr = em.createQuery(sql);
        result = qr.setParameter("search", "%" + search + "%").getResultList();
        return result;
    }

    public void addMovie(String title, String image, String link, String category) throws Exception {
        EntityManager em = emf.createEntityManager();
        Movie newMovie = new Movie(title, image, link, category);
        em.getTransaction().begin();
        em.persist(newMovie);
        em.getTransaction().commit();
    }

}

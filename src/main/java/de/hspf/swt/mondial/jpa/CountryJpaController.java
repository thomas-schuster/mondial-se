package de.hspf.swt.mondial.jpa;

import de.hspf.swt.mondial.dao.Country;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ThomasSchuster
 */
public class CountryJpaController implements Serializable {
    
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final String PERSISTENCE_UNIT_NAME = "mondialPU";
    private static EntityManagerFactory factory;
    private EntityManager em;

    public CountryJpaController() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    public void closeConnection() {
        em.close();
    }

    /**
     * Find a record by primary key. . Internally uses find method.
     *
     * @param id
     * @return
     */
    public Country findCountry(String id) {
        return em.find(Country.class, id);
    }

    /**
     * *
     * Find a record by primary key. Internally uses named query.
     *
     * @param code
     * @return
     */
    public Country getCountry(String code) {
        TypedQuery<Country> query = em.createNamedQuery("Country.findByCode", Country.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    /**
     * *
     * Take a new or detached entity and add it as a new record in the table
     *
     * @param country
     */
    public void create(Country country) {
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();

    }

    /**
     * *
     * Take a detached entity and update the matching record in the table
     *
     * @param country
     */
    public void edit(Country country) {
        em.getTransaction().begin();
        em.merge(country);
        em.getTransaction().commit();
    }

    /**
     *
     * Delete the record that matched the primary key. Verify that the record
     * exists before deleting it.
     *
     * @param code
     */
    public void delete(String code) {
        em.getTransaction().begin();
        em.getTransaction().commit();
        Country country = em.getReference(Country.class, code);
        em.remove(country);
    }

    /**
     * Return all the records in the table
     *
     * @return
     */
    public Collection<Country> findFishEntities() {
        return getCountries();
    }

    /**
     * Return some of the records from the table. Useful for paginating.
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Country> findCountryEntities(int maxResults, int firstResult) {
        return findCountryEntities(false, maxResults, firstResult);
    }

    public Collection<Country> getCountries() {

        em.getTransaction().begin();
        TypedQuery<Country> query = em.createNamedQuery("Country.findAll", Country.class);

        List<Country> result = query.getResultList();
        em.getTransaction().commit();

        return result;
    }

    
    /**
     * Either find all or find a group of countries
     *
     * @param all True means find all, false means find subset
     * @param maxResults Number of records to find
     * @param firstResult Record number to start returning records
     * @return
     */
    private List<Country> findCountryEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Country.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    /**
     * Return the number of records in the table
     *
     * @return
     */
    public Long getCountryCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Country> rt = cq.from(Country.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        
        Query q = em.createQuery(cq);
        Long number = (Long) q.getSingleResult();
        LOGGER.log(Level.INFO, "country count: {0}", number);
        
        return number;
    }

}

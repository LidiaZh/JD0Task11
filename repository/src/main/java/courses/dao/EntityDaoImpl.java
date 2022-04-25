package courses.dao;

import courses.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class EntityDaoImpl<K, T> implements EntityDao {

    private static EntityManager em;
    private final Class<T> clazz;

    public EntityDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Method to add object
     *
     * @param object
     * @param <T>
     */
    @Override
    public <T> void insert(T object) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("This object can`t be added");
        } finally {
            em.close();
        }
    }

    /**
     * Method to delete object
     *
     * @param object
     * @param <T>
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @Override
    public <T> void delete(T object) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        deleteById(getIdOfObject(object));
    }

    /**
     * Method to delete object by ID
     *
     * @param id
     * @param <T>
     */
    @Override
    public <T> void deleteById(T id) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(em.find(clazz, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("This element is absent in table " +
                    "or has connections with other tables.");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Method to update information about the object
     *
     * @param object
     * @param <T>
     */
    @Override
    public <T> void update(T object) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("This object can`t be updated.");
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Method to get information about the object
     */
    @Override
    public List select() {
        em = HibernateUtil.getEntityManager();
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e";
        Query query = em.createQuery(queryString);
        List list = query.getResultList();
        list.forEach(System.out::println);
        em.close();
        return list;
    }

    /**
     * Method to get Entity
     */
    @Override
    public <K, T> T getEntity(K id) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        T obj = (T) em.find(clazz, id);
        em.getTransaction().commit();
        em.close();
        return obj;
    }

    /**
     * Find "ID" of Object
     */
    private <T> K getIdOfObject(T object)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = object.getClass().getMethod("getId");
        K id = (K) method.invoke(object);
        return id;
    }
}

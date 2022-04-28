package courses.dao;

import courses.entity.Task;
import courses.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplTask extends EntityDaoImpl<Task> {

    private static EntityManager em;

    public EntityDaoImplTask() {
        super(Task.class);
    }

    public void showTaskWithMark() {
        em = HibernateUtil.getEntityManager();
        String queryString = "SELECT t FROM Task t WHERE t.mark is not NULL";
        Query query = em.createQuery(queryString);
        List list = query.getResultList();
        list.forEach(System.out::println);
        em.close();
    }

    public void showTaskWithoutMark() {
        em = HibernateUtil.getEntityManager();
        String queryString = "SELECT t FROM Task t WHERE t.mark is NULL";
        Query query = em.createQuery(queryString);
        List list = query.getResultList();
        list.forEach(System.out::println);
        em.close();
    }
}

package courses.entityTest;

import courses.dao.EntityDaoImplMark;
import courses.entity.Mark;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static courses.constans.ConstantsMark.*;
import static org.junit.Assert.assertTrue;

public class MarkTest {
    private static Mark mark1;
    private static final List<Mark> marks = new ArrayList<>();

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void jpqlMark() {
        mark1 = Utils.createMark();
        EntityManager entityManager = HibernateUtil.getEntityManager();//Java Persistence API
        entityManager.getTransaction().begin();
        entityManager.persist(mark1);
        marks.add(mark1);
        Mark mark2 = entityManager.find(Mark.class, mark1.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(mark2);
        Assert.assertNotNull(mark2.getId());
        Assert.assertEquals("Mark not equals", MARK_GRADE, mark1.getMark());
    }

    @Test
    public void insertTestMark() {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        marks.add(mark1);
        Assert.assertNotNull(daoMark.getEntity(MARK_INSERT_ID));
    }

    @Test
    public void deleteTestMark()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        marks.add(mark1);
        daoMark.delete(mark1);
        Assert.assertNotNull(mark1);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void deleteIdTestMark() {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        marks.add(mark1);
        daoMark.deleteById(MARK_DELETE_BYID);
        Assert.assertNotNull(mark1);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void updateTestMark() {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        mark1.setMark(MARK_SET);
        marks.add(mark1);
        Assert.assertNotEquals(mark1.toString(),
                daoMark.getEntity(MARK_UPDATE_ID).toString());
        daoMark.update(mark1);
        Assert.assertEquals(mark1.toString(),
                daoMark.getEntity(MARK_UPDATE_ID).toString());
    }

    @Test
    public void getEntityTestMark() {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        marks.add(mark1);
        Assert.assertNotNull(daoMark.getEntity(MARK_GET_ID).toString());
    }

    @Test
    public void selectTestMark() {
        mark1 = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark1);
        marks.add(mark1);
        Assert.assertEquals(marks.toString(),
                daoMark.select().toString());
    }
}

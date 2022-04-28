package courses.entityTest;

import courses.dao.EntityDaoImplCourse;
import courses.entity.Course;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static courses.constans.ConstantsCourse.*;
import static org.junit.Assert.assertTrue;

public class CourseTest {
    private static Course course1;
    private static final List<Course> courses = new ArrayList<>();

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void jpqlCourse() {
        course1 = Utils.createCourse();
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course1);
        courses.add(course1);
        Course course2 = entityManager.find(Course.class, course1.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(course2);
        Assert.assertNotNull(course2.getId());
        Assert.assertEquals("Course description not equals",
                COURSE_DESCRIPTION, course1.getDescription());
    }

    @Test
    public void insertTestCourse() {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        courses.add(course1);
        Assert.assertNotNull(daoCourse.getEntity(COURSE_INSERT_ID));
    }

    @Test
    public void deleteTestCourse()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        courses.add(course1);
        daoCourse.delete(course1);
        Assert.assertNull(daoCourse.getEntity(COURSE_DELETE_ID));
    }

    @Test
    public void deleteIdTestCourse() {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        courses.add(course1);
        daoCourse.deleteById(COURSE_DELETE_BYID);
        Assert.assertNull(daoCourse.getEntity(COURSE_DELETE_BYID));
    }

    @Test
    public void updateTestCourse() {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        course1.setHours(COURSE_SET);
        courses.add(course1);
        Assert.assertNotEquals(course1.toString(),
                daoCourse.getEntity(COURSE_UPDATE_ID).toString());
        daoCourse.update(course1);
        Assert.assertEquals(course1.toString(),
                daoCourse.getEntity(COURSE_UPDATE_ID).toString());
        System.out.println(course1.toString());
    }

    @Test
    public void getEntityTestCourse() {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        courses.add(course1);
        Assert.assertNotNull(daoCourse.getEntity(COURSE_GET_ID).toString());
    }

    @Test
    public void selectTestCourse() {
        course1 = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course1);
        courses.add(course1);
        Assert.assertEquals(courses.get(1).toString(), daoCourse.select().get(1).toString());
    }
}

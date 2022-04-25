package courses;

import static courses.Constans.*;
import static org.junit.Assert.assertTrue;

import courses.dao.EntityDaoImpl;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Student;
import courses.entity.Task;
import courses.entity.Teacher;
import courses.util.HibernateUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void jpql() {
        Course course = Utils.createCourse();
        Student student = Utils.createStudent(Set.of(course));
        Teacher teacher = Utils.createTeacher(Set.of(course));
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(course, mark);

        EntityManager entityManager = HibernateUtil.getEntityManager();//Java Persistence API
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(mark);
        entityManager.persist(task);
        Task task1 = entityManager.find(Task.class, task.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(task);
        Assert.assertNotNull(task1.getId());
        Assert.assertEquals("Task description not equals", TASK_DESCRIPTION, task.getDescription());
    }

    @Test
    public void insertTest() {
        Course course1 = Course.builder()
                .description(COURSE_DESCRIPTION)
                .hours(COURSE_HOURS)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(course1);

        Assert.assertNotNull(daoCourse.getEntity(Course.class, DAO_INSERT_ID));
    }

    @Test
    public void deleteTest() {
        Mark mark1 = Mark.builder()
                .mark(MARK)
                .review(MARK_REVIEW)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(mark1);
        daoCourse.delete(mark1);
        Assert.assertNull(daoCourse.getEntity(Mark.class, DAO_DELETE_ID));
    }

    @Test
    public void deleteIdTest() {
        Course course2 = Course.builder()
                .description(COURSE_DESCRIPTION_2)
                .hours(COURSE_HOURS_2)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(course2);
        daoCourse.deleteById(DAO_DELETE_BYID);
        Assert.assertNull(daoCourse.getEntity(Course.class, DAO_DELETE_BYID));
    }

    @Test
    public void updateTest() {
        Course course1 = Course.builder()
                .description(COURSE_DESCRIPTION)
                .hours(COURSE_HOURS)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(course1);
        course1.setHours(COURSE_SET);
        Assert.assertNotEquals(course1.toString(), daoCourse.getEntity(Course.class, UPDATE_ID).toString());
        daoCourse.update(course1);
        Assert.assertEquals(course1.toString(), daoCourse.getEntity(Course.class, UPDATE_ID).toString());
    }

    @Test
    public void getEntityTest() {
        Mark mark2 = Mark.builder()
                .mark(MARK)
                .review(MARK_REVIEW)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(mark2);
        Assert.assertNotNull(daoCourse.getEntity(Mark.class, GET_ID).toString());
    }

    @Test
    public void selectTest() {
        Course course1 = Course.builder()
                .description(COURSE_DESCRIPTION)
                .hours(COURSE_HOURS)
                .build();
        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(course1);
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        Assert.assertEquals(courses.toString(), daoCourse.select().toString());
    }

    @AfterClass
    public static void clean() throws Exception {
        HibernateUtil.close();
    }
}

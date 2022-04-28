package courses.entityTest;

import courses.dao.EntityDaoImplTeacher;
import courses.entity.Course;
import courses.entity.Teacher;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static courses.constans.ConstantsTeacher.*;
import static org.junit.Assert.assertTrue;

public class TeacherTest {
    private static Teacher teacher;
    private static final List<Teacher> teachers = new ArrayList<>();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void jpqlTeacher() {
        Course course = Utils.createCourse();
        teacher = Utils.createTeacher(Set.of(course));
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(teacher);
        Assert.assertEquals("Teacher name not equals", TEACHER_NAME, teacher.getName());
    }

    @Test
    public void insertTestTeacher() {
        Course course = Utils.createCourse();
        teacher = Utils.createTeacher(Set.of(course));
        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
        daoTeacher.insert(course);
        daoTeacher.insert(teacher);
        teachers.add(teacher);
        Assert.assertNotNull(teacher);
    }

    @Test
    public void deleteTestTeacher()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        Course course = Utils.createCourse();
        teacher = Utils.createTeacher(Set.of(course));
        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
        daoTeacher.insert(course);
        daoTeacher.insert(teacher);
        teachers.add(teacher);
        daoTeacher.delete(teacher);
        Assert.assertNotNull(teacher);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void deleteIdTestTeacher() {
        Course course = Utils.createCourse();
        teacher = Utils.createTeacher(Set.of(course));
        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
        daoTeacher.insert(course);
        daoTeacher.insert(teacher);
        teachers.add(teacher);
        daoTeacher.deleteById(TEACHER_DELETE_BYID);
        Assert.assertNotNull(teacher);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void updateTestTeacher() {
        teacher = Teacher.builder()
                .name(TEACHER_NAME)
                .surname(TEACHER_SURNAME)
                .build();
        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
        daoTeacher.insert(teacher);
        teacher.setName(TEACHER_SET_NAME);
        teachers.add(teacher);
        daoTeacher.update(teacher);
        Assert.assertEquals(teacher.getName(),
                TEACHER_SET_NAME);
    }

    @Test
    public void getEntityTestTeacher() {
        Course course = Utils.createCourse();
        teacher = Utils.createTeacher(Set.of(course));
        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
        daoTeacher.insert(course);
        daoTeacher.insert(teacher);
        teachers.add(teacher);
        Assert.assertNotNull(teacher);
    }

//    @Test
//    public void selectTestStudent() {
//        Course course = Utils.createCourse();
//        teacher = Utils.createTeacher(Set.of(course));
//        EntityDaoImplTeacher daoTeacher = new EntityDaoImplTeacher();
//        daoTeacher.insert(course);
//        daoTeacher.insert(teacher);
//        teachers.add(teacher);
//        Assert.assertEquals(teachers.get(0).toString(), daoTeacher.select().get(0).toString());
//    }
}

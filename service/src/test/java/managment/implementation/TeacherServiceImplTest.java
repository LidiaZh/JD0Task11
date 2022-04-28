package managment.implementation;

import courses.dao.EntityDaoImplTeacher;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Task;
import courses.entity.Teacher;
import courses.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;
import utilsTest.Utils;

import javax.persistence.EntityManager;
import java.util.Set;

import static constans.ConstantsTask.TASK_DESCRIPTION;
import static managment.implementation.ConstantsForTest.*;
import static managment.implementation.UtilsForTest.*;

public class TeacherServiceImplTest extends Assert {
    private static Task task;

    @Test
    public void jpqlTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        task = Utils.createTask(mark);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(mark);
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(task);
        Assert.assertEquals("Task description not equals",
                TASK_DESCRIPTION, task.getDescription());
    }

    @Test
    public void addTaskTest() {
        EntityDaoImplTeacher daoTeacher
                = new EntityDaoImplTeacher();
        daoTeacher.insert(Teacher.builder().name("111").surname("2222").build());
        TeacherServiceImpl ts = new TeacherServiceImpl(daoTeacher);

        Task task = ts.addTask(TASK_DESC_1);
        assertEquals(TASK_DESC_1, task.getDescription());
    }

    @Test
    public void deleteTaskTest() {

        EntityDaoImplTeacher daoTeacher
                = new EntityDaoImplTeacher();
        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);

        task = teacherServiceImpl.addTask(TASK_DESC_2);
        teacherServiceImpl.deleteTask(task.getId());
        assertNull("Task was not delete",
                teacherServiceImpl.getTask(task.getId()));
    }

    @Test
    public void showTaskTest() {
        EntityDaoImplTeacher daoTeacher
                = new EntityDaoImplTeacher();
        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);
        Set<Task> setOfTasks = Set.of(getTask(),getTaskForDelTest());
        teacherServiceImpl.showTask();

    }

    @Test
    public void showCourseForTeacherTest() {
        EntityDaoImplTeacher daoTeacher
                = new EntityDaoImplTeacher();
        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);
        teacherServiceImpl.showTask();
    }


}
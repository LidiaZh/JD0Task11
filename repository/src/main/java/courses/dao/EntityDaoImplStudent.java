package courses.dao;

import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import courses.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.Set;

public class EntityDaoImplStudent extends EntityDaoImpl<Student> {

    private static EntityManager em;

    public EntityDaoImplStudent() {
        super(Student.class);
    }

    public void enrollInCourse(Student student, Course course) {
        em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Course courseForFind = em.find(Course.class, course.getId());
            System.out.println("Вы успешно записаны на выбранный курс:");
            student.setCourses(Set.of(course));
            System.out.println(courseForFind.getDescription());
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void selectMark(int studentID, int courseId, int taskId) {
        em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student studentForFind = em.find(Student.class, studentID);
            System.out.println(studentForFind);
            Course courseForFind = em.find(Course.class, courseId);
            System.out.println(courseForFind.getDescription());
            Task taskForFind = em.find(Task.class, taskId);
            System.out.println(taskForFind.getMark());
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

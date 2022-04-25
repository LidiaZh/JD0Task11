package managment;

import courses.dao.EntityDaoImpl;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Person;
import courses.entity.Student;
import courses.entity.Task;
import courses.entity.Teacher;

import java.lang.reflect.InvocationTargetException;

import static java.util.Set.of;

/**
 * Main Class
 */
public class Manager {
    public static void main(String[] args)
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        /**
         * Create courses
         */
        Course course1 = Course.builder()
                .description("Math")
                .hours("140")
                .build();

        Course course2 = Course.builder()
                .description("Gym")
                .hours("150")
                .build();

        Course course3 = Course.builder()
                .description("Physics")
                .hours("150")
                .build();

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Course, ?> daoCourse
                = new EntityDaoImpl<>(Course.class);
        daoCourse.insert(course1);
        daoCourse.insert(course2);
        daoCourse.insert(course3);

        /**
         * Create Students
         */
        Student student1 = Student.builder()
                .name("Mihail")
                .surname("Lutikov")
                .courses(of(course1, course2))
                .build();

        Student student2 = Student.builder()
                .name("Maria")
                .surname("Cvetkova")
                .courses(of(course1))
                .build();

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Person, ?> daoStudents
                = new EntityDaoImpl<>(Student.class);
        daoStudents.insert(student1);
        daoStudents.insert(student2);

        /**
         * Create Teachers
         */
        Teacher teacher1 = Teacher.builder()
                .name("Valeria")
                .surname("Petrova")
                .courses(of(course1, course2))
                .build();

        Teacher teacher2 = Teacher.builder()
                .name("Galina")
                .surname("Ivanova")
                .courses(of(course1))
                .build();

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Person, ?> daoTeacher
                = new EntityDaoImpl<>(Teacher.class);
        daoTeacher.insert(teacher1);
        daoTeacher.insert(teacher2);

        /**
         * Create Marks
         */
        Mark mark1 = Mark.builder()
                .mark(10)
                .review("Amazing!!!")
                .build();

        Mark mark2 = Mark.builder()
                .mark(5)
                .build();

        Mark mark3 = Mark.builder()
                .mark(7)
                .build();

        Mark mark4 = Mark.builder()
                .mark(8)
                .build();

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Mark, ?> daoMark
                = new EntityDaoImpl<>(Mark.class);
        daoMark.insert(mark1);
        daoMark.insert(mark2);
        daoMark.insert(mark3);
        daoMark.insert(mark4);

        /**
         * Create Task
         */
        Task task1 = Task.builder()
                .description("Task1")
                .course(course1)
                .mark(mark2)
                .build();

        Task task2 = Task.builder()
                .description("Task2")
                .course(course2)
                .mark(mark1)
                .build();

        Task task3 = Task.builder()
                .description("Task3")
                .course(course2)
                .mark(mark3)
                .build();

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Task, ?> daoTask
                = new EntityDaoImpl<>(Task.class);
        daoTask.insert(task1);
        daoTask.insert(task2);
        daoTask.insert(task3);

        /**
         * Create DaoImplementation
         */
        EntityDaoImpl<? extends Person, ?> daoPerson
                = new EntityDaoImpl<>(Person.class);

        System.out.println("\n____________Table of Courses____________");
        daoCourse.select();

        System.out.println("\n____________Table of Students____________");
        daoStudents.select();

        System.out.println("\n____________Table of Teachers____________");
        daoTeacher.select();

        System.out.println("\n____________Table of Marks____________");
        daoMark.select();
        daoMark.deleteById(3);
        System.out.println("\n____________Table of Marks after delete " +
                "mark with id = 3____________");
        daoMark.select();

        System.out.println("\n____________Table of Task____________");
        daoTask.select();
        task2.setMark(null);
        System.out.println("\n____________Table of Task after change mark____________");
        daoTask.update(task2);

        System.out.println("\n____________Table of Marks____________");
        daoMark.select();

        System.out.println("\n____________Attempt to delete course1____________");
        daoCourse.delete(course1);
        daoCourse.select();

        System.out.println("\n____________Table of Person____________");
        daoPerson.select();
    }
}

package managment;

import courses.dao.EntityDaoImplAdmin;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Student;
import courses.entity.Task;
import courses.entity.Teacher;
import managment.implementation.AdminServiceImpl;
import managment.implementation.StudentServiceImpl;
import managment.implementation.TeacherServiceImpl;
import managment.interfaces.StudentService;

/**
 * Main Class
 */
public class Manager {
    public static void main(String[] args) {

        /**
         * AdminService
         */
        System.out.println("\n************Admin Service******************");
        AdminServiceImpl as = new AdminServiceImpl<>(new EntityDaoImplAdmin());
        /**
         * Create Courses
         */
        Course course1 = as.createCourse("Math", "240");
        Course course2 = as.createCourse("Gym", "150");
        Course course3 = as.createCourse("Physics", "160");
        System.out.println("\n____________Table of Courses____________");
        as.printCourse();
        /**
         * Create Teachers
         */
        Teacher teacher1 = as.createTeacher("Valeria", "Petrova");
        Teacher teacher2 = as.createTeacher("Galina", "Ivanovf");
        System.out.println("\n____________Table of Teachers____________");
        as.printTeacher();
        /**
         * ConnectTeacherAndCourses
         */
        as.connectTeacherAndCourse(teacher1, course1);
        as.connectTeacherAndCourse(teacher1, course3);
        as.connectTeacherAndCourse(teacher2, course2);
        System.out.println("\n____________Table of Teachers and Courses____________");
        as.printCourse();

        /**
         * Create Mark
         */
        Mark mark1 = as.createMark(1);
        Mark mark2 = as.createMark(2);
        Mark mark3 = as.createMark(3);
        Mark mark4 = as.createMark(4);
        Mark mark5 = as.createMark(5);
        Mark mark6 = as.createMark(6);
        Mark mark7 = as.createMark(7);
        Mark mark8 = as.createMark(8);
        Mark mark9 = as.createMark(9);
        Mark mark10 = as.createMark(10);

        /**
         * StudentService
         */
        System.out.println("\n************Student Service******************");
        StudentService ss = new StudentServiceImpl();
        Student student1 = ss.register("Mihail", "Lutikov");
        Student student2 = ss.register("Maria", "Cvetkova");
        System.out.println("=========================================================================================");
        ss.enrollInCourse(student1, course1);
        System.out.println("=========================================================================================");
        ss.enrollInCourse(student2, course2);
        System.out.println("=========================================================================================");
//        ss.searchInTasks(3, 2, 1);
//        System.out.println("=========================================================================================");
//        ss.searchInTasks(3, 1, 2);
//        System.out.println("=========================================================================================");

        /**
         * TeacherService
         */
        System.out.println("\n************Teacher Service******************");
        TeacherServiceImpl ts = new TeacherServiceImpl(new EntityDaoImplTeacher());
        /**
         * Create Task
         */
        Task task1 = ts.addTask("Task1");
        Task task2 = ts.addTask("Task2");
        Task task3 = ts.addTask("Task3");
        Task task4 = ts.addTask("Task4");
        System.out.println("\n____________Table of Task____________");
        ts.showTask();
        ts.deleteTask(task4.getId());
        System.out.println("\n\n____________Table of Task after Delete Task4____________");
        ts.showTask();
        ts.setTaskToStudent(student1, task1);
        ts.setTaskToStudent(student1, task2);
        ts.setTaskToStudent(student2, task1);
        System.out.println("\n\n____________Table of Task after set Students____________");
        ts.showTask();
        ts.rateTask(1, mark8, "Amazing!");
        ts.rateTask(3, mark6, null);
        System.out.println("\n\n____________Table of Task with Mark____________");
        ts.showTaskWithMark();
        System.out.println("\n\n____________Table of Task without Mark____________");
        ts.showTaskWithoutMark();
    }
}

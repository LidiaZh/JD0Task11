package managment.interfaces;

import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Teacher;

import java.lang.reflect.InvocationTargetException;

public interface AdminService {

    Course createCourse(String desc, String hours);

    void deleteCourse(int id)
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    void updateCourse(String desc, String hours);

    void printCourse();

    Teacher createTeacher(String surname, String name);

    void deleteTeacher(int id);

    void updateTeacher(String surname, String name);

    void printTeacher();

    void connectTeacherAndCourse(Teacher teacher,Course course);

    Mark createMark(Integer mark);
}

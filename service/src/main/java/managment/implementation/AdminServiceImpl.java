package managment.implementation;

import courses.dao.EntityDaoImplAdmin;
import courses.dao.EntityDaoImplCourse;
import courses.dao.EntityDaoImplMark;
import courses.dao.EntityDaoImplPerson;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Teacher;
import managment.interfaces.AdminService;

import java.util.Set;

public class AdminServiceImpl<T> implements AdminService {

    private final EntityDaoImplAdmin daoImplAdmin;

    private final EntityDaoImplCourse daoImplCourse
            = new EntityDaoImplCourse();

    private final EntityDaoImplTeacher daoImplTeacher
            = new EntityDaoImplTeacher();

    private final EntityDaoImplMark daoImplMark
            = new EntityDaoImplMark();

    EntityDaoImplPerson daoPerson
            = new EntityDaoImplPerson();

    public AdminServiceImpl(EntityDaoImplAdmin daoImplAdmin) {
        this.daoImplAdmin = daoImplAdmin;
    }

    @Override
    public Course createCourse(String desc, String hours) {
        Course course = Course.builder()
                .description(desc)
                .hours(hours)
                .build();
        daoImplCourse.insert(course);
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        daoImplCourse.deleteById(id);
    }

    @Override
    public void updateCourse(String desc, String hours) {
        daoImplCourse.update(createCourse(desc, hours));
    }

    @Override
    public void printCourse() {
        daoImplCourse.select();
    }

    @Override
    public Teacher createTeacher(String surname, String name) {
        Teacher teacher = Teacher.builder()
                .surname(surname)
                .name(name)
                .build();
        daoImplTeacher.insert(teacher);
        return teacher;
    }

    @Override
    public void deleteTeacher(int id) {
        daoImplTeacher.deleteById(id);

    }

    @Override
    public void updateTeacher(String surname, String name) {
        daoImplTeacher.update(createTeacher(surname, name));
    }

    @Override
    public void printTeacher() {
        daoImplTeacher.select();
    }

    @Override
    public void connectTeacherAndCourse(Teacher teacher, Course course) {
        teacher.setCourses(Set.of(course));
        daoImplTeacher.update(teacher);
    }

    @Override
    public Mark createMark(Integer grade) {
        Mark mark = Mark.builder()
                .mark(grade)
                .build();
        daoImplMark.insert(mark);
        return mark;
    }
}

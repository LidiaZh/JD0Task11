package courses.utilsTest;

import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Student;
import courses.entity.Task;
import courses.entity.Teacher;

import java.util.Set;

import static courses.constans.ConstansStudent.STUDENT_NAME;
import static courses.constans.ConstansStudent.STUDENT_SURNAME;
import static courses.constans.ConstantsCourse.COURSE_DESCRIPTION;
import static courses.constans.ConstantsCourse.COURSE_HOURS;
import static courses.constans.ConstantsMark.MARK;
import static courses.constans.ConstantsTask.TASK_DESCRIPTION;
import static courses.constans.ConstantsTeacher.TEACHER_NAME;
import static courses.constans.ConstantsTeacher.TEACHER_SURNAME;

public class Utils {
    public static Course createCourse() {
        return Course.builder()
                .description(COURSE_DESCRIPTION)
                .hours(COURSE_HOURS)
                .build();
    }

    public static Student createStudent(Set<Course> courses) {
        return Student.builder()
                .name(STUDENT_NAME)
                .surname(STUDENT_SURNAME)
                .courses(courses)
                .build();
    }

    public static Teacher createTeacher(Set<Course> courses) {
        return Teacher.builder()
                .name(TEACHER_NAME)
                .surname(TEACHER_SURNAME)
 //               .courses(courses)
                .build();
    }

    public static Mark createMark() {
        return Mark.builder()
                .mark(MARK)
                .build();
    }

    public static Task createTask(Mark mark) {
        Task task = Task.builder()
                .description(TASK_DESCRIPTION)
                .mark(mark)
                .build();
        return task;
    }
}

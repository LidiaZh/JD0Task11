package managment.implementation;

import courses.entity.Course;
import courses.entity.Task;
import courses.entity.Teacher;

import java.util.Set;

import static managment.implementation.ConstantsForTest.*;

public class UtilsForTest {


    public static Course getCourse1() {
        Course courseTest1 = Course.builder()
                .description(DESCRIPTION_COURSE_1)
                .hours(HOURS_COURSE)
                .build();
        return courseTest1;
    }

    public static Course getCourse2() {
        Course courseTest2 = Course.builder()
                .description(DESCRIPTION_COURSE_2)
                .hours(HOURS_COURSE)
                .build();
        return courseTest2;
    }

    public static Task getTask() {
        Task taskTest = Task.builder()
                .description(TASK_DESC_1)
                .build();
        return taskTest;
    }

    public static Task getTaskForDelTest() {
        Task taskTest = Task.builder()
                .description(TASK_DESC_2)
                .build();
        return taskTest;
    }

    public static Teacher getTeacher() {
        Teacher teacherTest = Teacher.builder()
                .name(TEACHER_NAME)
                .surname(TEACHER_SURNAME)
 //               .courses(Set.of(getCourse1(),getCourse2()))
                .build();
        return teacherTest;
    }
}

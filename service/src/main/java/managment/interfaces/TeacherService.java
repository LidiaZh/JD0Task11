package managment.interfaces;

import courses.entity.Mark;
import courses.entity.Student;
import courses.entity.Task;

import java.lang.reflect.InvocationTargetException;

public interface TeacherService {
    /**
     * Add Task
     */
    Task addTask(String desc);

    /**
     * Delete Task
     */
    void deleteTask(Integer id)
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    /**
     * Update Task
     */
     void rateTask(Integer id, Mark mark, String review);

    /**
     * Print Task
     */
    void showTask();

    /**
     * Print Task with Mark
     */
    void showTaskWithMark();

    /**
     * Print Task without Mark
     */
    void showTaskWithoutMark();

    /**
     * Print Task and Student
     */
    void setTaskToStudent(Student student, Task task);

    /**
     * Get Task
     */
    Task getTask(Integer id);
}

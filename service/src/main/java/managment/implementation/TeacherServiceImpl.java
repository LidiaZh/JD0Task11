package managment.implementation;

import courses.dao.EntityDaoImplTask;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.Mark;
import courses.entity.Student;
import courses.entity.Task;
import managment.interfaces.TeacherService;

import java.util.Set;

public class TeacherServiceImpl implements TeacherService {

    private final EntityDaoImplTeacher daoImplTeacher;

    private final EntityDaoImplTask daoImplTask =
            new EntityDaoImplTask();

    public TeacherServiceImpl(EntityDaoImplTeacher daoImplTeacher) {
        this.daoImplTeacher = daoImplTeacher;
    }

    @Override
    public Task addTask(String desc) {
        Task task = Task.builder()
                .description(desc)
                .build();
        daoImplTask.insert(task);
        return task;
    }

    @Override
    public void deleteTask(Integer id) {
        daoImplTask.deleteById(id);
    }

    @Override
    public void rateTask(Integer id, Mark mark, String review) {
        Task task = getTask(id);
        task.setMark(mark);
        task.setReview(review);
        daoImplTask.update(task);
    }

    @Override
    public void showTask() {
        daoImplTask.select();
    }

    @Override
    public void setTaskToStudent(Student student, Task task) {
        task.setStudent(student);
        daoImplTask.update(task);
    }

    @Override
    public Task getTask(Integer id) {
        return daoImplTask.getEntity(id);
    }

    @Override
    public void showTaskWithMark() {
        daoImplTask.showTaskWithMark();
    }

    @Override
    public void showTaskWithoutMark() {
        daoImplTask.showTaskWithoutMark();
    }
}

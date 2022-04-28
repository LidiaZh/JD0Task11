package courses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class studentl
 */
@Getter
@Setter
@Entity(name = "Student")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT")
public class Student extends Person implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * Connection with table "Course"
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "students_courses",
            joinColumns = {@JoinColumn(name = "id_student")},
            inverseJoinColumns = {@JoinColumn(name = "id_course")}
    )
    @ToString.Exclude
    @Builder.Default
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();


    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }
}

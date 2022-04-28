package courses.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Admin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ADMIN")

public class Admin implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

}

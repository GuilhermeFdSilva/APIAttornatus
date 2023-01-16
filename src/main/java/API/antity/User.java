package API.antity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "tbl_usuario")
@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nascimento;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "usuario")
    private List<Location> locations;
}

package API.antity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_enderecos")
@Entity
@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endereco;
    @Column(length = 8, nullable = false)
    private String cep;
    @Column(nullable = false)
    private Long numero;
    private String cidade;
    @Column(nullable = false)
    private Long usuario;
    @Column(nullable = false)
    private boolean principal;
}

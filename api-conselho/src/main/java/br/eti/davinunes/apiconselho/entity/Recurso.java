import javax.persistence.*;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "recurso")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notificacao;
    private String bloco;
    private int unidade;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private String email;
    private String nome;

    // Getters e setters
}

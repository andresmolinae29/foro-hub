package forohub.com.api.domain.topic;

import forohub.com.api.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="topicos")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;
    private String curso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private User autor;

    public Topic(TopicRegister topicRegister, User user) {
        this.titulo = topicRegister.titulo();
        this.mensaje = topicRegister.mensaje();
        this.curso = topicRegister.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autor = user;
    }

    public void inactiveTopic() {
        this.status = false;
    }

    public void updateTopic(TopicUpdate topicUpdate) {
        if (topicUpdate.titulo() != null) {
            this.titulo = topicUpdate.titulo();
        }
        if (topicUpdate.mensaje() != null) {
            this.mensaje = topicUpdate.mensaje();
        }
        if (topicUpdate.curso() != null) {
            this.curso = topicUpdate.curso();
        }
    }
}
